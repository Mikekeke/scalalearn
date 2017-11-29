import ThingsDoer._

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

  object doThing1 {
    def apply[A](f: => A) = f
  }

  def doThing2[A](f: => A) = f

}

ThingsDoer {
  val r = Math.random() * 100
  println(s"1 - Randomed $r")
}

ThingsDoer {
  doThing1 {
    val r = Math.random() * 100
    println(s"2 - Randomed in doThing1 'nested' $r")
  }
}

ThingsDoer doThing1 {
  println(s"3 - Randomed in ThingsDoer apply() doThing1 apply{} ${Math.random() * 100}")
}
ThingsDoer doThing1 println(s"4 - Randomed in ThingsDoer apply() doThing1 apply() ${Math.random() * 100}")

ThingsDoer doThing2 println(s"5 - Randomed in doThing1 onelined method call ${Math.random() * 100}")
ThingsDoer doThing2 {
  println(s"6 - Randomed in doThing1 {} method call ${Math.random() * 100}")
}
