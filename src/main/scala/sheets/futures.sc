import java.util.Random
import java.util.concurrent.TimeUnit

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
val r = new Random()
def f1(r: Long) = Future {
  if (r > 10) r else throw new IllegalStateException("wrong")
}
def inFuture = f1(r.nextInt(30
)).map(res => {
  println("Future came")
  res > 15
})

Await.result(inFuture, 3 seconds)


