package my

object MOrdering extends App{

  sealed abstract class Log(val code: Int)
  case object Error extends Log(3)
  case object Warn extends Log(2)
  case object Info extends Log(1)

  implicit val logOrd: Ordering[Log] = (x: Log, y: Log) => x.code - y.code

  // need to specify l:List[Log] or l.sorted[Log] to make it see implicit
  val l: List[Log] = List(Warn, Info, Error)
  println(l.sorted[Log])
}
