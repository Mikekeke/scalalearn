trait Encoder[T] extends (T => String)

object Encoder {
  implicit val intEncoder: Encoder[Int] = x => s"Encoded Int: $x"
  implicit val stringEncoder: Encoder[String] = s => s"Encoded String: $s"
}

def showWEncode[T: Encoder](x: T) = implicitly[Encoder[T]].apply(x)

showWEncode("test")
showWEncode(5)