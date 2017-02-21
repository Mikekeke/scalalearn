import scala.annotation.tailrec

/**
  * Created by ibes on 21.02.17.
  */
object Ex2_2 {

  def isSorted[A] (as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    @tailrec def loop(n:Int): Boolean =
      if (n >= as.length) true
      else if (!ordered(as(n-1), as(n))) false
      else loop(n + 1)
    loop(1)
  }

  def main(args: Array[String]): Unit = {
    val intArr = Array(1,2,3,4)
    val strArr = Array("one", "two", "three")

    println(s"Integer array sorted: ${isSorted(intArr, (x1: Int, x2: Int) => x1 < x2)}")
    println(s"String array sorted: ${isSorted(strArr, (x1: String, x2: String) => x1.length >= x2.length)}")

  }

}
