package streams

/**
  * Created by ibes on 13.03.17.
  */
object StreamRun extends App{
  val stream = Stream(1,2,3,4,5)
  println(s"To list: ${stream.toList}")
  println(s"Take 3: ${stream.take(3).toList}")
  println(s"Drop 3: ${stream.drop(3).toList}")
  println(s"Take wile: ${stream.takeWhile(_ < 4).toList}")
}
