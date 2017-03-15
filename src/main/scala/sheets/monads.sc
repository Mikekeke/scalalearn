import scala.util.Try

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
      .flatMap(x2 => o3(x2)).map(x3 => x1 + x3)
  )

val t2 =
  o1.flatMap(o2).flatMap(o3)

val somelsit = Some(1) :: Some(2) :: Some(3) :: Nil
somelsit.map(_.map(_ + 1))
somelsit.flatMap(_.map(_ + 1))
somelsit.flatMap(_.flatMap(x => Some(x + 1)))

val d = Some("33f".toInt)
try d
catch {case e: Exception => println(e)}
