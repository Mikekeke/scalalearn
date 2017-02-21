import scala.annotation.tailrec

/**
  * Created by ibes on 21.02.17.
  */
object Run {
  private def fib(n: Int): Int = {
    @tailrec def go(current: Int, next: Int, iter: Int): Int =
      if (iter ==  1) current
      else {
        go(next, current + next, iter - 1)
      }
    go(0, 1, n)
  }

  def abs(n: Int): Int =
    if (n < 0) -n
    else n

  private def formatAbs(x: Int) = {
    s"The absolute value of $x is ${abs(x)}"
  }

  def factorial(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n-1, n*acc)
    go(n, 1)
  }

  private def formatFactorial(x: Int) = {
    s"The factorial of $x is ${factorial(x)}"
  }

  private def formatResult(name: String, x: Int, f: Int => Int) = {
    s"The $name of $x is ${f(x)}"
  }

  def main(args: Array[String]): Unit = {
    println(formatAbs(-42))
    println(formatFactorial(7))
    println(formatResult("fib number", 7, fib))

  }


}
