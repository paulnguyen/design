  import scala.actors.Actor._

  object ActorsExample {
    def main(args : Array[String]) {
      val myact = actor {
        while (true) {
          receive {
            case incoming => println("I got mail: "+ incoming)
          }
        }
      }
    }
  }

