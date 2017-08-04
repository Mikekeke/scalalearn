// http://blog.tmorris.net/posts/scala-exercises-for-beginners/

object Exercises {
  def succ(n: Int) = n + 1

  def pred(n: Int) = n - 1

  def add(x: Int, y: Int): Int = {
      if (y > 0) add(succ(x), pred(y)) else  x
  }

  def sum(x: List[Int]): Int = {
    def go(l: List[Int], acc: Int): Int = l match {
      case Nil => acc
      case h :: t => go(t, add(acc, h))
    }
    go(x, 0)
  }

  def length[A](x: List[A]): Int = {
    def go(l: List[A], acc: Int): Int = l match {
      case Nil => acc
      case _ :: t => go(t, acc + 1)
    }
    go(x, 0)
  }

  def map[A, B](x: List[A])(f: A => B): List[B] = x match {
    case Nil => Nil
    case h :: t => f(h) :: map(t)(f)
  }

  def filter[A](x: List[A])(f: A => Boolean): List[A] = x match {
    case Nil => Nil
    case h :: t if f(h) => h :: filter(t)(f)
    case _ :: t => filter(t)(f)
  }

  def append[A](x: List[A], y: List[A]): List[A] = x match {
    case Nil => y
    case h :: t => h :: append(t, y)
  }

  def concat[A](x: List[List[A]]): List[A] = x match {
    case Nil => Nil
    case h :: t => append(h, concat(t))
  }

  def concatMap[A, B](x: List[A])(f: A => List[B]): List[B] = x match {
    case Nil => Nil
    case h :: t => append(f(h), concatMap(t)(f))
  }

  def myMaximum(x: List[Int]): Int = {
    require(x.nonEmpty, new IllegalArgumentException("Empty list"))
    x match {
      case h :: Nil => h
      case h :: t =>
        if (h > t.head) myMaximum(h :: t.tail)
        else myMaximum(t)
    }
  }

  def betterMaximum(x: List[Int]): Int = {
    require(x.nonEmpty, new IllegalArgumentException("Empty list"))
    x match {
      case h :: Nil => h
      case a :: b :: rest => betterMaximum((if (a > b) a else b) :: rest)
    }
  }

  def reverse[A](x: List[A]): List[A] = {
    def addBackwards(from: List[A], to: List[A]): List[A] = from match {
      case Nil => to
      case h :: t => addBackwards(t, h :: to)
    }
    addBackwards(x, List.empty[A])
  }

}

val l1 = List('a, 'b, 'c)
val l2 = List('d, 'e)
val l3 = List('f, 'g)
Exercises.concat(List(l1, l2, l3))
Exercises.concatMap(l1)(x => List(x,x,x))
Exercises.myMaximum(List(1,5,6,7,8,4,5,67,8))
Exercises.betterMaximum(List(1,5,6,7,8,4,5,67,8))
Exercises.reverse(List(1,2,3,4,5,6))









