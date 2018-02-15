package tips_tricks

object StructuralWithAndWoEvidence extends App {

  // fom telegram 14.02.18
  //"duck typing like (?)" shit
  // про evidence: https://twitter.github.io/scala_school/advanced-types.html

  // by @eld0727 (Alexey Otts)
  //  С evidence можно написать value class и тогда будет создан только один инстанс обёртки, у которого будет кешироваться получение метода через рефлексию.
  //  А в твоём примере (см. следующий пример - прим. моё) на каждый вызов будет через рефлексию получаться нужный метод, так как каждый раз будет создаваться новый экземпляр обёртки

//  implicit class Ops[A](val a: A) extends AnyVal {
//    def go[R](implicit ev: A <:< {def run(): R}): R = a.run()
//  }

  //
  // без evidence, не value class
//  implicit class Ops[T](val env: {def run(): T}) {
//    def go = env.run()
//  }

//   но вот так тоже работает
  implicit class Ops[T](val env: {def run(): T}) extends AnyVal {
    def go = env.run()
  }
// про ^
//  Alexey Otts, [15.02.18 12:18]
//  Если скомпилится, то один раз для каждого класса
//
//  Alexey Otts, [15.02.18 12:19]
//  Если эта функция вызывается очень часто, то, конечно, лучше сделать тайп класс



  class Foo {
    def run(): String = "foo"
  }

  class Lol {
    def run(): String = "lul2"
  }

  class LolInt {
    def run(): Int = 666
  }

  println(new Foo().go)
  println(new Lol().go)
  println(new LolInt().go)

}
