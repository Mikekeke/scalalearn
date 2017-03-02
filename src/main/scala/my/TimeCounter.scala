package my

import scala.util.{Failure, Success, Try}

/**
  * Created by ibes on 02.03.17.
  */
object TimeCounter {
  val helpCommand = Array("-h", "-help")

  def main(args: Array[String]): Unit = {
    require(args.nonEmpty, "Type the time. Pls. -h for help")
    println(args.mkString(" and "))

    //    val times = "11:20-12:54,13:00-14:10"
    val times = args(0)

    if (helpCommand contains times) {
      println("Type time in format hh:mm-hh-mm, comma separated.  -h for help")
      return
    }

    countTime(times)

    def countTime(timeString: String) = {
      // ain't work with implicit for some reason (shows $%#@$#@-like error)
      // implicit val formatTuple: String => Int = _.split(":").map(_.toInt).reduceRight((h:Int, m:Int) => h * 60 + m)
      val formatTuple: String => Int = _.split(":").map(_.toInt).reduceRight((h: Int, m: Int) => h * 60 + m)

      def untuple(t: (String, String))(implicit f: String => Int) =
        (f(t._2) - f(t._1)).ensuring(_ >= 0, s"Some negative time u put: ${t._1} - ${t._2}")

      def format(mins: Int) = {
        def go(m: Int, h: Int): (Int, Int) = if (m < 60) (h, m) else go(m - 60, h + 1)

        val hm = go(mins, 0)
        s"${hm._1}:${hm._2}"
      }

      Try{
        val arr = timeString.replaceAll(" ","").split(",").ensuring(_.nonEmpty, "Arguments can't be parsed")
        val totalMins = arr.map(_.split("-")).map(arr => (arr(0), arr(1))).map(untuple(_)(formatTuple)).sum
        format(totalMins)
      } match {
        case Success(time) => println(s"Total time: $times}")
        case Failure(e) => sys.error("Error parsing")
      }
    }
  }
}
