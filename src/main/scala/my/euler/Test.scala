package my.euler

import scala.collection.mutable.ListBuffer

/**
  * Created by ibes on 24.04.17.
  */
object Test extends App{
  val x: BigInt = 600
  val r: Stream[BigInt] = BigInt(1) #:: BigInt(2) #:: r.zip(r.tail).map(_._2 + 1)

  val r2: Stream[BigInt] = r takeWhile(_ < x)
  var mutList = new ListBuffer[BigInt]
  r2.foreach { num =>
    if (x % num == 0) mutList += num
  }

  mutList.toList.foreach(println)


//  def loopExit(it: Iterator[Long]) = {
//    def go(prev: Long, acc: Long): Long = {
//      val next = it.next()
//      if (acc >= x) prev else go(next, acc * next)
//    }
//    go(1, it.next())
//  }
//
//  loopExit(r.iterator)

}
