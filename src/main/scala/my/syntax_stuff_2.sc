trait PathEl {
  def ->(e: PathEl): PathEl
}
trait Sep extends PathEl {
  def ->(e: PathEl): PathEl
}
trait Dir extends PathEl

case class folder(s: String) extends PathEl {
  override def toString: String = s

  override def ->(e: Sep) = folder(s + e.toString)
}

case class slash(e: PathEl)  extends Sep{
  override def toString: String = "/"

  override def ->(e: folder) = folder(e.s)
}

//trait Sep
//trait Pth {
//  val s: String
//}
//
//case class Slash() extends Sep{
//  override def toString: String = "/"
//}
//
//case object End extends Sep
//
//case class Path(s: String) extends Pth{
//  def ~>(sep: Sep): Pth => Pth = p => sep match {
//    case s: Slash => Path(p.s + sep.toString + s)
//    case End => this
//  }
//
//  override def toString: String = s
//}
//
//val res: Pth => Pth = Path {
//  "test"
//} ~> Slash()
//val res2: Pth = res(Path("root"))