val word = "some word"

def toMatch(s: String) = s match {
  case `word` => println("Got val word")
  case _ => println("Got smth else")
}

toMatch("word")
toMatch("some word")
toMatch(word)