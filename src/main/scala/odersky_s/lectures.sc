// insertion sort

def sortAcc(l: List[Int]): List[Int] = l match {
  case Nil => Nil
  case x :: xs => insert(x, sortAcc(xs))
}

def insert(x: Int, xs: List[Int]): List[Int] = ???


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