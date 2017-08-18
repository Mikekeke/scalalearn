package my

import java.awt.Dimension
import java.awt.event.{MouseAdapter, MouseEvent}
import javax.swing.JFrame

import akka.actor.{ActorRef, ActorSystem}
import akka.stream.scaladsl.{Flow, Keep, RunnableGraph, Sink, Source, SourceQueue}
import akka.stream.{ActorMaterializer, OverflowStrategy}
import akka.{Done, NotUsed}

import scala.concurrent.Future
import scala.util.Try

object StreamsTest extends App {
  implicit val system = ActorSystem("TestSystem")
  implicit val materializer = ActorMaterializer()
  implicit val ctx = system.dispatcher


  val qu: Source[String, SourceQueue[String]] = Source.queue[String](bufferSize = 100, OverflowStrategy.backpressure)

  val intFlow = Flow[String].map(x => Try(x.toInt))

  val sink: Sink[Any, Future[Done]] = Sink.foreach(println)

  val subm = qu via intFlow to sink run()
  subm.offer("4")
  subm.offer("f")

  val graph1: SourceQueue[String] = qu.via(intFlow).to(sink) run()
  val graph: RunnableGraph[(SourceQueue[String], Future[Done])] = qu.via(intFlow).toMat(sink)(Keep.both)
  val (queue, future1) = graph run()
  queue.offer("f")
  queue.offer("4")

  val tst: RunnableGraph[(NotUsed, Future[Done])] = Source(1 to 3).toMat(sink)(Keep.both)
  val (nu, future2) = tst.run()
  future2.onComplete(_ => println("dode"))

  def mkClickFrame(ref: ActorRef): Unit = {
    new JFrame("Click Stream Example") {
      setPreferredSize(new Dimension(300, 300))
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
      addMouseListener(new MouseAdapter {
        override def mouseClicked(e: MouseEvent) =
          ref ! e
      })
      pack()
      setVisible(true)
    }
  }

  //
  val clickStream: Source[MouseEvent, Unit] = Source
    .actorRef[MouseEvent](bufferSize = 0, OverflowStrategy.fail)
    .mapMaterializedValue(mkClickFrame)

}


//  def mkClickFrame(ref: ActorRef): Unit = {
//  new JFrame("Click Stream Example") {
//  setPreferredSize(new Dimension(300, 300))
//  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
//  addMouseListener(new MouseAdapter {
//  override def mouseClicked(e: MouseEvent) =
//  ref ! e
//  })
//  pack()
//  setVisible(true)
//  }
//  }
//
//  implicit val system = ActorSystem("TestSystem")
//  implicit val materializer = ActorMaterializer()
//
//  val clickStream: Source[MouseEvent, Unit] = Source
//  .actorRef[MouseEvent](bufferSize = 0, OverflowStrategy.fail)
//  .mapMaterializedValue(mkClickFrame)
