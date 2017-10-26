lazy val s: Stream[Int] =
  0 #:: 1 #:: s.zip(s.tail).map(zpd => zpd._1 + zpd._2)

s.take(20).toList

List(List(1, 2), List(3), List(4, 5)).flatMap(l => l)




