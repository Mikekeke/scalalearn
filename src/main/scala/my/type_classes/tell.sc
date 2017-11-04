trait TellType[T] {
  def tell(v: T): String
}

object TellType {
  def apply[T](implicit tt: TellType[T]) = tt
}

implicit val tellInt: TellType[Int] =
  (_: Int) => "Integer"

implicit val tellFloat: TellType[Float] =
  (_: Float) => "Float"

implicit val tellBool: TellType[Boolean] =
  (_: Boolean) => "Boolean"

def tellType[T:TellType](v: T) = TellType[T].tell(v)

tellType(4)
tellType(true)
tellType(4: Float)