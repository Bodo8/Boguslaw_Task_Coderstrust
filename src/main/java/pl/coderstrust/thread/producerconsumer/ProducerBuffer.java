package pl.coderstrust.thread.producerconsumer;

import java.util.LinkedList;

class ProducerBuffer {

  private LinkedList<String> contents;
  private int maxBufferSize;

  public ProducerBuffer(int size) {
    this.maxBufferSize = size;
    this.contents = new LinkedList<>();
  }

  public synchronized String get() {
    while (isEmpty()) {
      try {
        System.out.println("Buffer is empty");
        wait();
      } catch (InterruptedException e) {
      }
    }
    String content = contents.removeFirst();
    notifyAll();
    return content;
  }

  public synchronized void put(String value) {
    while (isFull()) {
      try {
        System.out.println("Buffer is full");
        wait();
      } catch (InterruptedException e) {
      }
    }
    contents.add(value);
    notifyAll();
  }

  private boolean isFull() {
    return contents.size() == maxBufferSize;
  }

  private boolean isEmpty() {
    return contents.size() == 0;
  }
}