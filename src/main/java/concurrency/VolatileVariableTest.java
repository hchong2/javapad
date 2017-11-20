package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

import org.jboss.logging.Logger;
import org.junit.Test;

public class VolatileVariableTest {
    Logger logger = Logger.getLogger(VolatileVariableTest.class);

    static volatile int volatileInt = 0;
    static int normalInt = 0;

    @Test
    public void testVolatileInc() {
	long start = System.currentTimeMillis();

	for (int i = 0; i < 500_000; i++) {
	    volatileInt++;
	}
	long end = System.currentTimeMillis();
	logger.info("Time elapsed(Volatile Integer): " + (end - start) + " milliseconds");
    }

    @Test
    public void testNormalIntIncrement() {
	long start = System.currentTimeMillis();
	int count = 0;
	for (int i = 0; i < 500_000; i++) {
	    count++;
	}
	long end = System.currentTimeMillis();
	logger.info("Time elapsed(Normal Int): " + (end - start) + " milliseconds");
    }

    @Test
    public void testAtomicIntIncrement() {
	long start = System.currentTimeMillis();

	AtomicInteger atomicInt = new AtomicInteger();
	for (int i = 0; i < 100; i++) {
	    atomicInt.incrementAndGet();
	}
	long end = System.currentTimeMillis();
	logger.info("Time elapsed(AtomicInteger): " + (end - start) + " milliseconds");
    }
}
