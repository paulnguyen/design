case class CasePoint(x : Int, y : Int) {
    def *(m : Int) = CasePoint(this.x * m, this.y * m)
    def +(other : CasePoint) = CasePoint(this.x + other.x, this.y + other.y)
}