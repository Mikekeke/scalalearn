def group(l: List[Int]): List[List[Int]] = l match {
  case Nil => Nil
  case x :: Nil =>
    List(List(x))
  case x :: xs if x == xs.head =>
    val (y :: ys) = group(xs)
    (x :: y) :: ys
  case x :: xs =>
    List(x) :: group(xs)
}
group(List(1,1,2,3,3, 1,1))

def words(s: String): List[String] = {
  val ls = s toList
  def group(l: List[Char]):List[List[Char]] = l match {
    case x :: Nil => List(List(x))
    case x :: xs if x != ' ' =>
      val(c::cs) = group(xs)
      (x::c)::cs
    case _ :: xs => List.empty :: group(xs)
  }

  group(ls) map(_.toString)
}

words("j fdf ggg")