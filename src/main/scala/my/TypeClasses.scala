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

//  def concat[T](a: T, b: T)(implicit  cc: Concat[T]): T = cc.makeTogether(a,b)
  def concat[T: Concat](a: T, b: T): String = implicitly[Concat[T]].makeTogether(a,b)

  println(concat(1,2))
  println(concat("a","b"))
  println(concat(true,false))
}
