  object TemperatureExample {
    def ctorMatchExample(sthg : AnyRef) = {
      val msg = sthg match {
        case Heartbeat => 0
        case TemperatureAlarm(temp) => "Tripped at temp "+ temp
        case _ => "No match"
      }
      println(msg)
    }
  
    def main(args : Array[String]) {
      val suze = TemperatureAlarm(99.9)
    
      ctorMatchExample(Heartbeat)
      ctorMatchExample(suze)
      ctorMatchExample("Diagnostic Message")
    }
  }