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

def comp2l[T](l1: List[T], l2: List[T]): Either[String, List[Boolean]] = {
  def checkResult(l: List[Boolean]): Either[String, List[Boolean]] =
    if (l.tail.forall(l.head ==)) Right(l)
    else Left(s"Lists differ: $l")

  def zipWith(_l1:List[T], _l2:List[T])(p : T => T => Boolean) =
    _l1.zip(_l2).map {case (l,r) => p(l)(r)}

  l1.lengthCompare(l2.length) match {
    case 0 => Right(zipWith(l1, l2)(a => b => a == b)).flatMap(checkResult)
    case _ => Left("Lists lens differ")
  }
}

comp2l(List(1,2,3), List(1,2,3))
comp2l(List(1,2,3), List(1,4,3))
comp2l(List(1,2,3), List(2,3))




