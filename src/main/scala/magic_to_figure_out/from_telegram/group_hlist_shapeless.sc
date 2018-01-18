// https://gist.github.com/Odomontois/d92d95e829254a39f21d2b847d7b7dfb

//import shapeless._
//
//import scala.reflect.ClassTag
//import scala.reflect.runtime.universe._
//
//trait CollectByType[T] {
//  type Repr <: HList
//  def empty: Repr
//  def addOne( repr: Repr, x: T): Repr
//}
//
//trait GenericCollectByType {
//  implicit def collectGen[T, C <: Coproduct]
//  (implicit gen: Generic.Aux[T, C], cbt: CollectByType[C]): CollectByType.Aux[T, cbt.Repr] = new CollectByType[T] {
//    type Repr = cbt.Repr
//    def empty = cbt.empty
//    def addOne(repr: Repr, x: T): Repr = cbt.addOne( repr, gen.to(x))
//  }
//}
//
//object CollectByType extends GenericCollectByType {
//  type Aux[A, R] = CollectByType[A] {type Repr = R}
//
//  implicit val collectCNil: Aux[CNil, HNil] = new CollectByType[CNil] {
//    type Repr = HNil
//    val empty = HNil
//    def addOne(repr: HNil, x: CNil): HNil = x.impossible
//  }
//
//  implicit def collectCCons[H: ClassTag, T <: Coproduct](implicit tail: CollectByType[T]): Aux[H :+: T, List[H] :: tail.Repr] = new CollectByType[H :+: T] {
//    override type Repr = List[H] :: tail.Repr
//    val empty = List.empty[H] :: tail.empty
//    override def addOne(repr: Repr, xc: H :+: T) = xc match {
//      case Inl(h) => (h :: repr.head) :: repr.tail
//      case Inr(t) => repr.head :: tail.addOne(repr.tail, t)
//    }
//  }
//
//  object syntax{
//    implicit class CollectionOps[X](val xs: List[X]) extends AnyVal{
//      def collectByType[R <: HList](implicit cbt: Aux[X, R], asTuple: ops.hlist.Tupler[R]): asTuple.Out =
//        asTuple(xs.foldLeft(cbt.empty)(cbt.addOne))
//    }
//  }
//}
//
//
//object CBTMain extends App {
//  sealed abstract class ADT
//  final case class Foo(s: String) extends ADT
//  final case class Bar(i: Int) extends ADT
//  final case class Baz(b: Boolean) extends ADT
//
//  val list: List[ADT] = List(Foo("lol"), Bar(2), Baz(false), Bar(3), Foo("kek"), Baz(true), Foo("cheburek"))
//
//  import CollectByType.syntax._
//
//  val collected = list.collectByType
//
//  println(collected)
//  println(typeOf[collected.type].widen)
//}