lazy val stream: Stream[Int] =
  1 #:: 2 #:: 11 #:: 22 #:: stream.zip(stream.tail).map(n => n._2 - n._1)

stream take 6 foreach println

val stream2 = Stream.from(1, 23)
stream2.take(3).foreach(println)

val d = "rerer".flatMap(_ + "d")
val dd = "rerer".map(_ + "d")
