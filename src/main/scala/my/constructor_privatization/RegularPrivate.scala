package my.constructor_privatization

class RegularPrivate private (num: Int, string: String) {
  def this(n: Int, s1: String, s2: String) = this(n, s1 + s2)
  override def toString: String = s"${this.getClass.getSimpleName}: num = $num, string = $string"
}
object RegularPrivate {
  def apply(n: Int): RegularPrivate = new RegularPrivate(n, "Made by companion's apply()")
}
