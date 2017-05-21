package state_control

import state_control.RNG.SimpleRNG

/**
  * Created by ibes on 02.05.17.
  */
object RngTests extends App {
  val rng = SimpleRNG(12)
//  println(RNG.double(rng))
//  println(RNG.ints(10)(rng))
  println(RNG.int)
  println(RNG.int(rng))
}
