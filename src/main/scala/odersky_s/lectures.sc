sealed trait Expr {
  def eval: Int = this match {
    case Num(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
    case Prod(e1, e2) => e1.eval * e2.eval
  }

  def show: String = this match {
    case Num(n) => n.toString
    case Sum(a, b) => a.show + " + " + b.show
    case Prod(a, b) => a.show + " * " + b.show
  }
}
case class Num(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr

Prod(Sum(Num(2), Num(38)), Num(5)).show