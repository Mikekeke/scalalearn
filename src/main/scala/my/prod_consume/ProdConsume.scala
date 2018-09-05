package my.prod_consume

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import my.prod_consume.Actors.Supervisor
import my.prod_consume.SupervisorCommands.Start

object ProdConsume {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("prodcons")
    implicit val materializer = ActorMaterializer()
    val sup = system.actorOf(Supervisor.props(5,2))
    sup ! Start
  }
}
