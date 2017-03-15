val list = 1 :: 2 :: 3 :: 4 :: Nil

// dealing with matching error
def printList[A](l: List[A]): Unit = l match {
  case head :: tail =>
    println(head)
    printList(tail)
  case _ => //nothing  - to avoid scala.MatchError
}
printList(list)

def pf[A]: PartialFunction[List[A], A] = {
  case head :: _ => head
  // or same
//  case head :: tail => head
    // or same
//  case List(head, _*) => head
}

pf.isDefinedAt(list)
pf.isDefinedAt(Nil)
def printList2[A](l: List[A])(p:PartialFunction[List[A], A]): Unit =
  if (p.isDefinedAt(l)) l match {
  case head :: tail =>
    println(head)
    printList2(tail)(p)

}
printList2(list)(pf)

def printList3[A](l: List[A]): Unit = if (l.nonEmpty) l match {
    case head :: tail =>
      println(head)
      printList3(tail)
  }
printList3(list)

def printListErr[A](l: List[A]): Unit = l match {
  case head :: tail =>
    println(head)
    printListErr(tail)
}
printListErr(list)
