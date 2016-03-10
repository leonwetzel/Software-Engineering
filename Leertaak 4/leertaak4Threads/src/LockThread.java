/**
 * Worker class.
 * Created by Leon on 10-3-2016.
 */
public class LockThread implements Runnable {
	private final static int LIMIT = 4;

	private static int amount = 0;
	private final static Object lock = new Object();

	public LockThread() {

	}

	/**
	 * Prints the amount twice in the command line.
	 */
	@Override
	public void run() {
		synchronized (lock) {
			amount++;
			System.out.println(amount + "" + amount);
		}
	}
}
