val test = "1 1 3 4 + 2 *".split(" ")

def foldingFunction (stack: List[Double], a: String): List[Double] = stack match {
  case List() => a.toDouble :: stack  // or Nil, if you prefer
  case List(_) => a.toDouble :: stack
  case l: List[Double] if l.size >=2 => println(stack); a match {
    case "*" => l.product:: Nil
    case "+" => l.sum :: Nil
    case "-" => l.reduce(_-_) :: Nil
    case "/" =>l.reduce(_/_) :: Nil
    case s: String => s.toDouble :: stack
  }
}

test.foldLeft(List[Double]())(foldingFunction).head