object ListAddon {
  trait Prt[T <: List[_]] {
    def prt(l : T): Unit
  }

  object Prt {
    def apply[T <: List[_]](implicit printer: Prt[T]) = printer
  }

  implicit object TplList extends Prt[List[(Int, Int)]] {
    override def prt(l: List[(Int, Int)]): Unit =
      l.map { case (a,b) => a + b} foreach println
  }

  implicit class ListUtil[T <: List[_]](ls: T) {
    def >>(implicit printer: Prt[T]): Unit = printer.prt(ls)
//    def >>>[T: Prt] = Prt[T].prt() //doesn't work
  }
}

import ListAddon._
List(1 -> 2, 2 -> 4) >>