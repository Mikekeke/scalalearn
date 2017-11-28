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


// ************************
//simplicitly
//в 4.2 This means that c1’s type can be instantiated
// through widening or polymorphic parameter instantiation to be c2’s type

class Foo{
  override def toString = "Foo"
}
class FooSpec extends Foo{
  override def toString = "FooSpec"
}


implicit val foo: Foo = new Foo
implicit val fooSpec: FooSpec = new FooSpec

implicitly[Foo]

implicit val fooShow: Foo => String = _ => "foo"
implicit val fooSpecShow: FooSpec => String = _ => "fooSpec"

implicitly[FooSpec => String].apply(implicitly)

implicit val fooFunc1: Foo => List[Foo] = _ => List(new Foo, new Foo)
implicit val fooFunc2: Foo => List[FooSpec] = _ => List(new FooSpec, new FooSpec)
implicit val fooFunc3: FooSpec => List[Foo] = _ => List(new FooSpec, new Foo)
implicit val fooFunc4: FooSpec => List[FooSpec] = _ => List(new FooSpec)

implicitly[FooSpec => List[Foo]].apply(implicitly)


//  как можно добраться до объекта-компаньона, если в метод передан тип
trait Companion[A] {
  def foo1: String
}

case class Dummy()
object Dummy extends Companion[Dummy] {
  implicit def companion:Companion[Dummy] = this
  def foo1: String = "Dummy string"
}

// could be class ot trait (they define type), but not object
trait OtherShit
object OtherShit extends Companion[OtherShit] {
  implicit def companion:Companion[OtherShit] = this
  def foo1: String = "OtherShit string"
}

def foo1[A](implicit c: Companion[A]) = println(c.foo1)

foo1[Dummy]
foo1[OtherShit]