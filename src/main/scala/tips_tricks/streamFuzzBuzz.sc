Stream.continually(Stream.fill(2)(None) :+ Some("Fizz")).flatten
  .zip(Stream.continually(Stream.fill(4)(None) :+ Some("Buzz")).flatten)
  .zipWithIndex
  .map { case ((fo, bo), i) => (for(f <- fo; b <- bo) yield s"$f $b")
    .orElse(fo).orElse(bo).getOrElse((i + 1).toString)
  }.take(100).foreach(println)