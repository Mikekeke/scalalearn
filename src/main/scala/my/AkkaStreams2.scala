package my

import akka.actor.{ActorRef, ActorSystem}
import akka.stream.scaladsl.{Balance, Flow, GraphDSL, Merge, Sink, Source}
import akka.stream.{ActorMaterializer, FlowShape, OverflowStrategy}
import akka.{Done, NotUsed}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions
import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.{JFXApp, Platform}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextArea}
import scalafx.scene.layout.VBox


object AkkaStreams2 extends JFXApp {
  implicit val system = ActorSystem("test")
  implicit val materializer = ActorMaterializer()

  val inTextArea = new TextArea()

  val outTextArea = new TextArea()

  def clearIO(): Unit = {
    inTextArea.text = ""
    outTextArea.text = ""
  }

  def button[T](text: String)(action: => T) = new Button(text) {
    onAction = handle {
      action
    }
  }

  // TODO: submit on Enter
  def mkActorListener[T](txtArea: TextArea, actorRef: ActorRef) = {
    txtArea.textProperty().addListener(obs => if (txtArea.getText.nonEmpty) actorRef ! txtArea.text.value)
  }

  val source = Source
    .actorRef(bufferSize = 100, overflowStrategy = OverflowStrategy.dropTail)
    .mapMaterializedValue(ref => mkActorListener(inTextArea, ref))

  val sink: Sink[String, Future[Done]] = Sink.foreachParallel(3)(v => Platform.runLater {
    outTextArea.text.value = outTextArea.delegate.getText + s"$v\n"
  })

  val longComputation = Flow[String].map(s => {
    val r = new scala.util.Random()
    println("Flow thread: " + Thread.currentThread().getName)
    Thread.sleep(r.nextInt(3000) + 500)
    "Computed: " + s
  })

  def balancer[In, Out](worker: Flow[In, Out, Any], workerCount: Int): Flow[In, Out, NotUsed] = {
    import akka.stream.scaladsl.GraphDSL.Implicits._

    Flow.fromGraph(GraphDSL.create() { implicit b =>
      val balancer = b.add(Balance[In](workerCount, waitForAllDownstreams = true))
      val merge = b.add(Merge[Out](workerCount))

      (1 to workerCount).map(_ => balancer ~> worker.async ~> merge)

      FlowShape(balancer.in, merge.out)
    })
  }

  //  source via longComputation to sink run() // sequential - flow sequential by default
  source via balancer(longComputation, 4) to sink run() // parallel

  //  Source(1 to 10).map(_.toString) via balancer(longComputation, 4) to sink run() // test


  stage = new PrimaryStage {
    scene = new Scene(400, 300) {
      title = "Streams test"
      root = new VBox {
        spacing = 6
        padding = Insets(10)
        alignment = Pos.Center
        children = Seq(
          button("Clear")(clearIO()),
          inTextArea,
          outTextArea
        )
      }
    }
  }
}
