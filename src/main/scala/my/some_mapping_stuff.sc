lazy val stream: Stream[Int] =
  1 #:: 2 #:: 11 #:: 22 #:: stream.tail

stream take 20 foreach println
