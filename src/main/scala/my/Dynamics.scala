package my

object Dynamics extends App {
  object TestD {
    trait TestType[A] {
      def exec(ar: A): String
    }

//    implicit object AppStr extends TestType[String] {
//      def appl(ar: String): String = s"Test1: $ar"
//    }

    // this syntax not gonna work if make trait sealed
    implicit val applString: TestType[String] = (ar: String) => s"Test1: $ar"
  }

  class TestD extends Dynamic{
    import reflect.runtime.universe._
    import TestD._

    def test2(x: Int) = x match {
      case 1 => "One!"
      case 2 => "Two!!"
      case _ => "Beep"
    }

    def applyDynamic[A : TypeTag : TestType](name: String)(arg: A): String = name match {
      case "test2" if typeOf[A] =:= typeOf[String] && arg.asInstanceOf[String].forall(Character.isDigit) =>
        test2(arg.toString.toInt)
      case "test1" if typeOf[A] =:= typeOf[String] =>
        implicitly[TestType[A]].exec(arg)
      case _ => "Undefined"
    }
  }

  val t1 = new TestD
  println(t1.test1("hello"))
  println(t1.test2(1))
  println(t1.applyDynamic("test1")("hoho"))

  List(
    ("test1", "hello"),
// if uncomment this, list will be of type [String, Any] and implicit lookup won't work
//    ("test2", 2),
//    ("test2", 1),
//    ("test2", 3),
    ("test2", "fff")
  ) foreach {
    case (fst, snd) => println(t1.applyDynamic(fst)(snd))
  }
}
