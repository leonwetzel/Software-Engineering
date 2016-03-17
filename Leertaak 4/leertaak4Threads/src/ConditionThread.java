import java.util.concurrent.locks.Condition;

/**
 * Worker class.
 * Created by Leon on 10-3-2016.
 */
public class ConditionThread implements Runnable {
	private static int amount = 5;
	private Condition con;

	public ConditionThread() {}

	/**
	 * Prints the amount twice in the command line.
	 */
	@Override
	public void run() {
		System.out.println();
	}
}