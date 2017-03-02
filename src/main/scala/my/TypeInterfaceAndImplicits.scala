package my

/**
  * Created by ibes on 02.03.17.
  */
object TypeInterfaceAndImplicits {
  def main(args: Array[String]): Unit = {
    implicit def fr2effer(f: Fr): Effer = Effer(s"${f.n} und ${f.f}")

    case class Fr(n: Int, f: Int)

    case class Effer(s: String) {
      override def toString: String = s"From Effer $s"
    }

    val fr = Fr(3, 4)
    println(s"as Fr: $fr")
    println(s"as Effer: ${fr: Effer}")
  }

}
