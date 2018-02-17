package pl.coderstrust.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class BlockingQueueTest {

  public static void main(String[] args) throws Exception {

    BlockingQueue queue = new ArrayBlockingQueue(1024);

    BlockingQueueProducer producer = new BlockingQueueProducer(queue, "Producer 1");
    BlockingQueueProducer producer2 = new BlockingQueueProducer(queue, "Producer 2");
    BlockingQueueProducer producer3 = new BlockingQueueProducer(queue, "Producer 3");
    BlockingQueueConsumer consumer = new BlockingQueueConsumer(queue, "Consumer 1");
    BlockingQueueConsumer consumer2 = new BlockingQueueConsumer(queue, "Consumer 2");

    startThread(new Thread(producer));
    startThread(new Thread(consumer));
    startThread(new Thread(producer2));
    startThread(new Thread(producer3));
    startThread(new Thread(consumer2));
  }

  static void startThread(Thread thread) throws InterruptedException {
    thread.start();
  }
}

