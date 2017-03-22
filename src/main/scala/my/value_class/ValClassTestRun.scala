package my.value_class

/**
  * Created by Mikekeke on 16-Mar-17.
  */
object ValClassTestRun extends App{
  def sayWord(w: Word): Unit = println(s"Saying '$w'")

  sayWord(Word("Bob"))
  sayWord(Word("Bob") + Word("cat"))

  // won't compile
//  sayWord(Word("Bob") + "String")
//  sayWord("String")

}
