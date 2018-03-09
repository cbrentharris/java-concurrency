package com.example.threads;

import java.lang.Thread;
import java.math.BigInteger;
import com.example.BlockingQueue;

public class PrimeProducerThread extends Thread {
  private final BlockingQueue<BigInteger> queue;

  public PrimeProducerThread(BlockingQueue<BigInteger> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
        BigInteger p = BigInteger.ONE;
        while (!isInterrupted()) {
          queue.put(p = p.nextProbablePrime());
        }
    } catch (InterruptedException ex) {
      interrupt();
    }
  }

  public void cancel() {
    interrupt();
  }
}
