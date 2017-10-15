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
List(1 -> 2, 2 -> 4) doSmth; // if remove ; compiler looks like thinks that '"test" doSmth' passed as argument to 1st doSmth
"test" doSmth