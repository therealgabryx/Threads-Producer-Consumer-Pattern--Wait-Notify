import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class MyBlockingQueue<Integer> {
    private int max;
    private Queue<Integer> queue = new LinkedList<Integer>();
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    
    public MyBlockingQueue(int size) {
      this.queue = new LinkedList<>();
      this.max = size;
    }

  public void put(Integer e) throws InterruptedException {
    lock.lock();
    try {
      while (queue.size() == max) {
        notFull.await();
      }
      queue.add(e);
      System.out.println("+ Piatto aggiunto! " + e);
      notEmpty.signalAll();
    } finally { 
      lock.unlock();
    } 
  }

  public Integer take() throws InterruptedException {
    lock.lock();
    try {
      while (queue.size() == 0) {
        notEmpty.await(); 
      }
      Integer item = queue.remove();
      System.out.println("- Piatto tolto..  " + item);
      notFull.signalAll();
      return item;
    } finally {
      lock.unlock();
    }
  }
}