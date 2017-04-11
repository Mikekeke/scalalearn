val testList = List("a", "a", "a", "b", "b", "c", "a")

def pack[T](list: List[T]): List[List[T]] = list match {
  case Nil => Nil
  case x :: xs => list.takeWhile(_ == x) :: pack(list.dropWhile(_ == x))
}

def packBetter[T](list: List[T]): List[List[T]] = list match {
  case Nil => Nil
//case List(x, _*) => // valid too
  case x :: _ =>
    val (first, rest) = list span (_ == x)
    first :: packBetter(rest)
}

pack(testList)
packBetter(testList)

def encode[T](list: List[T]) =
  packBetter(list).map(l => List(l.head, l.length))

encode(testList)

