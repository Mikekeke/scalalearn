final case class Countable[T](count: BigInt)
implicit val countableUnit = Countable[Unit](1)
implicit def countableSum[A, B](implicit a: Countable[A], b: Countable[B]) = Countable[(A, B)](a.count + b.count)

class Fib[C, P]{
  def succ: Fib[(C, P), C] = new Fib[(C, P), C]
  def value(implicit cur: Countable[C]): BigInt = cur.count
}

val one = new Fib[Unit, Unit]

val x = one.succ.succ.succ.succ.succ.succ.succ.succ.succ.succ.succ.succ.succ.succ
x.value



// ************************
//import cats.Show
//import cats.syntax.show._
//
//type Sum[x] = AnyRef{type original = x}
//def Sum[x](x: x): Sum[x] = x.asInstanceOf[Sum[x]]
//implicit class SumOps[x](sum: Sum[x]){
//  def getSum: x = sum.asInstanceOf[x]
//}
//
//implicit def showSum[x: Show]: Show[Sum[x]] = (x: Sum[x]) => s"Sum(${x.getSum.show})"
//implicit val showInt: Show[Int] = _.toString
//
//1.show
//1.toString
//Sum(1).show
//Sum(1).toString

