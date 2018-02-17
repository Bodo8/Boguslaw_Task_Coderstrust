package pl.coderstrust.thread.producerconsumer;

public class ProducerConsumerTest {

  public static void main(String[] args) throws InterruptedException {
    ProducerBuffer buffer = new ProducerBuffer(5);
   Producer producer1 = new Producer(buffer, "Producer1");
    Producer producer2 = new Producer(buffer, "Producer2");
    Consumer consumer1 = new Consumer(buffer, "Consumer1");
    Consumer consumer2 = new Consumer(buffer, "Consumer2");

    startThread(producer1);
    startThread(producer2);
    startThread(consumer1);
    startThread(consumer2);
  }

  static void startThread(Thread thread) throws InterruptedException {
    thread.start();
  }
}

