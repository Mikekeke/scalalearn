package streams

import Stream._

sealed trait Stream[+A] {
  def toListRec: List[A] = this match {
    case _ => List()
    case Cons(h, t) => h() :: t().toListRec
  }

  def toList: List[A] = {
    def go(stream: Stream[A], acc: List[A]): List[A] = stream match {
      case Cons(h, t) => go(t(), h() :: acc)
      case _ => acc
    }

    go(this, List()).reverse
  }

  /*
In order to avoid the `reverse` at the end, we could write it using a
mutable list buffer and an explicit loop instead. Note that the mutable
list buffer never escapes our `toList` method, so this function is
still _pure_.
*/
  def toListFast: List[A] = {
    val buf = new collection.mutable.ListBuffer[A]

    @annotation.tailrec
    def go(s: Stream[A]): List[A] = s match {
      case Cons(h, t) =>
        buf += h()
        go(t())
      case _ => buf.toList
    }

    go(this)
  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 1 => cons(h(), t().take(n - 1))
    case Cons(h, _) if n == 1 => cons(h(), empty)
    case _ => empty
  }

  def dropMy(n: Int): Stream[A] = if (n == 0) this else this match {
    case Cons(_, t) if n > 1 => t().drop(n - 1)
    case Cons(_, t) if n == 1 => t()
    case _ => empty
  }

  /*
  Unlike `take`, `drop` is not incremental. That is, it doesn't generate the
  answer lazily. It must traverse the first `n` elements of the stream eagerly.
  */
  @annotation.tailrec
  final def drop(n: Int): Stream[A] = this match {
    case Cons(_, t) if n > 0 => t().drop(n - 1)
    case _ => this
  }

  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => cons(h(), t().takeWhile(p))
    case _ => empty
  }
}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}