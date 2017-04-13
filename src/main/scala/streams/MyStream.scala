package streams

import scala.annotation.tailrec
import streams.MyStream.{cons, empty}

sealed trait MyStream[+A] {
  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, _) => Some(h())
  }

  def toListRecursive: List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => h() :: t().toListRecursive
  }

  def toList: List[A] = {
    @tailrec
    def go(s: MyStream[A], acc: List[A]): List[A] = s match {
      case Cons(h, t) => go(t(), h() :: acc)
      case Empty => acc
    }

    go(this, List.empty[A]).reverse
  }

  def toListFast: List[A] = {
    val buf = new collection.mutable.ListBuffer[A]

    @annotation.tailrec
    def go(s: MyStream[A]): List[A] = s match {
      case Cons(h, t) =>
        buf += h()
        go(t())
      case _ => buf.toList
    }

    go(this)
  }

  def take(n: Int): MyStream[A] = this match {
    case Empty => empty
    case Cons(h, t) => if (n > 0) cons(h(), t().take(n-1)) else cons(h(), MyStream.empty)
  }
}

case object Empty extends MyStream[Nothing]

case class Cons[+A](head: () => A, tail: () => MyStream[A]) extends MyStream[A]

object MyStream {
  def cons[A](head: => A, tail: => MyStream[A]): MyStream[A] = {
    lazy val hd = head
    lazy val tl = tail
    Cons(() => hd, () => tl)
  }

  def empty[A]: MyStream[A] = Empty

  def apply[A](as: A*): MyStream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}
