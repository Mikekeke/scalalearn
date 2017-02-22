package datastructures

sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def tail[A](list: List[A]): List[A] = list match {
    case Nil => throw new IllegalArgumentException("Can't get tail of Nil")
    case Cons(_, t) => t
  }

  def setHead[A](head: A, list: List[A]): List[A] = list match {
    case Nil => throw new IllegalArgumentException("Can't set head to Nil")
    case Cons(_, t) => Cons(head, t)
  }

  def drop[A](l: List[A], n: Int): List[A] =
    if (n <= 0) l
    else l match {
      case Nil => Nil
      case Cons(_, t) => drop(t, n - 1)
    }

  //  my variant
  //  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
  //    case Nil => Nil
  //    case Cons(h, t) => if (f(h)) drop(l, 1) else dropWhile(t, f)
  //  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  def dropWhile2[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  //  my variant
  //  def drop[A](l: List[A], n: Int): List[A] = l match {
  //    case Nil => throw new IllegalArgumentException("Can't drop Nil")
  //    case Cons(_, t) =>
  //      if (n > 1 && t == Nil) throw new IndexOutOfBoundsException()
  //      else if (n == 1) t
  //      else drop(t, n - 1)
  //  }

  //  def init[A](l: List[A]): List[A] = l match {
  //    case Nil => Nil
  //    case Cons(h, Nil) =>
  //  }

  def init1[A](l: List[A]): List[A] =
    l match {
      case Nil => sys.error("init of empty list")
      case Cons(_, Nil) => Nil
      case Cons(h, t) => Cons(h, init1(t))
    }

  def init2[A](l: List[A]): List[A] = {
    import collection.mutable.ListBuffer
    val buf = new ListBuffer[A]

    @annotation.tailrec
    def go(cur: List[A]): List[A] = cur match {
      case Nil => sys.error("init of empty list")
      case Cons(_, Nil) => List(buf.toList: _*)
      case Cons(h, t) => buf += h; go(t)
    }

    go(l)
  }

  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = l match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def sum2(ns: List[Int]) = foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) = foldRight(ns, 1.0)(_ * _)

  def length[A](as: List[A]): Int =
    foldRight(as, 0)((_, acc: Int) => acc + 1)


}
