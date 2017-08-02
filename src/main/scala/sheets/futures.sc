import java.util.Random
import java.util.concurrent.TimeUnit

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}
val r = new Random()
def f1(r: Long) = Future {
  if (r > 10) r else throw new IllegalStateException("wrong")
}
val inFuture =
  f1(r.nextInt(1000))
  .map(res => {
  println("Future came")
  res > 15
})

Await.result(inFuture, 3 seconds)

val f = Future { 5 }
f andThen {
  case r => sys.error("runtime exception")
} andThen {
  case Failure(t) => println(t)
  case Success(v) => println(v)
}
Await.result(f, 3 seconds)


