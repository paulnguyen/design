  import scala.actors._
  
  class TemperatureMonitor extends Actor {
    var tripped : Boolean = false
    var tripTemp : Double = 0.0
  
    def act() = {
      while (true) {
        receive {
          case Heartbeat => 0
          case TemperatureAlarm(temp) =>
            tripped = true
            tripTemp = temp
          case _ => println("No match")
        }
      }
    }
  }
