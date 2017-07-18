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