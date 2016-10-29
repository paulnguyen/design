/***
 * Excerpted from "Seven Concurrency Models in Seven Weeks",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/pb7con for more book information.
***/

public class DiningPhilosophers2 {

  public static void main(String[] args) throws InterruptedException {
    Philosopher2[] philosophers = new Philosopher2[5];
    Chopstick2[] chopsticks = new Chopstick2[5];
    
    for (int i = 0; i < 5; ++i)
      chopsticks[i] = new Chopstick2(i);
    for (int i = 0; i < 5; ++i) {
      philosophers[i] = new Philosopher2(chopsticks[i], chopsticks[(i + 1) % 5]);
      philosophers[i].start();
    }
    for (int i = 0; i < 5; ++i)
      philosophers[i].join();
  }
}
