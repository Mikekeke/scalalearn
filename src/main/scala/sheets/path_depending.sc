class BallGame(name: String) {
  class Ball(gameName: String) {
    override def toString: String =
      s"ball for $gameName"
  }
  def prepareBall() = new Ball(name)
  def play(ball: Ball): Unit =
    println(s"Playing with awesome $ball")
}

val basketball = new BallGame("Basketball")
val baskBallBall = basketball.prepareBall()
val hockey = new BallGame("Hockey")
val hockeyBall = hockey.prepareBall()

basketball.play(baskBallBall)
hockey.play(hockeyBall)
//hockey.play(baskBallBall) - won't work