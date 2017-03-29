type Calc = String => Double
val test = "3 4 + 2 *".split(" ")

test.foldLeft(List[Double]())(foldingFunction).head

def foldingFunction (stack: List[Double], a: String): List[Double] = stack match {
  case List() => a.toDouble :: stack  // or Nil, if you prefer
  case List(_) => a.toDouble :: stack
  case x::y::ys => a match {
    case "*" => x * y :: ys
    case "+" => x + y :: ys
    case "-" => y - x :: ys
    case "/" => y / x :: ys
    case s: String => s.toDouble :: stack
  }
}
