class C1 {
  private var _v1: String = ""
  def v1 = _v1
  def v1_=(v: String) {_v1 = v}
  def callXX = println(_v1)
  def  callC1 = println("Calling C1")
}

new C1 {
  callXX
  callC1 // method will be called right here
}

new C1 {
  v1 = "d"
  callXX
  callC1 // method will be called right here
}