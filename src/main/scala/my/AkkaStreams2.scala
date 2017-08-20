package my

import javafx.beans.{InvalidationListener, Observable}
import javafx.scene.control.TextField
import javax.swing.event.{ChangeEvent, ChangeListener}

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.StringProperty
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.{Scene, layout}
import scalafx.scene.control.{Button, Label, TextArea, TextInputControl}
import scalafx.scene.layout.{BorderPane, VBox}


class TestA extends Actor {
  override def receive = {
    case any => println(any)
  }
}

trait ImplConversions {
  implicit def funToInvalidationListener(f: Observable => Unit) =
    new InvalidationListener {
      override def invalidated(observable: Observable): Unit = {
        println('invalidaying)
        f(observable)
      }
    }
}

object AkkaStreams2 extends JFXApp with ImplConversions{
  val system = ActorSystem("test")
  val ref = system.actorOf(Props[TestA])

  def mkActorListener[T](actorRef: ActorRef)(s: => T): Observable => Unit =
    (_: Observable) => actorRef ! s

  val inTextArea = new TextArea {
    text.delegate.addListener(mkActorListener(ref)(text.value))
  }

  val outTextArea = new TextArea()

  stage = new PrimaryStage {
    scene = new Scene(400, 300) {
      title = "Streams test"
      root = new VBox {
        spacing = 6
        padding = Insets(10)
        alignment = Pos.Center
        children = Seq(
          new Button("Test"),
          inTextArea,
          outTextArea
        )
      }
    }
  }
}
