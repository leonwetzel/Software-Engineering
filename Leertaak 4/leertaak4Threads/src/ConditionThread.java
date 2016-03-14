import java.util.concurrent.locks.Lock;

/**
 * Worker class.
 * Created by Leon on 10-3-2016.
 */
public class ConditionThread implements Runnable {
	private static int amount = 5;
	private static Lock lock;

	public ConditionThread() {}

	/**
	 * Prints the amount twice in the command line.
	 */
	@Override
	public void run() {
		if(lock.tryLock()) {

		}
	}
}