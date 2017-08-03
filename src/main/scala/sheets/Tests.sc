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
}

val l1 = List('a, 'b, 'c)
val l2 = List('d, 'e)
val l3 = List('f, 'g)
Exercises.concat(List(l1, l2, l3))
Exercises.concatMap(l1)(x => List(x,x,x))









