val times = List(
  "11:20-12:54",
  "13:00-14:10",
  "14:50-16:30",
  "16:36-18:08",
  "10:30-11:50",
  "11:50-12:33",
  "12:33-12:59",
  "12:59-14:00",
  "14:42-14:56",
  "14:57-17:50",
  "10:45-11:05"
)
def toMins(l: List[String]) = {
  implicit val formattedTimeToMins: String => Int = s => s.split(":").map(_.toInt).reduceRight((h:Int, m:Int) => h * 60 + m)
  def untuple(t:(String, String))(implicit f: String => Int) = f(t._2) - f(t._1)

  val totalMins = l.map(_.split("-")).map(arr => (arr(0), arr(1))).map(untuple).sum

  def format(mins: Int) = {
    def go(m: Int, h: Int): (Int, Int) = if (m < 60) (h, m) else go(m - 60, h + 1)
    val hm = go(mins, 0)
    s"${hm._1}:${hm._2}"
  }

  format(totalMins)

}

val res = toMins(times)



