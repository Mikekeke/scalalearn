import scala.collection.immutable.Seq
val l = List("12:33-12:34","15:33-18:04")
def strToMins(s: String) = {
  val arr = s.split(":")
  arr.map(_.toInt).reduceRight((h, m) => h * 60 + m)
}
val arrays =
  l.map(_.split("-").map(strToMins).reduceRight((l, r) => (r - l).ensuring(r > l)))



val test1 = strToMins("11:40").ensuring(_.isInstanceOf[Int])


