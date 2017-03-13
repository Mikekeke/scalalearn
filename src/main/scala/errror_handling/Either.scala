package errror_handling

sealed trait Either[+E, +A] {
//  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C):
//  Either[EE, C] = for { a <- this; b1 <- b } yield f(a,b1)
}

case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]
