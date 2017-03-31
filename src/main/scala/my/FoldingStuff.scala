package my

/**
  * Created by ibes on 29.03.17.
  */
object FoldingStuff extends App {

  val test2 = "1 2 3 4 5"
  test2.foldLeft("-start-")(_+_)
  test2.foldRight("-start-")(_+_)

  val test3 = "12345"
  val foldStarSpacer: (List[String], Char) => List[String] = foldingFun("-*-")

//  will throw NullPointer coz of 'val', which be evaluated only in string 27
//  compare to 'def foldingFun', that will be evaluated on call
//  val foldStarSpacer: (List[String], Char) => List[String] = foldingFun2("-*-")

  def foldingFun(spacer: String)(list: List[String], s: Char) =
    if(list.isEmpty) List(s.toString)
    else {
      val h = list.head
      val t = list.tail
      val res = h + s + spacer :: t
      res
  }

  val foldingFun2: (String) => (List[String], Char) => List[String] = (spacer: String) => (list: List[String], s: Char) =>
    if(list.isEmpty) List(s.toString)
    else {
      val h = list.head
      val t = list.tail
      val res = h + s + spacer :: t
      res
    }

  println(test3.foldLeft(List[String]())(foldStarSpacer))
}
