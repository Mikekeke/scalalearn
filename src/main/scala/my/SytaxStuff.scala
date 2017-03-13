package my

/**
  * Created by Mikekeke on 01-Mar-17.
  */
object SyntaxStuff {
  def main(args: Array[String]): Unit = {
    val pr = (x: Int) => x + 1

    println("1 = " + pr(1))
    println("2 = " + pr(2))

    class Fr{
      // function assigned to var
      var nt: Unit => String = Unit => "initial"
    }
    val f1 = new Fr
    //prints variable
    println(f1.nt)
    //calls function
    println(f1.nt())

    //reassigning var
    val f = new Fr{
      nt = _ => "reassigned"
    }
    println(f.nt)
    println(f.nt())

    //same result
    var userDir1: Unit => Long = Unit => { System.currentTimeMillis() }
//    var userDir2: Unit => Long = _ => { System.currentTimeMillis() } - doesn't work
    var userDir3 = () => { System.currentTimeMillis() }
    println(userDir1)
    println(userDir1())
  }

}
