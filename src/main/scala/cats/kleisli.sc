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
val multByTwo: Int => Option[Double] = x => Some(x * 2.1)

val t1: Kleisli[Option, String, Double] =
  Kleisli(multByTwo) compose Kleisli(intFromString)
"t1 = " + (tstFun compose testWithK) (t1)

val t2: String => Option[Double] = Option(_) >>= intFromString >>= multByTwo
"t2 = " + (tstFun compose testWith) (t2)

val t3: String => Option[Double] = Option(_) flatMap intFromString flatMap multByTwo
"t3 = " + tstFun(testWith(t3))

val t4: String => Option[Double] = intFromString(_) flatMap multByTwo
"t4 = " + tstFun(testWith(t3))

Option("112") >>= intFromString >>= multByTwo
Option("1v2") >>= intFromString >>= multByTwo