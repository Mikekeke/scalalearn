case class Thing(vrbl: String)
val l = Thing("Bob") :: Thing("Sam") :: Thing("Mike") :: Nil
l.foldRight("empty")((t, s) => t.vrbl + s)
l.foldRight(Thing(""))((t1, t2) => Thing(t1.vrbl + t2.vrbl))
l.reduceRight((t1, t2) => Thing(t1.vrbl + t2.vrbl))