trait Stringer[T] {
  def stringify(v: T): String
}

val boolStr: Stringer[Boolean] = (b: Boolean) => s"$b now String"

object someStr extends Stringer[Option[_]] {
  def stringify(v: Option[_]) = v match {
    case s: Some[_] => s"$s is string now"
    case None => s"None string too"
  }
}

boolStr stringify true
someStr stringify Some(44)
someStr stringify None

// ++++++++++++++++++++++++++++++++++++

object ThingsDoer {
  def apply[A](f: => A) = f
  // or
//  def apply[A](f: A) = f
}

ThingsDoer {
  val r = Math.random() * 100
  println(s"We randomed $r")
}
