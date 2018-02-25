import cats.data.StateT
import cats.implicits._

case class Ctx(v: String = "Start") {
  private def stageNum = Math.random()* 100 toInt
  def curName = s"cn = $stageNum"
}


val st: StateT[Option, Ctx, String] =
  StateT(ctx => Option(Ctx(ctx.v + "+stage"), ctx.curName))

val res: Option[(Ctx, String)] = st
  .modify(ctx => Ctx(s"{${ctx.v}}/lol"))
  .run(Ctx())
