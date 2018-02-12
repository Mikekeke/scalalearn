package my


import java.time.LocalDateTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}
import scala.util.{Failure, Success, Try}


object FutureTests extends App {

  def f1 = Future {
    val id = (Math.random() * 100).toInt
    def time = LocalDateTime.now()
    println(s"#$id $time print1")
    Thread.sleep(5000)
    println(s"#$id $time print2")
    "Done"
  }

  def retry[A](times: Int)(block: => Future[A]): Future[A] =
    if (times == 0) Future.failed(new Error("Woops"))
    else {
      block fallbackTo {
        retry(times - 1){
          block
        }
      }
    }

  def ff = Future {
    val id = (Math.random() * 100).toInt
    def time = LocalDateTime.now()
    println(s"#$id $time print1")
    Thread.sleep(2000)
    println(s"#$id $time print2")
    "Done"
    throw new Error("lol")
  }

  def retry2[A](times: Int)(block: => Future[A]): Future[A] = {
    if (times == 0) Future.failed(new Error("Woops"))
    else block recoverWith {case _ => retry2(times - 1)(block)}
  }

  Await.ready(retry2(4)(ff), 30000 seconds) onComplete {
    case Success(x) => println(x)
    case Failure(e) => println(e.getCause)
  }
}
