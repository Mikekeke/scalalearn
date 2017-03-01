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
      var nt: Unit => String = Unit => "sd"
    }
    val f1 = new Fr
    //prints variable
    println(f1.nt)
    //calls function
    println(f1.nt())

    //reassigning var
    val f = new Fr{
      nt = _ => "fffff"
    }
    println(f.nt)
    println(f.nt())
  }

}
