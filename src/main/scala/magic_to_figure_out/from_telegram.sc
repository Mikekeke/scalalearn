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

