import streams.MyStream

val str = MyStream(1,2,3,1,3,4,5,7)
val ones: MyStream[Int] = MyStream.constant(6)
ones take 5 toList










