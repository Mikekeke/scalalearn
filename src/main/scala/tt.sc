import scala.util.Try

Option(null)

def findFun(caretPos: Int)(line: String)(funSplitter: Char): Option[String] = {
  if (line.isEmpty) return None

  val fromCaret = line.substring(caretPos, line.length)
  val findSplitterToRight = {
    val idx = fromCaret.indexOf(funSplitter)
    if (idx != -1) Some(idx+caretPos) else None
  }

  val tillCaret = line.substring(0, caretPos)
  def  findSplitterToLeft = {
    def go(idx: Int): Int =
      if (idx < 0) idx
      else if (tillCaret(idx) == funSplitter) idx else go(idx - 1)
    val res = go(tillCaret.length-1)
    if (res!= -1) Some(res) else None
  }

  val result = line.substring(
    findSplitterToLeft.map(1+).getOrElse(0),
    findSplitterToRight.getOrElse(line.length)
  ).trim

  if (result.isEmpty) None else Some(result)
}

val fs = List(
  "fff;3333;fdf" -> 3,
  "fff;3333;fdf" -> 5,
  "3333;fdf" -> 3,
  "3333" -> 3,
  "" -> 3,
  "      " -> 3,
) map {case (line, pos) => Try(findFun(pos)(line)(';'))}
fs.zipWithIndex.map(_.swap) foreach println

List.empty[Int].dropWhile(4>)



