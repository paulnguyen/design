  object CashMain {
    def main(args : Array[String]) {
      val wages = new CashFlow(2000.0)
      println(wages.amount)
      println(wages.currency)
    }
  }