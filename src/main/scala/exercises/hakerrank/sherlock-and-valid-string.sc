val sOk = "ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd"
val sNok = "aabbccddeefghi"

//val r1 = sOk.groupBy(_.toChar).mapValues(_.length).map(_._2).groupBy(_.toInt).mapValues(_.size)

def isValid(s: String): String = {
  val r1 = s.groupBy(identity).map(_._2.length).groupBy(identity).mapValues(_.size)
  if (r1.size == 1) return "YES"
  if (r1.size > 2) return "NO"
  val (ones, notOnes) = r1.partition(_._1 == 1)
  if (ones.size == 1) {
    if (ones(1) == 1) return "YES"
    if (ones(1) > 1 && notOnes.values.head != 1) return "NO"
  }
  if (r1.values.forall(_ > 1)) return "NO"
  val one = r1.filter(_._2 == 1).keys.head
  val nonone = r1.filter(_._2 != 1).keys.head
  if (one - 1 != nonone) "NO"
  else "YES"
}

isValid(sOk) == "YES"
isValid(sNok) == "NO"
isValid("abbbbcccc") == "YES"
isValid("aabbbbcccc") == "NO"
isValid("aaabbbbcccc") == "NO"
isValid("aaabbbcccc") == "YES"

/**
  * Haskell e.g.
test :: [[Int]] -> String
test [x] = "YES"
test [x, [1]] = "YES"
test [[x],(y:ys)]
  | x - 1 == y = "YES"
  | otherwise = "NO"
test x = "NO"

main = do
  t <- getLine
  putStrLn . test . reverse . sort . group . map length $ group . sort $ t
  */