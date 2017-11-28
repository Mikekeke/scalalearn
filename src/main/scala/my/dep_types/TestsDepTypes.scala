package my.dep_types

object Pan {
  sealed trait State

  sealed trait Empty extends State
  sealed trait Full extends State

  def apply(): Pan[Empty] = new Pan
}

class Pan[S <: Pan.State] {
  import Pan._

  //  false error
  def fill[T >: S <: Pan.Empty]: Pan[Full] = {
    println("Filling pan")
    new Pan
  }

  def empty(implicit ev: S =:= Pan.Full): Pan[Pan.Empty] = {
    println("Clearing pan")
    new Pan
  }

  def cook(implicit ev: S =:= Pan.Full) = {
    println("Cooking")
    new Pan
  }
}


object TestsDepTypes extends App {
  Pan().fill.empty.fill.cook
//  won't compile
//  Pan().empty
//  Pan().cook
//  Pan().fill.fill

}
