package com.example.threads;

import java.lang.Thread;
import java.math.BigInteger;
import com.example.BlockingQueue;

public class PrimeConsumerThread extends Thread {
    private final BlockingQueue<BigInteger> queue;

    public PrimeConsumerThread(BlockingQueue<BigInteger> queue) {
      this.queue = queue;
    }

    @Override
    public void run() {
      try {
        while (!isInterrupted()) {
          BigInteger prime = queue.take();
          System.out.println("Took prime: " + prime);
        }
      } catch (InterruptedException ex) {
        interrupt();
      }
    }

    /**
     * Standardizing against tasks and threads, using a general nomenclature
     * of "cancel" instead of "cancel" + "interrupt"
     */
    public void cancel() {
      interrupt();
    }
}
