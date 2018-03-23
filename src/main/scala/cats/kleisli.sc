// https://blog.ssanj.net/posts/2017-06-07-composing-monadic-functions-with-kleisli-arrows.html
  import cats.data.Kleisli
  import cats.implicits._

val correct: List[Option[Double]] = List(Some(8.4), None, None)
val testVals = List("4", "a222", "")
val tstFun: List[Option[Double]] => String = l => if (l == correct) "OK" else "BAD"

def testWith(f: String => Option[Double]): List[Option[Double]] =
  testVals map f

def testWithK(f: Kleisli[Option, String, Double]): List[Option[Double]] =
  testVals.map(s => f(s))


val intFromString: String => Option[Int] = s =>
  if (s.nonEmpty && s.forall(Character.isDigit)) Some(s.toInt) else None
val multByTwoOne: Int => Option[Double] = x => Some(x * 2.1)

val t1: Kleisli[Option, String, Double] =
  Kleisli(multByTwoOne) compose Kleisli(intFromString)
"t1 = " + (tstFun compose testWithK) (t1)

val t2: String => Option[Double] = Option(_) >>= intFromString >>= multByTwoOne
"t2 = " + (tstFun compose testWith) (t2)

val t3: String => Option[Double] = Option(_) flatMap intFromString flatMap multByTwoOne
"t3 = " + tstFun(testWith(t3))

val t4: String => Option[Double] = intFromString(_) flatMap multByTwoOne
"t4 = " + tstFun(testWith(t3))

Option("112") >>= intFromString >>= multByTwoOne
Option("1v2") >>= intFromString >>= multByTwoOne
//val kl1: Kleisli[Option, Nothing, String] = Kleisli.liftF(Option("12")) // idea's type, but can't call with Nothing
def someOpt[T](x:T) = Some(x)
val kl1 = Kleisli.liftF[Option, String, Int](Option(1))
val kLift1Test = kl1 andThen Kleisli(multByTwoOne)
val liftRes = kLift1Test("fff")



