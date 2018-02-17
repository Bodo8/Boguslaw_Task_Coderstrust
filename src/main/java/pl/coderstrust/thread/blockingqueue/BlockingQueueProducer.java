package pl.coderstrust.thread.blockingqueue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueProducer implements Runnable {

  protected BlockingQueue queue = null;
  protected String producerName;

  public BlockingQueueProducer(BlockingQueue queue, String producerName) {
    this.queue = queue;
    this.producerName = producerName;
  }

  public void run() {
    try {
      while (true) {
        if (queue.remainingCapacity() == 0) {
          System.out.println("Queue id full.");
        } else {
          String value = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss.SSS"));
          queue.put(new Product(value));
          System.out.println("Producer #" + this.producerName + " put: " + value);
          Thread.sleep(1000);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}


