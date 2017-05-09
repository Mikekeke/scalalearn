val f1: String => String => Unit = s1 => s2 => println(s1 + " - " + s2)
val fPart: String => String = s => s + "!"

f1("a")("b")

def t1 = f1 {
  fPart {
    "d"
  }
}

t1("a")

val t2 = f1 compose fPart
t2("1")("2")

val t3 = fPart andThen f1
t3("1")("2")

val transfPrint: List[String] => Unit = c => c.foreach(println)

//def stage1(x: Int)(f: String => List[String]): List[String] = f(x.toString)
val stage1: Int => ((String) => List[String]) => List[String] =
  n => f => f(n.toString)
val stage2: String => String => List[String] = s1 => s2 => List(s2 + s1, s2 + s1)

def t4() = transfPrint {
  stage1(4) {
    stage2 {
      val l1 = List(1,2)
      val l2 = List(3,4)
      (l1 ++ l2).foldLeft("-")(_ + _)
    }
  }
}

t4()
val b: (String) => List[String] = stage2("@")
val a: List[String] = stage1(4)(b)
transfPrint(a)
val tr = b andThen transfPrint
tr("tst - ")

def ff(x: Int) = x + 3
ff {
  44
}
