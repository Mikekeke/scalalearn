def add(x: Int) = Some(x + 10)
val opt = Some(1)

val de = for {
  x <- opt
  x2 <- add(x)
  // this assignment causes to call additional map()
  d = x + x2
} yield {println(s"x = $x, x2 = $x2, d = $d");x + x2 + d}

def gameResults(): Seq[(String, Int)] =
  ("Daniel", 3500) :: ("Melissa", 13000) :: ("John", 7000) :: Nil

def hallOfFame = for {
  result <- gameResults()
  (name, score) = result
  if score > 5000
} yield name
hallOfFame

List(1,2,3,4).grouped(3).next()
