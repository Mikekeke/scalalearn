package my

/**
  * Created by ibes on 06.03.17.
  */
object FunctionReturningFunction extends App {

  def incrementer(increment: Int): (Int) => Int = (x: Int) => x + increment

  def plusTen: (Int) => Int = incrementer(10)

    val ex_one = plusTen(3)
  //calling 2 functions one line
  val ex_two: Int = incrementer(5)(3)
  println(s"ex_one = $ex_one")
  println(s"ex_two = $ex_two")

}
