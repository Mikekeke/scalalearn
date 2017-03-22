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