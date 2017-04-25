val deadline: Int = 4000000
lazy val fibs: Stream[BigInt] =
  BigInt(1) #:: BigInt(2) #:: fibs.zip(fibs.tail).map(x => x._1 + x._2)
def calc(dl: Int, s: Stream[BigInt])= {
  s.takeWhile(_ <= dl).filter(x => x % 2 == 0).sum
}
//calc(deadline, fibs)


val x = 600851475143L
val r = (1L to x).filter(n => x % n == 0)
r.foreach(println)
val l2 = List(2,4,1,2,2,2,2,2)

def loopExit(it: Iterator[Long]) = {
  def go(prev: Long, acc: Long): Long = {
    val next = it.next()
    if (acc >= x) prev else go(next, acc * next)
  }
  go(1, it.next())
}

loopExit(r.iterator)