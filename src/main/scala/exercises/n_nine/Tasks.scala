package exercises.n_nine

import scala.Int

/**
  * Created by Mikekeke on 22-May-17.
  */
object Tasks {
  def last[T](l: List[T]): T = l match {
    case Nil => throw new IllegalArgumentException
    case x :: Nil => x
    case x :: xs => last(xs)
  }

  def lastButOne[T](l: List[T]): T =
    if (l.length < 2) throw new IllegalArgumentException
    else l match {
      case x1 :: x2 :: Nil => x1
      case x :: xs => lastButOne(xs)
    }

  def nth[T](n: Int, l: List[T]): T =
    if (n > l.length) throw new IllegalArgumentException
    else if (n > 1) nth(n - 1, l.tail)
    else l.head

  def nthRecursive[A](n: Int, ls: List[A]): A = (n, ls) match {
    case (1, h :: _) => h
    case (n1, _ :: tail) => nthRecursive(n1 - 1, tail)
    case (_, Nil) => throw new NoSuchElementException
  }

  def length[T](l: List[T]): Int = {
    def go(n: Int, l1: List[T]): Int = l1 match {
      case Nil => n
      case _ :: xs => go(n + 1, xs)
    }

    go(0, l)
  }

  def lengthViaFold[T](l: List[T]) = l.foldLeft(0)((x, _) => x + 1)

  def reverse[T](l: List[T]): List[T] = {
    def go(l1: List[T], acc: List[T]): List[T] = l1 match {
      case Nil => acc
      case _ => go(l1.tail, l1.head :: acc)
    }

    go(l, List.empty[T])
  }

  def isPalindrome[T](l: List[T]): Boolean = {
    def go(ls: List[T], prev: Boolean): Boolean = {
      if (ls.isEmpty || ls.length == 1) true
      else if (prev) go(ls.tail.init, ls.head == ls.last)
      else false
    }

    go(l, true)
  }

  def flatten(l: List[Any]): List[Any] = {
    def go(x: Any, acc: List[Any]): List[Any] = x match {
      case Nil => acc
      case x1: Int => x1 :: acc
      case l1: List[Any] => go(l1.head, acc) ++ go(l1.tail, acc)
    }
    go(l, List.empty[Any])
  }

  def flattenViaFlatMap(l: List[Any]): List[Any] = l flatMap {
    case ls: List[_] => flattenViaFlatMap(ls)
    case x => List(x)
  }

  def compress1[T](l: List[T]) = l.foldRight(List(l.last))((x, b) => if (x.equals(b.head)) b else x :: b)

  def compress[T](l: List[T]) =
    l.foldRight(List.empty[T])((x, z) => if (z.isEmpty || x != z.head) x :: z else z)

  def compressTailrec[T](l: List[T]) = {
    def go(ls: List[T], acc: List[T]): List[T] = ls match {
      case Nil => acc.reverse
      case x :: xs => go(xs.dropWhile(_ == x), x :: acc)
    }
    go(l, List.empty[T])
  }

  def pack(l: List[String]): List[List[String]] = l match {
    case Nil => Nil
    case x :: _ =>
      val (xs, rest) = l span(_ == x)
      xs :: pack(rest)
  }

  def encode(l: List[String]): List[(Int, String)] = pack(l).map(ls => (ls.length, ls.head))

  def encodeModified(l: List[String]) =
    pack(l) map (l => if (l.length == 1) l.head else l)

  def decode(l: List[(Int, Symbol)]): List[Symbol] = {
    l flatMap {case (num, symbol) => List.fill(num)(symbol)} //  or  l flatMap (t => List.fill(t._1)(t._2))
  }

  def encodeDirect(l: List[String]): List[(Int, String)] = l match {
    case Nil => Nil
    case other: List[String] =>
      val(chunk, rest) = other.span(_ == other.head)
      (chunk.length, chunk.head) :: encodeDirect(rest)
  }

  def duplicate(l: List[Symbol]) = l flatMap(x => List(x,x))

  def duplicateN(n: Int, l: List[Symbol]) = l flatMap(x => List.fill(n)(x))

  def dropEveryN(n: Int, l: List[Symbol]): List[Symbol] = l match {
    case list if list.length >= n =>
      val (chunk, rest) = list.splitAt(n - 1)
      chunk ++ dropEveryN(n, rest.tail)
    case other => other

  }
}
