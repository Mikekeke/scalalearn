import scala.language.dynamics
class Ping extends Dynamic{
  def ping = "pong"
  def selectDynamic(s: String) = s
}
val dd = new Ping
dd.ping
dd.tesdt

class TestD extends Dynamic{
  def test1(s: String) = s"Test1: $s"
  def test2(x: Int) = x match {
    case 1 => "One!"
    case 2 => "Two!!"
    case _ => "Beep"
  }

  import reflect.runtime.universe._

  def applyDynamic[A : TypeTag](name: String)(arg: A): String = name match {
    case "test1" if typeOf[A] =:= typeOf[String] =>
      test1(arg.asInstanceOf[String])
    case "test2" if typeOf[A] =:= typeOf[String] && arg.asInstanceOf[String].forall(Character.isDigit) =>
      test2(arg.asInstanceOf[String].toInt)
    case _ => "Undefined"
  }
}
val t1 = new TestD
val dd4 = "test1"
t1.dd4("f")

List(
  ("test1", "hello"),
  ("test2", "2"),
  ("test2", "1"),
  ("test2", "10"),
  ("test2", "fff")
) foreach {case (fst, snd) => println(t1.applyDynamic(fst)(snd))}