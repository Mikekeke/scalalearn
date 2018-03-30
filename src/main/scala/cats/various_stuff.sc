import cats.syntax.apply._
import cats.instances.option._

// from telegram
val x = Option(1)
val y = Option("lol")
val z = Option(2.3)

final case class Lol(x: Int, y: String, z: Double)

(x, y, z).mapN(Lol)