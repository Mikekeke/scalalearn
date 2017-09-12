package my

/*
 Some !good! examples:
 https://blog.scalac.io/2017/04/19/typeclasses-in-scala.html
 */

import annotation.implicitNotFound
@implicitNotFound("CUSTOM no member of type class NumberLike in scope for ${T}")
trait Concat[T] {
  def makeTogether(a: T, b:T): String
}

object Concat {

  def apply[T](implicit cc: Concat[T]): Concat[T] = cc

  implicit object ConcatInt extends Concat[Int] {
    override def makeTogether(a: Int, b: Int): String = (a * 10 + b).toString
  }

  implicit object ConcatString extends Concat[String] {
    override def makeTogether(a: String, b: String): String = a + b
  }
}


object TypeClasses extends App{
  import Concat._

//  full variant
//  implicit val concatBoolean: Concat[Boolean] =
//    new Concat[Boolean] {
//      override def makeTogether(a: Boolean, b: Boolean): String = s"${a.toString}&${b.toString}"
//    }

  // idea told u can do that ^ like this
  implicit val concatBoolean: Concat[Boolean] =
    (a: Boolean, b: Boolean) => s"${a.toString}&${b.toString}"

  // full
  def concat0[T](a: T, b: T)(implicit  cc: Concat[T]): String = cc.makeTogether(a,b)

  // shorter
  def concat1[T: Concat](a: T, b: T): String = implicitly[Concat[T]].makeTogether(a,b)

  // shortest thanks to Concat.apply()
  def concat2[T: Concat](a: T, b: T): String = Concat[T].makeTogether(a,b)

  println(concat0(1,2))
  println(concat1("a","b"))
  println(concat2(true,false))
}
