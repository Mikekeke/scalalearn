import java.util.concurrent.TimeUnit

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

def f1 = Future(println("f1"))
def f2 = Future(println("f2"))
def f3 = Future(println("f3"))

val d: Future[Unit] = for {
  tes <-f1
} yield tes