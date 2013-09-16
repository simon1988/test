package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Test0 {
	private int i = 0;

	synchronized public void A() {
		try {
			while (i % 2 == 1)
				this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("AA" + i++);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notify();
		System.out.println("AB");
		try {
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized public void B() {
		try {
			while (i % 2 == 0)
				wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("BA" + i++);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notify();
		// System.out.println("BB"+i++);
	}
}

class Test1 implements Runnable {

	@Override
	public void run() {

	}

}

public class TestWaitNotify {

	Test0 test0 = new Test0();

	public void test() {
		try {

			ExecutorService es = Executors.newCachedThreadPool();
			es.execute(new Runnable() {

				@Override
				public void run() {
					while (true)
						test0.A();

				}

			});
			es.execute(new Runnable() {

				@Override
				public void run() {
					while (true)
						test0.B();

				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestWaitNotify test = new TestWaitNotify();
		test.test();
	}

}
