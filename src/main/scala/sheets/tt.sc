val l1 = List(1,2,3)
val l2 = List(4,5,6)
l1.flatMap(x => l2.map(x1 => List(x, x1)))

def go[T](l: List[T]): List[List[T]] = l match {
  case Nil => List(List.empty[T])
  case ls@x::xs =>
}


