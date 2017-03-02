package my

import scala.util.{Failure, Success, Try}

/**
  * Created by ibes on 02.03.17.
  */
object TimeCounter {
  val helpCommand = Array("-h", "-help")

  def main(args: Array[String]): Unit = {
//    require(args.nonEmpty, "Type the time. Pls. -h for help")
//    println(args.mkString(" and "))

        val times = "11:20-12:54,13:00-14:10"
//    val times = args(0)

    if (helpCommand contains times) {
      println("Type time in format hh:mm-hh-mm, comma separated.  -h for help")
      return
    }

    countTime(times) match {
      case Success(time) =>
        println(s"Total time: $time")
      case Failure(e) =>
        System.err.println(s"Error parsing $times")
        e.printStackTrace()
    }
  }

  private def countTime(timeString: String) = {
    // Converts string if hh:mm to Int minutes
    def strToMins(s: String) = s.split(":").map(_.toInt).reduceRight((h, m) => h * 60 + m)

    def format(mins: Int) = {
      def go(m: Int, h: Int): (Int, Int) = if (m < 60) (h, m) else go(m - 60, h + 1)
      val hm = go(mins, 0)
      s"${hm._1}:${hm._2}"
    }

    Try {
      val timesArray: Array[String] = timeString.replaceAll(" ", "").split(",").ensuring(_.nonEmpty, "No values to parse")
      val arrayOfMinutes =
        timesArray.map(str => str.split("-")
          .map(strToMins).ensuring(minsArr => minsArr(0) < minsArr(1), s"Probably u set time wrong here: $str")
          .reduceRight((l, r) => (r - l).ensuring(r > l, "")))
      val totalMins = arrayOfMinutes.sum
      format(totalMins)
    }
  }
}
