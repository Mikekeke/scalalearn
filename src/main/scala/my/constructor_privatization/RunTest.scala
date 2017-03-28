package my.constructor_privatization

/**
  * Created by ibes on 16.03.17.
  */
object RunTest extends App {
  //no reaction,since it has auto generated companion with apply()
  val n1 = CasePrivate(1, "dsd")
  println(n1)

  // instance created by companion object
  val n2 = RegularPrivate(2)
  println(n2)

  // instance created by non-private constructor
  val n3 = new RegularPrivate(3, "Made by non-private", " constructor")
  println(n3)

  // ain't work coz default constructor is private
//  val nErr = new RegularPrivate(3, "test")
//  println(nErr)
val cleanStringWith3: String => (String => String) = s => fun => fun()

}
