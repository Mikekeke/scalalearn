
// "zips"-like 1st line with others
val ls = List("head", "line1", "line2", "line3")
val res =
  ls.scanLeft(("nil","nil"))((s1, s2) => s1 match {
    case (("nil","nil")) => s2 -> "nil"
    case (ns, _) if ns != "nil" => ns -> s2
  }) collect { case r if r._2 != "nil" => r}

// "zips"-like 1st line with others bu w/o dumb "nil"
// can use shit like that in streams
type Alias1 = (Option[String], Option[String])
type Alias2 = Seq[(Option[String], Option[String])]
val res2 = ls.scanLeft[Alias1, Alias2]((None,None))((s1: Alias1, s2: String) => s1 match {
  case (None, _) => (Some(s2), None)
  case (Some(h), _) => (Some(h), Some(s2))
}) collect {case (Some(a), Some(b)) => a -> b}

// have no idea wtf is going on here
val tst = res.scan("-")((a,b) => {
  println(a)
  b
})