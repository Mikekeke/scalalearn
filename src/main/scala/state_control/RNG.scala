package state_control

import scala.annotation.tailrec

/**
  * Created by ibes on 02.05.17.
  */
trait RNG {

  def nextInt: (Int, RNG)
  def nonNegativeInt: (Int, RNG)
  def myNonNegativeInt: (Int, RNG)
}

case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }

  override def myNonNegativeInt: (Int, RNG) = {
    this.nextInt match {
      case (n, r) if n >= 0 => (n, r)
      case (_, r) => r.nonNegativeInt
    }
  }

  // We need to be quite careful not to skew the generator.
  // Since `Int.Minvalue` is 1 smaller than `-(Int.MaxValue)`,
  // it suffices to increment the negative numbers by 1 and make them positive.
  // This maps Int.MinValue to Int.MaxValue and -1 to 0.
  override def nonNegativeInt: (Int, RNG) = {
    val (i, r) = nextInt
    (if (i < 0) -(i + 1) else i, r)
  }
}

object RNG {
  def double(rng: RNG): (Double, RNG) = {
    val (i, r) = rng.nonNegativeInt
    (i.toDouble / Int.MaxValue.toDouble, r)
  }

  def intDouble(rng: RNG): ((Int,Double), RNG) = {
    val (i, r1) = rng.nextInt
    val (d, r2) = RNG.double(r1)
    ((i, d), r2)
  }

  def doubleInt(rng: RNG): ((Double,Int), RNG) = {
    val ((i, d), r) = intDouble(rng)
    ((d, i), r)
  }

  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
    val (d1, r1) = double(rng)
    val (d2, r2) = double(r1)
    val (d3, r3) = double(r2)
    ((d1, d2, d3), r3)
  }

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
    var resultList = new scala.collection.mutable.ListBuffer[Int]()
    def go(rand: RNG, cnt: Int): (List[Int], RNG) = {
      val(i, r) = rand.nextInt
      if (cnt > 0) {
        resultList += i
        go(r, cnt - 1)
      } else {
        (resultList.toList, r)
      }
    }
    go(rng, count)
  }

  type Rand[+A] = RNG => (A, RNG)
  val int: Rand[Int] = _.nextInt
  def unit[A](a: A): Rand[A] = rng => (a, rng)
  def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }
}