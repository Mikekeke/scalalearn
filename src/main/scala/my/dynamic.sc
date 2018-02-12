import scala.language.dynamics
class Ping extends Dynamic{
  def ping = "pong"
  def selectDynamic(s: String) = s
}
val dd = new Ping
dd.ping
dd.tesdt