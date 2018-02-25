package my.my_akka

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{MergeHub, RunnableGraph, Sink, Source}

object Mhub extends App {
  implicit val system = ActorSystem("mhub")
  implicit val materializer = ActorMaterializer()
  // A simple consumer that will print to the console for now
  val consumer = Sink.foreach(println)

  // Attach a MergeHub Source to the consumer. This will materialize to a
  // corresponding Sink.
  val runnableGraph: RunnableGraph[Sink[String, NotUsed]] =
  MergeHub.source[String](perProducerBufferSize = 16).to(consumer)

  // By running/materializing the consumer we get back a Sink, and hence
  // now have access to feed elements into it. This Sink can be materialized
  // any number of times, and every element that enters the Sink will
  // be consumed by our consumer.
  val toConsumer: Sink[String, NotUsed] = runnableGraph.run()

  //TODO check allocations in some profiler for hub and materializing twice
  // Feeding two independent sources into the hub.
  val mr1 = Source.single("Hello!").runWith(toConsumer)
  val mr2 = Source.single("Hub!").runWith(toConsumer)
  println(mr1)
  println(mr2)
  println(mr1 == mr2)

  val r1 = Source.single("Hello!").runWith(consumer)
  val r2 = Source.single("Hub!").runWith(consumer)

  Thread.sleep(2000)
  println(r1 == r2)
  println(r1)
  println(r2)

}
