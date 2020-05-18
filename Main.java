import java.util.Random;

class Main {
  static Random rand = new Random();

  public static void main(String[] args) throws InterruptedException {
    MyBlockingQueue queue = new MyBlockingQueue(10);

    final Runnable producer = () -> {
      for (int i = 0; i < 10; i++) { 
        try {
          int x = createItem();
          queue.put(x);

          Thread.sleep(300);

        } catch (InterruptedException e) { }
      }
    };

    new Thread(producer).start();
    new Thread(producer).start();

    final Runnable consumer = () -> {
      for (int i = 0; i < 10; i++) {
        try {
          queue.take();

          Thread.sleep(500);

        } catch (InterruptedException e) { }
      }
    };

    new Thread(consumer).start();
    new Thread(consumer).start();
  }

  public static int createItem() {
    int x = rand.nextInt(9) + 1;
    return x;
  }
}