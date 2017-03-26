import java.net.URL

val Decimal = """(-)?(\d+)(\.\d*)?""".r

val Decimal(sign, integerpart, decimalpart) = "1.0"
println(integerpart)

case class Tester(n:Int, s: String)
val tester1 = Tester(1, "test")
println(tester1)

object extr {
  def unapply(arg: Tester): Option[String] = Some(arg.n + " and " + arg.s)
}

val extr(str) = tester1
println(s"res: $str")

val url = new URL("http://stackoverflow.com/questions/27591709/scala-string-to-url")
