import scala.util.Random

def getRnd = {
  println("Getting random")
  Random.nextInt(10)
}

def strict(f: Int) = {
  // f already evaluated
  val x1 = f
  println(s"strict x1 = $x1")
  val x2 = f
  println(s"strict x1 = $x2")
  println(s"strict sum = ${x1 + x2}")
}

def non_strict(f: => Int) = {
  // f will be evaluated each time its referenced, producing different result
  val x1 = f
  println(s"non_strict x1 = $x1")
  val x2 = f
  println(s"non_strict x1 = $x2")
  println(s"non_strict sum = ${x1 + x2}")
}

strict(getRnd)
println()
non_strict(getRnd)
println("*" * 5)

def someTest1(n: Int) = {
  println("someTest1 - 1: " + n)
  println("someTest1 - 2: " + n)
}

def someTest2(n: => Int) = {
  println("someTest2 - 1: " + n)
  println("someTest2 - 2: " + n)
}

def someTest3(n: => Int) = {
  println("entering someTest 3")
  // will be assigned and cached only when called in println 1st time
  lazy val g = {
    println("assigning g")
    n
  }
  println("-line after assigning g-")
  println("someTest2 - 1: " + g)
  println("someTest2 - 2: " + g)
}

def getRnd2(s: Int) = {
  Random.nextInt(s)
}

someTest1(getRnd2(10))
someTest2(getRnd2(10))
someTest3(getRnd2(10))


