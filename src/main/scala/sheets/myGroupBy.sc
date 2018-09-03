List(1, 1, 2, 3, 3).groupBy(_ > 2)

def grpBy[A, B](l: List[A])(f: A => B): Map[B, List[A]] = {
  def go(l: List[A], acc: Map[B, List[A]]): Map[B, List[A]] =
    l match {
      case Nil => acc
      case x :: xs if acc.contains(f(x)) =>
        val key: B = f(x)
        go(xs, acc + (key -> (x :: acc(key))))
      case x :: xs => go(xs, acc + (f(x) -> List(x)))
    }

  go(l, Map.empty)
}

grpBy(List(1, 2, 2))(identity)

def grpBy_2[A, B](l: List[A])(f: A => B): Map[B, List[A]] =
  l.foldLeft(Map.empty[B, List[A]]) { case (acc, el) =>
    val key = f(el)
    if (acc.contains(key)) acc + (key -> (el :: acc(key)))
    else acc + (key -> List(el))
  }
grpBy_2(List(1, 2, 2))(identity)

def groupWith[A](l: List[A])(p : A => A => Boolean): List[List[A]] = l match {
  case Nil => Nil
  case x :: Nil =>
    List(List(x))
  case x :: xs if p(x)(xs.head) =>
    val y :: ys = groupWith(xs)(p)
    (x :: y) :: ys
  case x :: xs =>
    List(x) :: groupWith(xs)(p)
}

groupWith("abcdfqfxyz".toList)(x => h => (x+1).toChar == h)
groupWith("aaaddfqqqfxyz".toList)(x => h => x == h)