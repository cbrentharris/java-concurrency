import java.math.BigInteger;
import com.example.BlockingQueue;
import com.example.threads.PrimeProducerThread;
import com.example.threads.PrimeConsumerThread;
import org.junit.Test;
import org.junit.Assert;

public class ProducerConsumerThreadTest {
  @Test
  public void testProducerConsumerThreads() throws InterruptedException {
    final int size = 20;
    final BlockingQueue<BigInteger> queue = new BlockingQueue<>(size);
    final PrimeProducerThread producer = new PrimeProducerThread(queue);
    final PrimeConsumerThread consumer = new PrimeConsumerThread(queue);

    producer.start();

    try {
      Thread.sleep(1000);
    } finally {
      Assert.assertEquals(producer.getState(), Thread.State.WAITING);
      producer.cancel();
    }

    Assert.assertEquals(queue.size(), size);

    consumer.start();

    try {
      Thread.sleep(1000);
    } finally {
      Assert.assertEquals(consumer.getState(), Thread.State.WAITING);
      consumer.cancel();
    }

    Assert.assertEquals(queue.size(), 0);
  }
}
