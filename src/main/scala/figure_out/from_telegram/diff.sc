//Wystan Hugh, [19.02.18 18:19]
//[In reply to Alexey]
//Я попробовал как-то для данных, даже без типов, пописать (сравнение).
// Ничего кроме простейшего рекурсивного прохода в голову не пришло:
// https://gist.github.com/anonymous/aed8729503a7206e834e879896b4c0d2

case class Foo(str: String)
case class Root(lst: List[Foo])


sealed trait Action

sealed trait FooAction extends Action
case class AddFoo(foo: Foo) extends FooAction
case object RemoveFoo extends FooAction

sealed trait RootAction
case class RootUpdate(lst: List[FooAction]) extends RootAction

case object Identity extends Action with FooAction with RootAction


object Diff {
  def diff(root1: Root, root2: Root): RootAction = {
    val actions = diff(root1.lst, root2.lst)
    val identity = actions.forall {
      case Identity => true
      case _ => false
    }
    if (identity) {
      Identity
    } else {
      RootUpdate(actions)
    }
  }

  def diff(foos1: List[Foo], foos2: List[Foo]): List[FooAction] = (foos1, foos2) match {
    case (foo1 :: tail1, foo2 :: tail2) if foo1 == foo2 => Identity :: diff(tail1, tail2)
    case (foo1 :: tail1, foo2 :: tail2) => List(RemoveFoo, AddFoo(foo2)) ::: diff(tail1, tail2)
    case (lst1, Nil) => List.fill(lst1.size)(RemoveFoo)
    case (Nil, lst2) => lst2.map(foo => AddFoo(foo))
  }
}


val root1 = Root(List(Foo("hi, "), Foo("scala"), Foo("!")))
val root2 = Root(List(Foo("hi, "), Foo("Scala")))

Diff.diff(root1, root2)
Diff.diff(root2, root1)
Diff.diff(root1, root1)