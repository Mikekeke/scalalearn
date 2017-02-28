package my.pattern_matching

trait Fruit {
  def name: String
}
case class Apple(name: String) extends Fruit
case class Orange(name: String) extends Fruit
object appleLil {
  def unapply(arg: Fruit): Boolean = arg.name == "Lil"
}
