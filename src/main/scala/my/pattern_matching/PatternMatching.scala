package my.pattern_matching

import my.varargs.MyArgs

/**
  * Created by ibes on 22.02.17.
  */
object PatternMatching {
  def main(args: Array[String]): Unit = {

    // pattern matching tests (doesn't work in REPL for some reason)
    def testFruit(f: Fruit) = f match {
      //      case appleLil() => println("Apple Lil) - will work too
      case o@appleLil() => println(o)
      case a@Apple("Frank") => println(s"This is apple Frank! - $a")
      case Apple(name) => println(s"Apple name: $name")
      case or: Orange => println(or)
    }

    testFruit(Apple("Boris"))
    testFruit(Orange("Tom"))
    testFruit(Apple("Lil"))
    testFruit(Apple("Frank"))


  }
}

