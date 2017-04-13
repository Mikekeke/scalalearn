package streams

/**
  * Created by ibes on 13.03.17.
  */
object StreamRun extends App{
  val tolist = MyStream(1,2,3,4).take(2)
  println(tolist.toList)
}
