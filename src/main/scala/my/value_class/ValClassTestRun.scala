package my.value_class

/**
  * Created by Mikekeke on 16-Mar-17.
  */
object ValClassTestRun extends App{
  val w1 = Word("Bob")
  val w2 = Word("cat")
  val w3 = "String"

  def sayWord(w: Word): Unit = println(s"Saying '$w'")
  sayWord(w1)
  sayWord(w1 + w2)

  // won't compile
//  sayWord(w1 + w3)
//  sayWord(w3)

}
