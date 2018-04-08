package concurrency;

public class BadThreads {

    static String message;

    private static class CorrectorThread extends Thread {

	public void run() {
	    try {
		sleep(1000);
	    } catch (InterruptedException e) {
	    }
	    // Key statement 1:
	    message = "Mares do eat oats.";
	    System.out.println("Thread is running!!");
	}
    }

    public static void main(String args[]) throws InterruptedException {

	(new CorrectorThread()).start();
	message = "Mares do not eat oats2.";
	Thread.sleep(1000);
	// Key statement 2:
	System.out.println(message);
    }
}