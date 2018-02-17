package pl.coderstrust.thread.producerconsumer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Producer extends Thread {

  private ProducerBuffer buffer;
  private String producerName;

  public Producer(ProducerBuffer buffer, String producerName) {
    this.buffer = buffer;
    this.producerName = producerName;
  }

  @Override
  public void run() {
    while (true) {
      String value = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss.SSS"));
      buffer.put(value);
      System.out.println("Producer #" + this.producerName + " put: " + value);
      try {
        sleep((int) (Math.random() * 1000));
      } catch (InterruptedException e) {
      }
    }
  }
}
