package my

/**
  * Created by ibes on 22.02.17.
  */
sealed trait MyArgs[+A]
case class Vararged [+A](seq: Seq[A]) extends MyArgs[A]

object MyArgs {
  def apply[A](xs: A*): MyArgs[A] = Vararged(Seq(xs: _*))
}
