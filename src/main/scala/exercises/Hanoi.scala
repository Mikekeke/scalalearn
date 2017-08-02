package exercises

case class Tile(size: Int) {
  override def toString: String = size match {
    case 1 => "Small"
    case 2 => "Medium"
    case 3 => "Big"
    case _ => throw new IllegalStateException(s"No tile with size $size")
  }
}

class Pole{
  protected var pole: List[Tile] = List.empty[Tile]

  def put(tile: Tile): Pole = {
    pole =
      if (pole.isEmpty) tile :: pole
      else tile :: pole.ensuring(_.head.size > tile.size, "Bottom tile smaller!")
    this
  }

  def take(): Tile = {
    require(pole.nonEmpty, "No tiles!")
    val taken = pole.head
    pole = pole.tail
    taken
  }

  override def toString: String = pole.mkString(" - ")
}

trait Win extends Pole{
  abstract override def put(tile: Tile): Pole = {
    super.put(tile)
    if (pole.length == 3) println("WIN!")
    this
  }
}

object Hanoi extends App{
  val smallTile = Tile(1)
  val mediumTile = Tile(2)
  val bigTile = Tile(3)

  val p_1 = new Pole
  val p_2 = new Pole
  val p_3 = new Pole with Win
  p_3
    .put(bigTile)
    .put(mediumTile)
    .put(smallTile)
}
