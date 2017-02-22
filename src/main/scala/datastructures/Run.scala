package datastructures

/**
  * Created by ibes on 21.02.17.
  */
object Run {

  def main(args: Array[String]): Unit = {

    val x = List(1,2,3,4,5)
    println(s"Length: ${List.length(x)}")
    val dropped = List.drop(x, 5)
    println(s"drop: $dropped")
    val toDrop = List(1,2,3,4,5,3)
    println(s"dropWile: ${List.dropWhile(toDrop, (x: Int) => x < 4)}")
    println(s"dropWile2: ${List.dropWhile2(toDrop)(x => x > 3)}")

    val testFold1 = List.foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_))
    println(s"testFold1: $testFold1")

  }
}
