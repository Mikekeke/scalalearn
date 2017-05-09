package my

object Emuming extends App {

  object WeekDay extends Enumeration {
    type WeekDay = Value
    val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
  }
  import WeekDay._

  def isWorkingDay(d: WeekDay) = ! (d == Sat || d == Sun)
  val isWeekend: WeekDay => Boolean = d =>  d == Sat || d == Sun
  WeekDay.values filter isWeekend foreach println
}
