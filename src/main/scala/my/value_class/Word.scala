package my.value_class

/**
  * <p>http://stackoverflow.com/questions/40704525/scala-value-class-use-cases</p>
  *
  * <p>http://stackoverflow.com/a/40705134</p>
  *
  * <p>wrapping something in new "type" w/o overhead of creating instances during operations like +() defined here</p>
  * @param letters
  */
case class Word(letters: String) extends AnyVal {
  def +(that: Word): Word = Word(letters + that.letters)

  override def toString: String = letters

}
