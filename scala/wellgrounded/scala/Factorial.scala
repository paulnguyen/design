  object Factorial {
    def fact(base : Int) : Int = {
      if (base <= 0) 
        return 1
      else
        return base * fact(base - 1)
    }
    
    def len(obj : AnyRef) = {
      obj.toString.length
    }
    
    def main(args : Array[String]) {
      for (i <- -1 to 10) println(fact(i))
      
      val xs = for (x <- 2 to 11) yield fact(x)
      for (factx <- xs) println(factx)
      
//      println(xs.mkString("\n"))
      
      println(len("foo"))
      println(len("quux"))
    }
  }
