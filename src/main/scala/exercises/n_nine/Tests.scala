package exercises.n_nine
import exercises.n_nine.Tasks._

import scala.util.Try

object Tests extends App{
  val ls = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)


  println(Try(nthRecursive(-1, ls)))
  println(Try(nthHLike(11, ls)))
}
