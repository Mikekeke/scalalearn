implicit val intToS: Int => String = x => x toString

implicit def intToS(x: Int): String = x toString
