//https://stackoverflow.com/questions/8524878/implicit-conversion-vs-type-class

trait Default[T] { def value : T }

implicit object DefaultInt extends Default[Int] {
  def value = 42
}

implicit def listsHaveDefault[T : Default] = new Default[List[T]] {
  def value = implicitly[Default[T]].value :: Nil
}

def default[T : Default] = implicitly[Default[T]].value

default[List[List[Int]]]
default[List[Int]]
default[Int]

