package my

import scala.concurrent.Future
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


object FutureSequential extends App{
// attempt to call futures in sequence

  val f1: () => Future[Int] = () => Future({println("f-1");1})
  val f2: () => Future[Int] = () => Future({println("f-2");2})
  val f3: () => Future[Int] = () => Future({println("f-3");3})
  val f4: () => Future[Int] = () => Future({println("f-4");4})

  val fut = List(f1, f2, f3, f4)
  val defFut = Future.successful(0)
  val folded: Future[Int] = fut.foldLeft(defFut)((f1, f2) => f1.flatMap(_ => f2()))
  val d1: Int = Await.result(folded, 10 seconds)
  println(d1)


}
