val devivisbleBy3: Int => Boolean = x => x % 3 == 0
val devivisbleBy2: Int => Boolean = x => x % 2 == 0
val twoAndThree = (x: Int) => devivisbleBy2(x) && devivisbleBy3(x)
def not[T](pred: T => Boolean) = (x: T) => !pred(x)
def complement[A](predicate: A => Boolean) = (a: A) => !predicate(a)
val undevBy3 = not(devivisbleBy3)
def just[A](preds: List[A => Boolean]): (A) => Boolean = (x: A) => preds.exists(p => p(x))
def noneOf[A](preds: List[A => Boolean]): (A) => Boolean = (x: A) => preds.forall(p => !p(x))
val lst = List(1,2,3)
val justFun: Int => Boolean = just(List(devivisbleBy3, devivisbleBy2))
lst.filter(x => justFun(x))
lst.filter(justFun)
lst.filter(noneOf(List(devivisbleBy3, devivisbleBy2)))
