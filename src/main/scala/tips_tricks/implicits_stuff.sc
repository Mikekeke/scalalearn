// fom telegram 14.02.18
//"duck typing like (?)" shit


// reflection free - better
implicit class Ops[A, R](val a: A) extends AnyVal{
    def go(implicit ev: A <:< { def run(): R }): R = a.run()

// with reflection - not good
//  implicit class Ops[T](env: {def run(): T}) {
//    def go = env.run()
//  }

}

class Foo {
  def run(): String = "foo"
}

class Lol {
  def run(): String = "lul2"
}

class LolInt {
  def run(): Int = 666
}

//import Test._

new Foo().go
new Lol().go
new LolInt().go