def twoSum(nums: Array[Int], target: Int): Array[Int] = {
  val res = for {
    fst <- nums.indices
    snd <- nums.indices.drop(fst + 1)
    if nums(fst) < target && nums(fst) + nums(snd) == target
  } yield (fst, snd)
  println(res)
  val (f, s) = res.head
  Array(f, s)
}

twoSum(Array(2, 7, 11, 15, 11), 18)


def twoSum2(nums: Array[Int], target: Int): Array[Int] = {
  val is = nums.indices.iterator
  val maxIdx = nums.length-1

  def go(currIdx: Int, arr: Array[Int]): Array[Int] =
    if (currIdx > maxIdx) Array()
    else if (nums(currIdx) >= target) go(currIdx+1, arr.tail)
    else {
      print("*")
      val dropped = arr.tail
      val snd = dropped.indexOf(target - nums(currIdx))
      if (snd != -1) Array(currIdx, nums(snd))
      else go(currIdx + 1, dropped)
    }

  go(0, nums)
}

twoSum2(Array(2, 7, 11, 15, 11), 1)

