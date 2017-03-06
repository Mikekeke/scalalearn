def o1 = Some(1)
def o2(x: Int) = Some(x + 11)
def o3(x: Int) = Some(x * 2)

val t0 = for {
  x1 <- o1
  x2 <- o2(x1)
  x3 <- o3(x2)
} yield x1 + x3

val t1 =
  o1.flatMap(
    x1 => o2(x1)
      .flatMap(x2 => o3(x2))
      .map(x3 => x1 + x3))

val t2 =
  o1.flatMap(o2).flatMap(o3)

implicit class Stringer(s: String) {
  def ! : Unit = println(s"$s!")
}
"ls" !
