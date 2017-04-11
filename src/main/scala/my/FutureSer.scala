package my

import scala.concurrent.Future
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


object FutureSer extends App{
  def f1 = Future({println("f-1");1})
  def f2 = Future({println("f-2");2})
  def f3 = Future({println("f-3");3})
  def f4 = Future({println("f-4");4})
  val futList: Future[Int] =
    Stream(f1, f2, f3, f4).foldLeft(Future.successful(0))((f1, f2) => f1.flatMap(_ => f2))
  val d: Int = Await.result(futList, 10 seconds)
  println(d)
  println("#" * 10)
//  val futList2: Future[Int] =
//    List(f1, f2, f3, f4).reduceLeft((f1, f2) => f1.flatMap(_ => f2))
//  val d2: Int = Await.result(futList2, 10 seconds)
//  println(d2)
  println("End")

}
