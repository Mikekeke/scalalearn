val l1 = List(1,2,3)
val l2 = List(4,5,6)
l1.flatMap(x => l2.map(x1 => List(x, x1)))

def go[T](l: List[T]): List[List[T]] = l match {
  case Nil => List(List.empty[T])
  case ls@x::xs =>
}
lazy val s: Stream[Int] =
  0 #:: 1 #:: s.zip(s.tail).map(zpd => zpd._1 + zpd._2)

s.take(20).toList

List(List(1, 2), List(3), List(4, 5)).flatMap(l => l)




