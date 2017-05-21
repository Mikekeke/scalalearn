package exercises.n_nine

/**
  * Created by Mikekeke on 22-May-17.
  */
object Tasks {
  def last[T](l: List[T]): T = l match {
    case Nil => throw new IllegalArgumentException
    case x :: Nil => x
    case x :: xs => last(xs)
  }

  def lastButOne[T](l: List[T]): T =
    if (l.length < 2) throw new IllegalArgumentException
    else l match {
      case x1 :: x2 :: Nil => x1
      case x :: xs => lastButOne(xs)
    }

  def nth[T](n: Int, l: List[T]): T =
    if (n > l.length) throw new IllegalArgumentException
    else if (n > 1) nth(n - 1, l.tail)
    else l.head

  def nthRecursive[A](n: Int, ls: List[A]): A = (n, ls) match {
    case (1, h :: _) => h
    case (n1, _ :: tail) => nthRecursive(n1 - 1, tail)
    case (_, Nil) => throw new NoSuchElementException
  }

  def length[T](l: List[T]): Int = {
    def go(n: Int, l1: List[T]): Int = l1 match {
      case Nil => n
      case _ :: xs => go(n + 1, xs)
    }
    go(0, l)
  }

  def lengthViaFold[T](l: List[T]) =
    l.foldLeft(0)((x, _) => x + 1)

}
