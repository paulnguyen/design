  class Queue[T](elts : T*) {
    // This is a way of passing all the items in _elems into the constructor for List
    var elems = List[T](elts : _*) 

    def enqueue(elem : T) = elems ::: List(elem)

    def dequeue = {
      val result = elems.head
      elems = elems.tail
      result    
    }
  }
  
