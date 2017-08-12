package magic_to_figure_out

object DependentTypes extends App{
  trait OS
  class Linux extends OS
  class Windows extends OS
  class PC[OS]

  class WindowsPC extends PC[Windows] {
//    def possibleOnlyWithWin(x: ???) = 'success
  } // how to access type here? was told about aux pattern but didn't understand shit

  val testWin = new Windows

  trait OS2
  class Linux2 extends OS2
  class Windows2 extends OS2

  class PC2{
    type OS2
  }

  class LinuxPC2 extends PC2 {
    type OS2 = Linux2
    def possibleOnlyWithLinux(x: OS2) = 'success
  }

  val testLin = new LinuxPC2
  testLin.possibleOnlyWithLinux(new Linux2)
//  testLin.possibleOnlyWithLinux(new Windows2)  //won't compile
}
