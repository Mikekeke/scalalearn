val p1: PartialFunction[Int, String] = {case 4 => "Ok 4"}
val p2: PartialFunction[Int, String] = {case 5 => "Ok 5"}
val p3: PartialFunction[Int, String] = {case x => s"Always Ok: $x"}

val res = p1 orElse p2 orElse p3
res(4)
res(5)
res(100)

