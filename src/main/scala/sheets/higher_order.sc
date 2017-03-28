import java.lang.Number

// decomposition for decomposition
val pattern = "@"
val string = "@Some words"

def clean(s: String)(cleaner: String => String) = cleaner(s)
val cleanNonWords: String => String = s => "@".r.replaceAllIn(s, "")

// !!!@return   a function `f` such that `f(x1)(x2) == apply(x1, x2)`
// that's why cleanStringWith: (String, String => String)
// works in (cleaner: String => String)
val cleanStringWith: (String, String => String) => String = (str, pattern) => pattern(str)
clean(string)(cleanStringWith(_, cleanNonWords))

def cleanStringWith2(s:String)(f: String => String): String = f(s)
clean(string)(cleanStringWith2(_)(cleanNonWords))

clean(string)(s => pattern.r.replaceAllIn(s, ""))
// decomposition for decomposition - END

def stringInput(s: String): String = s + " inputed"
def stringTransformDef(s: String)(tr: String => String) = tr(s)
val stringTransformVal: (String, String => String) => String = (s, transform) => transform(s)
stringInput(stringTransformDef("test")(_ + " transformed"))
// just remember - val doesn't work with generic type
stringInput(stringTransformVal("test", _ + " transformed"))

val f: Int => Boolean => Int = x => bool => if (bool) x else 500
f(3)(false)
f(3)(true)


def inter(s: String) = {
  println("Making integer")
  s.toInt
}

val f2: Int => (Boolean, => Int) => Int = x => (bool, num) => if (bool) x else num
f2(3)(false, inter("34"))
f2(3)(true, inter("34"))

val f3: Int => Boolean => (=> Int) => Int = x => bool => num => if (bool) x else num
f3(3)(false)(inter("343"))
f3(3)(true)(inter("343"))

// Partial applying ***********************************************************
type Customer = String
type CustomerId = Int
type GetCustomer = CustomerId => Customer
type Connection = String

def getCustomer1(c: Connection)(id: CustomerId): Customer =
  s"Customer with ID $id via connection '$c'"
val getCustomer2: Connection => CustomerId => Customer = c => id =>
  s"Customer with ID $id via connection '$c'"
val bdConnection: Connection = "SQL-connection"
val getCustViaSQL1: (CustomerId) => Customer = getCustomer1(bdConnection)
val getCustViaSQL2: (CustomerId) => Customer = getCustomer2(bdConnection)

// Partial applying ***********************************************************
def divide[E, S](ifZero: => E)(ifOk: => Double => S)(a: Double, b: Double) =
  if (b == 0) ifZero else ifOk(a / b)

// will print all right here if val instead of def
def ifZero1 = {println("eval ifZero"); println("Error mthfkr!")}

// will throw right here coz of val
//val ifZero2: Nothing = throw new IllegalArgumentException("Cant divide by 0")
def ifZero2: Nothing = throw new IllegalArgumentException("Cant divide by 0")
def ifOk = (x: Double) => {println("eval ifOk"); s"Successful: $x"}

val divide1: (Double, Double) => Any = divide[Int, String](ifZero2)(ifOk)
divide1(2,3)

def divideNotByName[E, S](ifZero: E)(ifOk: Double => S)(a: Double, b: Double) =
  if (b == 0) ifZero else ifOk(a / b)

def divide2: (Double, Double) => Any = divideNotByName[Int, String](ifZero2)(ifOk)
println("dividing w divideNotByName")
// throws exception from ifZero2 coz evaluates it by value during divide2 call
divide2(2,3)

