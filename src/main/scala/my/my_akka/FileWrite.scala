package my.my_akka

import java.nio.file.Paths

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, IOResult}
import akka.stream.scaladsl.{FileIO, Flow, Keep, Sink, Source}
import akka.util.ByteString

import scala.concurrent.Future

object FileWrite extends App{

  implicit val system = ActorSystem("TestSystem")
  implicit val materializer = ActorMaterializer()
  implicit val ctx = system.dispatcher

  lazy val src1 = Stream.from(1)

  val source: Source[Int, NotUsed] = Source(src1.take(20))
  val futureFlow: Flow[Int, String, NotUsed] = Flow[Int].map(x => x.toString)
  val sink = Sink.foreach(println)

  def fileSink(fileName: String): Sink[String, NotUsed] =
    Flow[String]
      .map(s => ByteString(s + "\n"))
      .to(FileIO.toPath(Paths.get(fileName)))

  def fileSink_2(fileName: String): Sink[String, Future[IOResult]] =
    Flow[String]
      .map(s => ByteString(s + "\n"))
      .toMat(FileIO.toPath(Paths.get(fileName)))(Keep.right)


//  source via futureFlow runWith fileSink("akka_write.txt") // can't "onComplete"
  source via futureFlow runWith fileSink_2 ("akka_write.txt") onComplete(_ => system.terminate())

}
