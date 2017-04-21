import streams.MyStream

//val f = Stream(1,2,3).map(x => {println("mapping"); x + 1})
//  .filter(x => {println("filtering"); x < 4}).toList

def sqr(n: Int) = () => {println("sqr"); n * n}
val f = MyStream(sqr(2), sqr(3)).mapSimple(x => () => {println("+200"); x() + 200})
//f.get()
println("toList")
f.toList.foreach(x => println(x()))










