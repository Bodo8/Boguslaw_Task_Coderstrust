package pl.coderstrust.thread.producerconsumer;

class Consumer extends Thread {

  private ProducerBuffer buffer;
  private String consumerName;

  public Consumer(ProducerBuffer buffer, String consumerName) {
    this.buffer = buffer;
    this.consumerName = consumerName;
  }

  @Override
  public void run() {
    String value;
    while (true) {
      value = buffer.get();
      System.out.println("Consumer #" + this.consumerName + " got: " + value);
      try {
        sleep(500);
      } catch (InterruptedException e) {
      }
    }
  }
}

