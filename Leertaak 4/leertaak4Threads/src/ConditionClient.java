/**
 * Class which manages threads by using conditions.
 * Created by Leon on 10-3-2016.
 */
public class ConditionClient implements Client {
	public static void main(String[] args) {
		new ConditionClient().setup();
	}

	@Override
	public void setup() {
		// Create threads by assigning runnables
		Thread thread1 = new Thread(new ConditionThread());
		Thread thread2 = new Thread(new ConditionThread());
		Thread thread3 = new Thread(new ConditionThread());
		Thread thread4 = new Thread(new ConditionThread());

		// Start threads
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
}
