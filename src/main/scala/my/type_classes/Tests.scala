package my.type_classes

object Tests extends App{
  object PrintAddon {
    trait SmthAndPrint[T] {
      def excecute(l : T): String
    }

    implicit object TplList extends SmthAndPrint[List[(Int, Int)]] {
      override def excecute(l: List[(Int, Int)]): String =
        l.map { case (a,b) => a + b} mkString("-")
    }

    implicit val StringPrinter: SmthAndPrint[String] =
      (s: String) => s"!$s!"

    implicit class PrtUtil[T](x: T) {
      def doSmth(implicit printer: SmthAndPrint[T]): String = printer.excecute(x)
    }
  }

  import PrintAddon._
  println(List(1 -> 2, 2 -> 4) doSmth)

  println("test" doSmth)

}
