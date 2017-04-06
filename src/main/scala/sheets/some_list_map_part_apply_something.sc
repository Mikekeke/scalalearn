import scala.collection.immutable.ListMap
import scala.concurrent.Future
import scala.util.{Failure, Success}

val map1 = List("apple", "banana", "orange", "rizelighterin") groupBy(_.length)
val tosec = map1.toSeq
val sortedSeq = tosec.sortBy(_._1)
ListMap(sortedSeq:_*)
case class FromSeq[A](s: A*)
val seq = Seq(1,2,3)
FromSeq(seq)
FromSeq("test", "lol")


val tupleSeq: Seq[(Int, Int)] = Seq((1,2), (9, 90))
val sums = tupleSeq.map { case (i1, i2) => i1 + i2 }
val col = tupleSeq.collect { case (i1, i2) if i2 == 2 => i1 + i2}

val names = List("Bob", "Nick", "Kot")
val maybeHasName: String => Boolean = names contains _
maybeHasName("Bob")
val mbHasUnderIndex = names lift _
mbHasUnderIndex(3)
mbHasUnderIndex(0)
