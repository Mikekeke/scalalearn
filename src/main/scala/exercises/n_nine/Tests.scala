package exercises.n_nine
import exercises.n_nine.Tasks._

object Tests extends App{
  val ls = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)


  println(myRotate1(3, ls).zip(List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)).forall {case (a, b) => a == b})
}
