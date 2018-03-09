package com.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BlockingQueue<T> {
  private final List<T> list;
  private final Semaphore putSemaphore;
  private final Semaphore takeSemaphore;

  public BlockingQueue(int size) {
    putSemaphore = new Semaphore(size);
    takeSemaphore = new Semaphore(0);
    list = new ArrayList<>();
  }

  public void put(T element) throws InterruptedException {
    // The following lines of code are temporally coupled, do _not_ switch the order
    putSemaphore.acquire();
    putSynchronized(element);
    takeSemaphore.release();
  }

  private synchronized void putSynchronized(T element) {
    list.add(element);
  }

  public T take() throws InterruptedException {
    // The following lines of code are temporally coupled, do _not_ switch the order
    takeSemaphore.acquire();
    T element = takeSynchronized();
    putSemaphore.release();
    return element;
  }

  private synchronized T takeSynchronized() {
    return list.remove(0);
  }

  public int size() {
    return list.size();
  }
}
