val mon = List(1,2)
val m2 = mon.map(d => List(d, d + 2))
val m3 = mon.flatMap(d => List(d, d + 2))

val x = 5
val mon2 = Some(x)
val s1 = mon2.map(x => List(x))
val s2= mon2.flatMap(x => None)

val d1 = "test".map(c => c + "-")
val d2 = "test".flatMap(c => c + "-")
val d3 = "test".to[List]

val c = "test"(3)
