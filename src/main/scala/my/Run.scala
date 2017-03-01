package my

import my.pattern_matching.{Apple, Fruit, Orange, appleLil}

/**
  * Created by ibes on 22.02.17.
  */
object Run {
  def main(args: Array[String]): Unit = {
    val ints = MyArgs("one", "two", "3")
    println(ints)

    // pattern matching tests (doesn't work in REPL for some reason)
    def testFruit(f: Fruit) = f match {
      case o @ appleLil() => println(o)
      case a @ Apple("Frank") => println("This is apple Frank!")
      case Apple(name) => println(s"Apple name: $name")
      case or: Orange => println(or)
    }

    testFruit(Apple("Boris"))
    testFruit(Orange("Tom"))
    testFruit(Apple("Lil"))
    testFruit(Apple("Frank"))


  }
}

