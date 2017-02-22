package composetest

/**
  * Created by ibes on 22.02.17.
  */
object Compose {
  def main(args: Array[String]): Unit = {
    def concatFoo(s: String): String = s + "Foo"
    def concatBar(s: String): String = s + "Bar"
    def intToString(i: Int): String = i.toString
    val composed = concatFoo _  andThen concatBar

    println(composed("dsd"))


    def fiveTimer(x: Int) = x * 5
    val fiveTimes = fiveTimer _
    println(fiveTimes(2))
  }



}
