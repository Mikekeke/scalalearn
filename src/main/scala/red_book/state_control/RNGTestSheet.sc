import red_book.state_control.{RNG, SimpleRNG}

val rng = SimpleRNG(13)
val (int1, rng1) = rng.nextInt
val (int2, rng2) = rng1.nextInt
RNG.double(rng)

