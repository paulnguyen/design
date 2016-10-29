/***
 * Excerpted from "Seven Concurrency Models in Seven Weeks",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/pb7con for more book information.
***/

import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers4 {

  public static void main(String[] args) throws InterruptedException {
    Philosopher4[] philosophers = new Philosopher4[5];
    ReentrantLock table = new ReentrantLock();
    
    for (int i = 0; i < 5; ++i)
      philosophers[i] = new Philosopher4(table);
    for (int i = 0; i < 5; ++i) {
      philosophers[i].setLeft(philosophers[(i + 4) % 5]);
      philosophers[i].setRight(philosophers[(i + 1) % 5]);
      philosophers[i].start();
    }
    for (int i = 0; i < 5; ++i)
      philosophers[i].join();
  }
}
