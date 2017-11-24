object TT extends App {
  class Correction(_lue: Int) {
    def lue = _lue

  }


  trait CorrectionP {
    val cr: Correction
  }

  val ff = new Correction(33)

  class Tst(x: Int) {
    this: CorrectionP =>
    def get() = x + cr.lue
  }

  val t1 = new Tst(3) with CorrectionP {
    override val cr = new Correction(99)
  }
  print(t1.get())

}
