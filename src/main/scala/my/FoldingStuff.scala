package my

/**
  * Created by ibes on 29.03.17.
  */
object FoldingStuff extends App {

  val test2 = "1 2 3 4 5"
  test2.foldLeft("-start-")(_+_)
  test2.foldRight("-start-")(_+_)

  test2.foldLeft(List[String]())(foldingFun)

  def foldingFun(list: List[String], s: Char) =
    if(list.isEmpty) List(s.toString)
    else list.head + s + "-1-" :: list.tail

}
