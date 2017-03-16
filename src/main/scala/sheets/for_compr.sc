def add(x: Int) = Some(x + 10)
val opt = Some(1)

for {
  x <- opt
  x2 <- add(x)
  // this assignment causes to call additional map()
  d = x + x2
} yield x + x2 + d

opt.flatMap { x =>
  add(x).map { case x$1@(x2) =>
    val x$2@(d) = x + x2
    (x$2, x$1)
  }.map { case (d, x2) => x + x2 + d }
}