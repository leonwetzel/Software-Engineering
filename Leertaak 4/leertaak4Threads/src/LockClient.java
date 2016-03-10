/**
 * Class which manages threads.
 * Created by Leon on 10-3-2016.
 */
public class LockClient {
	public static void main(String[] args) {
		// Create threads by assigning runnables
		Thread thread1 = new Thread(new LockThread());
		Thread thread2 = new Thread(new LockThread());
		Thread thread3 = new Thread(new LockThread());
		Thread thread4 = new Thread(new LockThread());

		// Start threads
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
}
