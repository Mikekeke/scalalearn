import exercises.n_nine.Tasks._

val testList = List(1,2,3,4,5)
last(testList)
lastButOne(testList)
nth(2, testList)
nthRecursive(2, testList)
length(testList)
lengthViaFold(testList)
reverse(testList)
val palindrome = List(1,2,3,2,1)
isPalindrome(testList)
isPalindrome(palindrome)

val toFlatten: List[Any] = List(List(1, 1), 2, List(3, List(5, 8)))
flatten(toFlatten)
val toCompress = List("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e")
compress1(toCompress)
compress(toCompress)
compressTailrec(toCompress)
encode(toCompress)
encodeModified(toCompress)
val decodeList: List[(Int, Symbol)] = List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))
decode(decodeList)

def myFlat[A,B](l: List[A])(f: Int => List[B]) =
  l.foldRight(Nil: List[B])((n, b) => f(n) ++ b)

myFlat(testList)(i => List(i + "a", i + "b"))
