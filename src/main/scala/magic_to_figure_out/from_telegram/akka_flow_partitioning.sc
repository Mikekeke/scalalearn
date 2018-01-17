//  val apiRequestFlow: Flow[ApiRequest, SerializationResult, NotUsed] =
//    Flow.fromGraph(GraphDSL.create() { implicit b =>
//      import GraphDSL.Implicits._
//
//      val partition = b.add(Partition[ApiRequest](2, { case _: Command => 0; case _: Query => 1 }))
//      val merge = b.add(Merge[SerializationResult](2))
//
//      partition.out(0) ~> Flow[ApiRequest].collect({ case command: Command => command }) ~> commandFlow ~> merge.in(0)
//      partition.out(1) ~> Flow[ApiRequest].collect({ case query: Query => query }) ~> queryFlow ~> merge.in(1)
//
//      FlowShape(partition.in, merge.out)
//    })