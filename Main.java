import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Main {
  public static void main(String[] args) {
    BlockingQueue<Item> queue = new ArrayBlockingQueue<>(10);

    // Producer
    final Runnable producer = () -> {
      while (true) {
        queue.put(createItem());
      }
    };
    new Thread(producer).start();
    new Thread(producer).start();

    // Consumer
    final Runnable consumer = () -> {
      while (true) {
        Item i =
      }
    }



  }
}