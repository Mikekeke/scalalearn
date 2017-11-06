def swp[T](l: List[T]): List[List[Any]] = l match {
  case Nil => Nil
  case x :: Nil => List(List(x))
  case x :: y :: Nil => List(List(y, x))
  case x :: y :: xs =>
    List(x :: swp(y::xs), List(y, swp(x::xs)))
}

swp(List(1,2,3,4)).foreach(println)

//val and: List[Boolean] => Boolean = {
//  case Nil => true
//  case x :: xs => x && and(xs)
//}
//def _forall[T](p: T => Boolean): List[T] => Boolean =
//  (and compose ((l: List[T]) => l.map(p)))(_)
//val forall = _forall(_)(_)
//forall((x:Int) => x > 2, List(4,4,5))
