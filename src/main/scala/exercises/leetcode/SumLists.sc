import exercises.leetcode._
def rnd = (Math.random() * 10).toInt
lazy val numStream: Stream[Int] =  Stream continually rnd
def mkListOf(n: Int) = numStream.take(n).toList

val lists1 = (1 to 10000) map(_ => mkListOf(5) -> mkListOf(8))
time {
  lists1.map{case (l,r) => tst(l,r)}
}._2 + " ms"

def tst(ll: List[Int], lr: List[Int]): List[Int] =
  ll.zipAll(lr,0,0).foldLeft((List.empty[Int], 0)){(acc, tpl) =>
    val (resultList, car) = acc
    val(left, right) = tpl
    val sum = left + right + car
    (sum%10 :: resultList) -> sum/10
  }._1


// javacopy
class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}
implicit def listToLn(l: List[Int]): ListNode = l match {
  case x :: Nil =>
    val ln = new ListNode(x); ln.next = null; ln
  case x :: xs =>
    val ln = new ListNode(x); ln.next = listToLn(xs); ln

}

def addTwoNumbers(l1: ListNode, l2: ListNode) = {
  val dummyHead = new ListNode(0)
  var p = l1
  var q = l2
  var curr = dummyHead
  var carry = 0
  while ( {
    p != null || q != null
  }) {
    val x = if (p != null) p.x
    else 0
    val y = if (q != null) q.x
    else 0
    val sum = carry + x + y
    carry = sum / 10
    curr.next = new ListNode(sum % 10)
    curr = curr.next
    if (p != null) p = p.next
    if (q != null) q = q.next
  }
  if (carry > 0) curr.next = new ListNode(carry)
  dummyHead.next
}
addTwoNumbers(List(1,2), List(3,4))
// javacopy - END

val lists2 = (1 to 10000) map(_ => listToLn(mkListOf(5)) -> listToLn(mkListOf(8)))
time {
  lists2.map{case (l,r) => addTwoNumbers(l,r)}
}._2 + " ms"
