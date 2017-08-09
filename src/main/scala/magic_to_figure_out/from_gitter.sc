trait Writes[T] {
  def foo: Unit = {}
}
object Writes {
  implicit def int: Writes[Int] = new Writes[Int]{}
  implicit def responseExportWrites[T: Writes]: Writes[ResponseExport[T]] = new Writes[ResponseExport[T]] {}
}
case class ResponseExport[T]()

implicit class BetterWrites[T: Writes](b: T) {
  def bar: Unit = {}
}

val a = ResponseExport[Int]()
val b = a.bar