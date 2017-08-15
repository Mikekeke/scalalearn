package red_book.streams

/**
  * Created by ibes on 13.04.17.
  */
case class NotSoStream(num: Int)
case class SoSoStream(num: () => Int)

object SoSoStream {
  def cons(num: => Int): SoSoStream = SoSoStream(() => num)

  def lazyCons(num: => Int): SoSoStream = {
    lazy val lazyNum = num
    SoSoStream(() => lazyNum)
  }
}
