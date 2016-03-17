// Exercise29_4.java: 1000 threads, each adds 1 to a variable sum
import java.util.concurrent.*;

public class Test {
	private int sum;

	public static void main(String[] args) {
		Test test = new Test();
		//System.out.println("What is sum ? " + test.sum);
	}

	public Test() {
		ExecutorService executor = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 4; i++) {
			executor.execute(new SumTask());
		}

		executor.shutdown();

		while(!executor.isTerminated()) {
		}
	}

	class SumTask implements Runnable {
		public void run() {
			//int value = sum++;
			sum++;
			System.out.println(sum + "" + sum);
		}
	}
}