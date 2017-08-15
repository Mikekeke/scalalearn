package my

object FutureCrutch extends App {

  import java.util.concurrent.{Executors, TimeUnit}

  import scala.concurrent.{Await, ExecutionContext, Future}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration._

  // can run futures in sequence just by making context from fixed thread pool with 1 thread and pass it to future explicitly
  val ec1 = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(1))


  def futSeq(els: Seq[() => Int]) = {
    println("Start seq")
    els.tail.foldLeft(Future(els.head()))((f, x) => f.flatMap(_ => Future(x())))
  }

  val res: Future[Int] = futSeq(Seq(
    () => {println(" Call wPrint");1},
    () => {println(" Call wPrint");1 + 1},
    () => {println(" Call wPrint");1 + 1 + 1},
    () => {println(" Call wPrint");1 + 1 + 1 + 1},
    () => {println(" Call wPrint");1 + 1 + 1 + 1 + 1},
  ))

  //res.foreach(res => println(s"res $res"))

  //def sleeper = Future{Thread.sleep(8000); println("done")}
  Await.ready(res, 10 seconds).foreach(r => println(s"done $r"))

}
