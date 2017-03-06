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


val froRes = tryTwo(12).flatMap(tryOne)
val froRes1 = tryTwo(2).flatMap(x => tryOne(x).map(x2 => x + x2))

val tstString = "test"
val withIndex: Seq[(Char, Int)] = tstString.zipWithIndex
val s21: Seq[String] = withIndex.map(t => s"${t._1}${t._2}")
val s22: Seq[Char] = withIndex.flatMap(t => s"${t._1}${t._2}")
val s22_2 = tstString.flatMap(x => new String(x.toString))
val s31 = tstString.map(x => x)
val s32 = tstString.flatMap(x => s"$x$x")









