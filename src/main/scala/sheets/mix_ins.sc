trait Explodable {
  def boom(): Unit
}

class Grenade() {
  this: Explodable =>

  def detonate() = {
    println("Detonating grenade")
    boom()
  }
}

trait SmallExplosion extends Explodable {
  override def boom(): Unit = println("boom")
}

trait LongCord extends Explodable {
  abstract override def boom(): Unit = {
    print("...long cord burns long... ")
    super.boom()
  }
}

// val simpleGr0 = new Grenade // doesn't work w/o it's dependency
// val simpleGr1 = new Grenade with LongCord // doesn't work w/o trait with concrete boom() impl
val simpleGr = new Grenade with SmallExplosion with LongCord
simpleGr.detonate()


// another try
case class SpeechProducer(version: Int) {
  def produce[B](f: => B): B = {
    def go(f: => B, n: Int): B =
      if (n > 1){
        f
        go(f, n - 1)
      } else f

    go(f, version)
  }
}

trait VoiceEngine {

  val speaker: SpeechProducer
  def say(s: String): Unit
}

trait WomanVoice extends VoiceEngine{
  def say(s: String): Unit =
    speaker.produce(println(s"Woman's voice says: $s"))
}

trait Anger extends VoiceEngine {
  abstract override def say(s: String): Unit =
    super.say(s.toUpperCase + " MUTHERFUCKER!")
}

class Assistant {
  this: VoiceEngine =>
  def greetings(): Unit = say("Hello")
}

val calmAssistant = new Assistant with WomanVoice {
  val speaker = SpeechProducer(1)
}
calmAssistant.greetings()

val madAssistant = new Assistant with WomanVoice with Anger {
  val speaker = SpeechProducer(3)
}
madAssistant.greetings()


// Ex: trait so it can only be subclassed by a certain type
class Spaceship
class SeaShip

trait WarpCore {
  this: Spaceship =>
}

// class BadShip extends SeaShip with WarpCore // illegal inheritance
class GoodShip extends Spaceship with WarpCore

// Ex: trait can only be mixed into a type that has a specific method
trait WarpCore2 {
  this: {
    def coreBoostable: Boolean
    def startWarpCore: Unit
  } =>

  def boost() = if (coreBoostable) println("BOOOOST!!!") else println("Cant boost")
}

class ShipOne extends  Spaceship with WarpCore2 {
  def coreBoostable = true
  def startWarpCore: Unit = println("Core started")
}

class PredefSpaceShip {
  def coreBoostable = false
  def startWarpCore: Unit = println("Predef core started")
}

// class ShipTwo extends Spaceship with WarpCore2  // illegal inheritance
class ShipThree extends PredefSpaceShip with WarpCore2
new ShipOne().boost()
new ShipThree().boost()

