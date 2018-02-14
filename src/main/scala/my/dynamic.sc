import scala.language.dynamics
class Ping extends Dynamic{
  def ping = "pong"
  def selectDynamic(s: String) = s
}
val dd = new Ping
dd.ping
dd.tesdt

object TestD {
  sealed trait TestType[A] {
    def appl(ar: A): String
  }

  implicit val applString: TestType[String] = (s: String) => s"Test1: $s"
}

class TestD extends Dynamic{
  def test2(x: Int) = x match {
    case 1 => "One!"
    case 2 => "Two!!"
    case _ => "Beep"
  }

  import reflect.runtime.universe._
  import TestD._

  def applyDynamic[A : TypeTag](name: String)(arg: A): String = name match {
    case "test2" if typeOf[A] =:= typeOf[String] && arg.asInstanceOf[String].forall(Character.isDigit) =>
      test2(arg.toString.toInt)
    case "test1" if typeOf[A] =:= typeOf[String] =>
      implicitly[TestType[A]].appl(arg)
    case _ => "Undefined"
  }
}
val t1 = new TestD
val dd4 = "test1"
t1.dd4("f")

List(
  ("test1", "hello"),
  ("test2", 2),
  ("test2", 1),
  ("test2", 3),
  ("test2", "fff")
) foreach {case (fst, snd) => println(t1.applyDynamic(fst)(snd))}