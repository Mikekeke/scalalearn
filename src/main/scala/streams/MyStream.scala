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
    case Cons(h, t) if n > 1 => cons(h(), t().take(n-1))
    case Cons(h, _) if n == 1 => cons(h(), empty)
    case _ => empty
  }

  def takeWhile(p: A => Boolean): MyStream[A] = this match {
    case Cons(h, t) if p(h()) => cons(h(), t().takeWhile(p))
    case _ => empty
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }

  def exists(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b)

  def forAllMy(f: A => Boolean): Boolean =
    foldRight(true)((a, b) => if(f(a)) b else false)

  def forAll(f: A => Boolean): Boolean =
    foldRight(true)((a, b) => f(a) && b)

  def takeWhileViaFoldRight(f: A => Boolean): MyStream[A] =
    foldRight(empty[A])((h, t) => if (f(h)) cons(h, t) else empty)

  def map[B](f: A => B): MyStream[B] =
    foldRight(empty[B])((h, t) => cons(f(h), t))

  def filter(p: A => Boolean): MyStream[A] =
    foldRight(empty[A])((h,t) => if (p(h)) cons(h, t) else t)

  def append[B>:A](s: => MyStream[B]): MyStream[B] =
    foldRight(s)((h,t) => cons(h, t))

  def flatMap[B>:A](p: B => MyStream[B]): MyStream[B] =
    foldRight(empty[B])((h,t) => p(h).append(t))
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
