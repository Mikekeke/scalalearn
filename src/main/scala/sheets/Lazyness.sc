

def maybeTwiceByVal(b: Boolean, i: Int) = if (b) i+i else 0
val x1 = maybeTwiceByVal(true, { println("hi"); 1+41 })

/*
Here, i is referenced twice in the body of maybeTwice , and we’ve made it particularly
obvious that it’s evaluated each time by passing the block {println("hi"); 1+41} ,
which prints hi as a side effect before returning a result of 42 . The expression 1+41
will be computed twice as well.
 */
def maybeTwice(b: Boolean, i: => Int) = if (b) i+i else 0
val x2 = maybeTwice(true, { println("hi"); 1+41 })

//We can cache the value explicitly if we wish to only evaluate the result once,
// by using the lazy keyword
def maybeTwice2(b: Boolean, i: => Int) = {
  lazy val j = i
  if (b) j+j else 0
}
val x3 = maybeTwice2(true, { println("hi"); 1+41 })


