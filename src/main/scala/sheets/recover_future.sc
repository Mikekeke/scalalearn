import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

def div10by(n: Int) = 10 / n
val f1 = Future(div10by(5)).recover{case _ => 11}
val res1 = Await.result(f1, 10 seconds)
val f2 = Future(div10by(0)).recover{case e => e}
val res2 = Await.result(f2, 10 seconds)
