case class Fr(s: String*)

println(Fr("one", "drfr"))

// class with partner obj
class FrFe(seq: Seq[String]) {
  override def toString: String = seq.mkString("-")
}

object FrFe {
  def apply(seq: String*): FrFe = new FrFe(seq)
}
// class with partner obj - END

val t = new FrFe(Seq("1", "2"))
val t2 = FrFe("a", "b")

println(t)
println(t2)

//usage of mutator
class Dee {
  private var f1 = ""
  def f: String = f1
  def f_=(value:String): Unit = f1 = value

  override def toString: String = s"Class Dee: value of private f = $f1"
}

val t4 = new Dee {
  f = "d"
}




