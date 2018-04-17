import scala.reflect.ClassTag
trait Smth
case class Aa() extends Smth
case class Bb() extends Smth

def trie[T : ClassTag](x: Smth): String = x match {
  case _: T => "true"
  case _ => "false"
}

trie[Aa](Aa())
trie[Aa](Bb())

// ********************************************

val plus1: (Int, Int) => Int = _+_
val plus2: Int => Int => Int = x => x + _
