val str = "one.two"
str.split("\\.").lift(0).get
str.split("\\.").headOption.get
str.split("\\.").applyOrElse(0, str)

str.indexOf(".") match {
  case -1 => str
  case i: Int => str.substring(0, i)
}