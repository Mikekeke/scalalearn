package my.my_akka

import java.util.Calendar
import java.util.concurrent.Executors

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.{ExecutionContext, Future}

case object Run

trait Blocking {
  def block(name: String) = {
    Thread.sleep(3000)
    println(s"$name - Block done")
  }
}

class BlockedActor extends Actor with Blocking {
  val name = this.getClass.getSimpleName

  override def receive = {
    case r@Run =>
      println(s"$name - $r at ${Calendar.getInstance().getTime}")
      block(name)
  }
}

class NonBlockedActor extends Actor with Blocking {
  val name = this.getClass.getSimpleName
  implicit val futuresContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(4))

  override def receive = {
    case r@Run =>
      println(s"$name - $r at ${Calendar.getInstance().getTime}")
      Future(block(name))
  }
}

object AkkaActorsTests extends App {
  val system = ActorSystem("test")

  val blocked = system.actorOf(Props[BlockedActor])
  val nonBlocked = system.actorOf(Props[NonBlockedActor])

  blocked ! Run
  blocked ! Run
  nonBlocked ! Run
  nonBlocked ! Run
}
