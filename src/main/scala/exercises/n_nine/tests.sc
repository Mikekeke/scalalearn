import exercises.n_nine.Tasks._
val testList = List(1,2,3,4,5)
//last(testList)
//lastButOne(testList)
//nth(2, testList)
//nthRecursive(2, testList)
//length(testList)
//lengthViaFold(testList)
reverse(testList)
val palindrome = List(1,2,3,2,1)
isPalindrome(testList)
isPalindrome(palindrome)

val toFlatten: List[Any] = List(List(1, 1), 2, List(3, List(5, 8)))
flatten(toFlatten)