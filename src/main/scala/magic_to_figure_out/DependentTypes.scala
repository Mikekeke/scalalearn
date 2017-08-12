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

//  A:
//  trait Pet
//  class Cat extends Pet
//
//  class Person {
//    type Pet
//  }
//
//  class Susan extends Person {
//    type Pet = Cat
//  }
//
//  ----------------------------------------------------------
//
//  B:
//  trait Pet
//  class Cat extends Pet
//
//  class Person[Pet]
//
//  class Susan extends Person[Cat]
//
//  http://typelevel.org/blog/2015/07/13/type-members-parameters.html
//
//  Oleg Nizhnik
//  В scala 2 это пока очень разные вещи.
//  1. type member ы не могут быть ко\контр вариантными
//  2. type member ы лучше работают как type families аналог, если совмещать их с typeclass ами, компилятор всегда будет стараться вывести и сравнить их, а не начинать с них поиск имплиситов
//  3. В type parameters вы не можете сослаться на type members, а наоборот - можно
//  В dotty эту разницу нивелируют и type parameters будет синтаксическим сахаром для type members