package datastructures

/**
  * Created by ibes on 21.02.17.
  */
object Run {

  def main(args: Array[String]): Unit = {

    val x = List(1,2,3,4,5)
    val x2 = List(4,5,1,2)
    List.reverse(x)
    val twoLists = List.appendViaFoldRight(x, x2)
    println(s"Length: ${List.length(x)}")
    val dropped = List.drop(x, 5)
    println(s"drop: $dropped")
    val toDrop = List(1,2,3,4,5,3)
    println(s"dropWile: ${List.dropWhile(toDrop, (x: Int) => x < 4)}")
    println(s"dropWile2: ${List.dropWhile2(toDrop)(x => x > 3)}")

    val foldedRight = List.foldRight(List(1,2,3), 0)((s1, s2) => s1 + s2)
    val foldedLeft = List.foldLeft(List(1,2,3), 0)((s1, s2) => s1 + s2)
    println(s"Fold left: $foldedRight")
    println(s"Fold right:  $foldedLeft")

    println(s"Transformed: ${List.add1(x)}")

    val doubles: List[String] = List.double2string(List(2.1, 1.4, 8.8))
    println(s"Doubles to string: $doubles")

    val mapped = List.map(x)(_ + 10)
    println(s"Mapped: $mapped")
  }
}
