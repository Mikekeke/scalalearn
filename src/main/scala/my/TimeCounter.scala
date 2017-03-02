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
    def strToMins(s: String) = s.split(":").map(_.toInt).reduceRight((h, m) => h * 60 + m)

    def format(mins: Int) = {
      def go(m: Int, h: Int): (Int, Int) = if (m < 60) (h, m) else go(m - 60, h + 1)
      val hm = go(mins, 0)
      s"${hm._1}:${hm._2}"
    }

    Try {
      val timesArray = timeString.replaceAll(" ", "").split(",").ensuring(_.nonEmpty, "Arguments can't be parsed")
      val arrayOfMinutes = timesArray.map(_.split("-").map(strToMins).reduceRight((l, r) => (r - l).ensuring(r > l)))
      val totalMins = arrayOfMinutes.sum
      format(totalMins)
    }
  }
}
