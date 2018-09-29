import scala.collection.Iterable

trait DocPart[T] {
  val content: T

  def isEmpty: Boolean
}

trait Renderer {
  def render(doc: Elements.doc): String
}
object Elements {

  // if make it not case - can't see tjem where Elements imported
  case class pargr(content: String) extends DocPart[String] {
    override def isEmpty: Boolean = false
  }
  case class header(content: String = "") extends DocPart[String] {
    override def isEmpty: Boolean = content.isEmpty }

  class body(val content: Seq[pargr] = Seq.empty[pargr]) extends DocPart[Seq[pargr]] {
    override def isEmpty: Boolean = content.isEmpty
  }
  object body {
    def apply(paragraphs: pargr*): body = new body(Seq(paragraphs:_*))
  }

  class doc (val h: Option[header] = None, val b: Option[body] = None) {
    def render(implicit r: Renderer) = r.render(this)
  }
  object doc {
    // if remove Elements for body, there will be Error:(18, 57) Elements.body does not take parameters
    // see https://stackoverflow.com/questions/26767525/scala-case-class-type-does-not-take-parameters
    def apply(head: header = header(), body: body = Elements.body()): doc =
      new doc (
        if (head.isEmpty) None else Some(head),
        if (body.isEmpty) None else Some(body)
      )
  }
}

object Document extends App {
  import Elements._

  implicit val stRend = new Renderer {
    private def rendHead(_h: Option[header]) = _h.fold("NO HEAD")(s =>  s"$s\n\n")

    private def rendPars(ps: Seq[pargr]) =
      ps.foldLeft("")((z,p) => z + s"${p.content}\n\n")


    private def rendBody(_b: Option[body]) =
      _b.fold("")(b => rendPars(b.content))

    override def render(doc: doc): String = {
      val res = s"${rendHead(doc.h)}${rendBody(doc.b)}"
      res
    }
  }

  val doc1 = doc(
    header("Header")
  )
  val doc2 = doc(
    header("Header")
    ,body(pargr("line 1"), pargr("line 2"))
  )
  val doc3 = doc(
    header()
  )

  println(doc1 render)
  println(doc2 render)
  println(doc3 render)


  List(1,2,3).foldRight()

}
