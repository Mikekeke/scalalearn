package state_control

/**
  * Created by ibes on 02.05.17.
  */
object RngTests extends App {
  val rng = SimpleRNG(12)
//  println(RNG.double(rng))
//  println(RNG.ints(10)(rng))
  println(RNG.int)
}
