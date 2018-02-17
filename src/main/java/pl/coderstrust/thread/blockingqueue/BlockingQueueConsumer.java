package pl.coderstrust.thread.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class BlockingQueueConsumer extends Thread {

  protected BlockingQueue queue = null;
  String consumerName;

  public BlockingQueueConsumer(BlockingQueue queue, String consumerName) {

    this.queue = queue;
    this.consumerName = consumerName;
  }

  public void run() {

    try {
      while (true) {
        if (queue.isEmpty()) {
          System.out.println("Queue is empty.");
        } else {
          System.out.println("Consumer #" + this.consumerName + " got: " + queue.take());
          Thread.sleep(1000);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
