package streams

/**
  * Created by ibes on 13.03.17.
  */
object StreamRun extends App{
  val stream = Stream(1,2,3,4,5)
  println(stream.toList)
  println(stream.take(3).toList)
  println(stream.drop(0).toList)
}
