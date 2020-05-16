import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Main {
  private int max;
  private Queue<E> queue = new LinkedList<>();
  private Object notEmpty = new Object();
  private Object notFull = new Object();

  public void MyBlockingQueue(ins size) {
    queue = new LinkedList<>();
    this.max = size;
  }

  public synchronized void put(E e) {
    while (queue.size() == max) {
      notFull.wait();
    }
    queue.add(e);
    notEmpty.notifyAll();
  }

  public synchronized E take() {
    if (queue.size() == 0) {
      notEmpty.wait();
    }
    E Item = queue.remove();
    notFull.notifyAll();
    return item;
  }

  public static void main(String[] args) {
    MyBlockingQueue<Item> queue = new ArrayBlockingQueue<>(10);

    // Producer
    final Runnable producer = () -> {
      while (true) {
        queue.put(createItem());    // Thread blocks if queue full 
      }
    };
    new Thread(producer).start();
    new Thread(producer).start();

    // Consumer
    final Runnable consumer = () -> {
      while (true) {
        Item i = queue.take();    // Thread blocks if queue empty 
        process(i);
      }
    };
    new Thread(consumer).start();
    new Thread(consumer).start();

    Thread.sleep(1000);

    // try:catch for take and put skipped for brevity 
  }
}