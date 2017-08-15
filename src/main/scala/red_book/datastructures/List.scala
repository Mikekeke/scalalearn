package red_book.datastructures

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
    case Cons(head, tail) => f(head, foldRight(tail, z)(f))
  }

  def sum2(ns: List[Int]) = foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) = foldRight(ns, 1.0)(_ * _)

  def length[A](as: List[A]): Int =
    foldRight(as, 0)((_, acc: Int) => acc + 1)

  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(head, tail) => foldLeft(tail, f(z, head))(f)
  }

  def sum3(l: List[Int]) = foldLeft(l, 0)(_ + _)

  def product3(l: List[Double]) = foldLeft(l, 1.0)(_ * _)

  def length2[A](l: List[A]): Int = foldLeft(l, 0)((acc, _) => acc + 1)

  def reverse[A](l: List[A]): List[A] = foldLeft(l, List[A]())((acc,h) => Cons(h,acc))

//  def append[A](x: A, l: List[A]): List[A] = x match {
//    case Nil => l
//    case Cons(h, t) =>
//  }

  def appendViaFoldRight[A](l: List[A], r: List[A]): List[A] =
    foldRight(l, r)(Cons(_,_))

  def add1(l: List[Int]): List[Int] =
    foldRight(l, Nil:List[Int])((h,t) => Cons(h+1, t))

  def double2string(l: List[Double]): List[String] =
    foldRight(l, Nil: List[String])((h, t) => Cons(h.toString, t))

  def map[A,B](as: List[A])(f: A => B): List[B] =
    foldRight(as, Nil: List[B])((h, t) => Cons(f(h), t))


  def foldRightViaFoldLeft[A,B](l: List[A], z: B)(f: (A,B) => B): B =
    foldLeft(reverse(l), z)((b,a) => f(a,b))

  def map_1[A,B](l: List[A])(f: A => B): List[B] =
    foldRightViaFoldLeft(l, Nil:List[B])((h,t) => Cons(f(h),t))

//  def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] =
//    foldRight(l, Nil: List[B])((x,b) => )

//  def filter[A](as: List[A])(f: A => Boolean): List[A] = as match {
//    case l @ Cons(h, t) if !f(h) => map(l)((_) => Cons(l.head, t.tail))

}
