package red_book.streams

import scala.annotation.tailrec
import red_book.streams.MyStream.{cons, empty}

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

  def mapSimple[B](f: A => B): MyStream[B] = this match {
    case Empty => empty
    case Cons(h, t) => cons(f(h()), t().mapSimple(f))
  }

  def filter(p: A => Boolean): MyStream[A] =
    foldRight(empty[A])((h,t) => if (p(h)) cons(h, t) else t)

  def find(p: A => Boolean): Option[A] =
    filter(p).headOption

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

  def myConstant[A](a: A): MyStream[A] = cons(a, myConstant(a))

  // This is more efficient than `cons(a, constant(a))` since it's just
  // one object referencing itself.
  def constant[A](a: A): MyStream[A] = {
    lazy val tail: MyStream[A] = Cons(() => a, () => tail)
    tail
  }

  def from(n: Int): MyStream[Int] = cons(n, from(n + 1))

//  doh
  def myFibs: MyStream[Int] = {
    val s1 = cons(0, empty[Int])
    val s2 = cons(1, empty[Int])
    def go(x1: MyStream[Int], x2: MyStream[Int]): MyStream[Int] = {
      lazy val Cons(h1, _) = x1
      lazy val Cons(h2, _) = x2
      lazy val x3: MyStream[Int] = cons(h1() + h2(), go(x2, x3))
      x3
    }
    s1.append(s2).append(go(s1, s2))
  }

  val fibs = {
    def go(f0: Int, f1: Int): MyStream[Int] =
      cons(f0, go(f1, f0+f1))
    go(0, 1)
  }
}
