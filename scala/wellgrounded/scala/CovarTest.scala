  object CovarTest {
    def main(args : Array[String]) {
      val tiddles = new Cat("tiddles")
      val catList = new MyList(tiddles :: Nil)
      var petList : MyList[Pet] = catList
      
      println(petList);
    }
  }