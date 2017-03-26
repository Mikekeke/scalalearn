val f: Int => Boolean => Int = x => bool => if (bool) x else 500
f(3)(false)
f(3)(true)


def inter(s: String) = {
  println("Making integer")
  s.toInt
}

val f2: Int => (Boolean, => Int) => Int = x => (bool, num) => if (bool) x else num
f2(3)(false, inter("34"))
f2(3)(true, inter("34"))

val f3: Int => Boolean => (=> Int) => Int = x => bool => num => if (bool) x else num
f3(3)(false)(inter("343"))
f3(3)(true)(inter("343"))


private def reportResult[U, M, R](receiver: U, message: M)(f:(U, M) => R) = f(receiver, message)

val z1: ((String, Int) => String) => String = reportResult[String, Int, String]("test", 11)(_)
val d1 = z1((x, y) => x + y)
val z2: (String, Int, (String, Int) => String) => String = reportResult[String, Int, String](_, _)(_)
val d2 = z2("test", 12, (x,y) => x + y)
//but not val d2 = z2("test", 12)((x,y) => x + y)!
//not compiles val z3 = reportResult[String, Int, String](_, _)((x,y) => x + y)
val z4 = reportResult[String, Int, String]("test", 11)((str, num) => )