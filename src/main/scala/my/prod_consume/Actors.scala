package my.prod_consume

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import java.util.UUID.randomUUID

import my.prod_consume.ProducerCommands.NewData

import scala.concurrent.duration._
import scala.collection.immutable.Queue

object ProducerCommands {
  case object Run
  case class NewData(data: Data)
}

object SupervisorCommands {
  case object Start
}

object ConsumerCommands {
  case object StartC
  case object AskJob
  case object ShutDown
  case class Job(data: Data)
}

case class Data private(id: String = randomUUID.toString)


object Actors {

  class Supervisor(topTrashHold: Int, bottomTrashHold: Int) extends Actor {
    var jobs = Queue[Data]()
    import SupervisorCommands._
    import ProducerCommands._
    import ConsumerCommands._

    var consumers = List.empty[ActorRef]

    override def receive: Receive = {
      case Start =>
        val prod = context.system.actorOf(Producer.props(self))
        prod ! Run
      case NewData(data) =>
        jobs = jobs :+ data
        println(s"Jobs: ${jobs.length}")
        if (consumers.isEmpty) {
          print("Starting init consumer")
          val newWorker = context.system.actorOf(Consumer.props(self, 7))
          consumers = consumers :+ newWorker
          newWorker ! StartC
        }

        if (jobs.length > topTrashHold) {
          print("Starting additional consumer")
          val additionalWorker = context.system.actorOf(Consumer.props(self, 7))
          consumers = consumers :+ additionalWorker
          additionalWorker ! StartC
        }
        println(s"consumers cnt = ${consumers.length}")

      case AskJob =>
        val sndr = sender()
        if (jobs.length <= bottomTrashHold && consumers.length >= 2) {
          consumers = consumers.filterNot(_ == sndr)
          sndr ! ShutDown
        } else if (jobs.isEmpty) {
          consumers = consumers.filterNot(_ == sndr)
          sndr ! ShutDown
        } else {
          val (data, newJobs) = jobs.dequeue
          jobs = newJobs
          sndr ! Job(data)
        }
    }
  }

  object Supervisor {
    def props(topTrashHold: Int, bottomTrashHold: Int) =
      Props(new Supervisor(topTrashHold, bottomTrashHold))
  }

  class Producer(supervisor: ActorRef) extends Actor {
    import ProducerCommands._
    import context.dispatcher

    def goProduce() = {
      context.system.scheduler.schedule(0 seconds, 2 seconds, supervisor, NewData(Data()))

    }
    override def receive: Receive = {
      case Run => goProduce()
    }
  }

  object Producer {
    def props(supervisor: ActorRef) = Props(new Producer(supervisor))
  }


  class Consumer(supervisor: ActorRef, rate: Int) extends Actor {
    import ConsumerCommands._
    import context.dispatcher

//    def startConsuming(): Unit =
//      context.system.scheduler.schedule(0 seconds, rate seconds, supervisor, AskJob)

    override def receive: Receive = {
      case StartC => sender() ! AskJob
      case Job(data) =>
        val sndr = sender()
        Thread.sleep(rate*1000)
        println(s"Worker: ${self.toString()}\n  processed job: ${data.id}")
        sndr ! AskJob
      case ShutDown =>
        println(s"id:${self.toString()} shutting down")
        self ! PoisonPill
    }
  }

  object Consumer {
    def props(supervisor: ActorRef, rate: Int) = {
      val rate = (Math.random() * 10).toInt
      println(s"  rate = $rate")
      Props(new Consumer(supervisor, rate))
    }
  }
}
