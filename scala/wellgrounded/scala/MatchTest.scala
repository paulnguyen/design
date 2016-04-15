  object MatchTest {
  
    def storageSize(obj: Any) = obj match {
      case s: String => s.length
      case i: Int    => 4
      case _         => -1
    }
  
    def main(args : Array[String]) {
      val frenchDayOfWeek = args(0) match {
        case "Sunday"    => "Dimanche"
        case "Monday"    => "Lundi"
        case "Tuesday"   => "Mardi"
        case "Wednesday" => "Mercredi"
        case "Thursday"  => "Jeudi" 
        case "Friday"    => "Vendredi" 
        case "Saturday"  => "Samedi" 
        case _           => "Error: '"+ args(0) +"' is not a day of the week"
      }
      println(frenchDayOfWeek)
      
      val xaxis = Point(2, 0)
      val yaxis = Point(0, 3)
      val some  = Point(5, 12)
      
      val whereami = (p : Point) => p match {
        case Point(x, 0) => "On the x-axis"
        case Point(0, y) => "On the y-axis"
        case _           => "Out in the plane"
      }
      println(whereami(xaxis))
      println(whereami(yaxis))
      println(whereami(some))
    }
  }

