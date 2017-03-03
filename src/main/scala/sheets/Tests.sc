import scala.collection.immutable.Seq
import java.util.concurrent.TimeUnit

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

val mon = List(1, 2)
val m2 = mon.map(d => List(d, d + 2))
val m3 = mon.flatMap(d => List(d, d + 2))

val x = 5
val mon2 = Some(x)
val s1 = mon2.map(x => List(x))
val s2 = mon2.flatMap(x => None)

val d1 = "test".map(c => c + "-")
val d2 = "test".flatMap(c => c + "-")
val d3 = "test".to[List]

val c = "test" (3)

val fut: Future[Int] = Future(1).flatMap(x => Future(x + 3))


val res = Await.result(fut, Duration(2, TimeUnit.SECONDS))


def tryOne(i: Int) = Try(i + 2)
def tryTwo(i: Int) = Try(i.ensuring(i < 10, "Error!?!?"))

val res2 = tryOne(3).flatMap(x => tryTwo(x))

def forResFunc(i: Int) = for {
  one <- tryOne(i)
  two <- tryTwo(one)
} yield (one, two)

val froRes = forResFunc(5)

val time = List("1-20", "2-50")
val result: Seq[(Int, Int)] =
  time.map(_.split("-") match {
    case Array(a, b) => (a.toInt, b.toInt)
  })

val reduced = result.reduceRight((a, b) => (a._1 + b._1, a._2 + b._2))
println(reduced)

implicit def tuple2string(t: (Int, Int)): String = s"Time: ${t._1} : ${t._2}"
def count(l: List[String]) = {
  def countTime(time: (Int, Int)) = {
    def go(m: Int, hAcc: Int): (Int, Int) =
      if (m < 60) (hAcc, m) else go(m - 60, hAcc + 1)
    go(time._2, time._1)
  }

  val accTuple = l.map(_.split("-") match {
    case Array(a, b) => (a.toInt, b.toInt)
  }).reduceRight((a, b) => (a._1 + b._1, a._2 + b._2))

  countTime(accTuple)
}

val test: String = count(time)



