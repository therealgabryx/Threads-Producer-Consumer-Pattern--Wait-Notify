import java.util.Random;

class Main {
  static Random rand = new Random();

  public static void main(String[] args) throws InterruptedException {
    MyBlockingQueue queue = new MyBlockingQueue(10);

    final Runnable producer = () -> {
      while (true) {
        try {
          int x = createItem();
          queue.put(x);
          System.out.println("+ ADD " + x);

          Thread.sleep(500);

        } catch (InterruptedException e) { }
      }
    };

    new Thread(producer).start();
    new Thread(producer).start();

    final Runnable consumer = () -> {
      while (true) {
        try {
          Integer i = (Integer) queue.take();
          System.out.println("- DEL " + i);

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