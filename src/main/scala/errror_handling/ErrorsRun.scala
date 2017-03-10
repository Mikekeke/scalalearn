package errror_handling

/**
  * Created by ibes on 10.03.17.
  */
object ErrorsRun extends App{
  val test1 = Some(1)
  println(test1.filter(_ > 9))

  val test2 = None
  println(test2.orElse(Some("Some")))



}
