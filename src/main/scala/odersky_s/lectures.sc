import scala.collection.immutable.::

def merge(xs: List[Int], ys: List[Int]): List[Int] =
  (xs, ys) match {
    case (Nil, xs) => ys
    case (_, Nil) => xs
    case (x1 :: xs1, y1 :: ys1) =>
      if (x1 < y1) x1 :: merge(xs1, ys)
      else y1 :: merge(xs, ys1)
  }

// insertion sort
val testList = 8 :: 1 :: 14 :: 34 :: Nil
def sortAcc(l: List[Int]): List[Int] = l match {
  case Nil => Nil
  case x :: xs => insert(x, sortAcc(xs))
}

def insert(x: Int, xs: List[Int]): List[Int] = {
  xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
  }
}
sortAcc(testList)

def init[T](l: List[T]): List[T] = l.tail match {
  case _ :: _ => l.head :: init(l.tail)
  case Nil => Nil
}

init(testList)


sealed trait Expr {
  def eval: Int = this match {
    case Num(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
    case Prod(e1, e2) => e1.eval * e2.eval
  }

  def show: String = {
    def checkBrackets(a: Expr) = if (a.isInstanceOf[Sum]) s"(${a.show})" else a.show
      this match {
      case Num(n) => n.toString
      case Sum(a, b) => a.show + " + " + b.show
      case Prod(a, b) =>
        s"${checkBrackets(a)} * ${checkBrackets(b)}"
    }
  }
}
case class Num(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr

Prod(
  Sum(Num(2), Prod(Num(9), Num(11))
  ), Num(5)).show

Prod(Num(9), Sum(Num(11), Num(23))).show
Prod(Sum(Num(11), Num(23)), Sum(Num(11), Num(23))).show