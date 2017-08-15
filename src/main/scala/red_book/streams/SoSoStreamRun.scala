package red_book.streams

/**
  * Created by ibes on 13.04.17.
  */
object SoSoStreamRun extends App{
  def eval = {println("evaluated"); 4}

  println("init NotSoStream:")
  val s1 = NotSoStream(eval)

  println("\ninit not lazy SotSoStream:")
  val s2 = SoSoStream.cons(eval)
  println("access num of not lazy SotSoStream 2 times:")
  val f1 = s2.num()
  val f2 = s2.num()

  println("\ninit lazy SotSoStream:")
  val s3 = SoSoStream.lazyCons(eval)
  println("access num of lazy SotSoStream 2 times:")
  val f3 = s3.num()
  val f4 = s3.num()
}
