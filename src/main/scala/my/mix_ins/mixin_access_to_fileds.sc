class Tst(s: String, x: Int) {
  protected val _s = s
}
val t1 = new Tst("test1", 3)
//t1._s // unreachable

trait GetS {
  protected val _s: String

  def gets() = _s
}

val t2 = new Tst("test2", 4) with GetS
t2.gets()