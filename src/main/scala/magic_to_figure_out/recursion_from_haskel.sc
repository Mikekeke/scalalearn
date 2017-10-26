def group(l: List[Int]): List[List[Int]] = l match {
  case Nil => Nil
  case x :: Nil =>
    println(s"x :: Nil == $x:Nil")
    List(List(x))
  case x :: xs if x == xs.head =>
    val (y :: ys) = group(xs)
    println(s"x:xs == $x:$xs")
    println(s"y:ys == $y:$ys")
    println(s"(x :: y) :: ys = ($x :: $y) :: $ys")
    (x :: y) :: ys
  case x :: xs =>
    println(s"x:xs != $x:$xs")
    List(x) :: group(xs)
}
group(List(1,1,2,3,3))