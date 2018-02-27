import java.util

import scala.collection.mutable.ArrayBuffer

def time[T](b: => T):(T,Long) = {
  val t1 = System.currentTimeMillis()
  val res = b
  val t2 = System.currentTimeMillis()
  (res, t2-t1)
}

def twoSum(nums: Array[Int], target: Int): Array[Int] = {
  val res = for {
    fst <- nums.indices
    snd <- nums.indices.drop(fst + 1)
    if nums(fst) + nums(snd) == target
  } yield (fst, snd)
  println(res)
  val (f, s) = res.head
  Array(f, s)
}

time(twoSum(Array(3,4,5,6,3), 6))


def twoSum2(nums: Array[Int], target: Int): Array[Int] = {
  if (nums.isEmpty) Array()
  val maxIdx = nums.length-1

  def go(shift: Int, arr: Array[Int]): Array[Int] =
    if (shift > nums.length) Array()
    else {
      val head = arr.head
      val dropped = arr.tail
      val snd = dropped.indexOf(target - head)
      if (snd != -1) Array(shift, snd+shift+1)
      else go(shift + 1, dropped)
    }

  go(0, nums)
}

def twoSum3(nums: Array[Int], target: Int): Array[Int] = {
  def go(idx: Int, map: Map[Int, Int]): Array[Int] = {
    val curr = nums(idx)
    val compl = target - curr
    if (map.isDefinedAt(compl)) Array(idx, map(compl))
    else go(idx+1, map + (curr -> idx))
  }
  go(0,Map.empty)
}

twoSum3(Array(3,2,4), 6)
twoSum3(Array(3,3), 6)
twoSum3(Array(-1,-2,-3,-4,-5), -6)
