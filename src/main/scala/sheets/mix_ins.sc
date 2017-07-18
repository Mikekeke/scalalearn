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
