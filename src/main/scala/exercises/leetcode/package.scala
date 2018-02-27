package exercises

package object leetcode {
  def time[T](code: => T):(T, Long) = {
    val t1 = System.currentTimeMillis()
    val res = code
    val t2 = System.currentTimeMillis()
    res -> (t2-t1)
  }
}
