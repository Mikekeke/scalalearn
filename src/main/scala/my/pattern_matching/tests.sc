import scala.collection.immutable.::

case class Player(name: String, score: Int)

val p1 = Player("Bob", 345)

p1 match {
  case Player(_, score) => println(s"score $score")
  case Player(name, _) => println(s"Hi $name")
}

//sequence unpacking
val list = List(1, 2, 3, 4, 5)

val List(first_1, _, third_1, _*) = list
println(s"first = $first_1, third = $third_1")

val first_2 :: _ :: third_2 :: _ = list
println(s"first = $first_2, third = $third_2")
//sequence unpacking - END


// seq destruction in for comprehension
// http://danielwestheide.com/blog/2012/12/05/the-neophytes-guide-to-scala-part-3-patterns-everywhere.html
def gameResults(): Seq[(String, Int)] =
("Daniel", 3500) :: ("Melissa", 13000) :: ("John", 7000) :: Nil

def hallOfFame1 = for {
  result <- gameResults()
  (name, score) = result
  if score > 5000
} yield name
println(hallOfFame1.mkString(" - "))

//shorter way
def hallOfFame2 = for {
  (name, score) <- gameResults()
  if score > 8000
} yield name
println(hallOfFame2.mkString(" - "))

val lists = List(1, 2, 3) :: List.empty :: List(5, 3) :: Nil

val sugared = for {
  list @ head :: _ <- lists
} yield list.size

val desugared = lists.withFilter(_ match {
  case head :: _ => true
  case _ => false
}) map (_.size)


val withAnonCase = lists.filter {
  case List(1, _*) => true
  case List(h, _*) => h == 5
  case _ => false
}

// Partial function(short "case" syntax)
val pf: PartialFunction[(String, Int), String] = {
  case (word, freq) if freq > 3 && freq < 25 => word
}

val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) ::
  ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil

val collected =  wordFrequencies.collect(pf)

//val mapped = wordFrequencies.map(pf) // error

val lst = List("one", "two")
val  lifted1 = lst.lift(1).map(_ + 10).getOrElse(11)
val  lifted2 = lst.lift(4).map(_ + 10).getOrElse(11)
