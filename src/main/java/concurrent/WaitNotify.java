package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaitNotify {

	private int i = 0;

	synchronized public void A() throws InterruptedException{
		while (i % 2 == 1){
			this.wait();
		}
		System.out.println("Thread A: i=" + i++);
		TimeUnit.SECONDS.sleep(1);
		notify();
		System.out.println("Thread A: After notifiy");
		TimeUnit.MILLISECONDS.sleep(5000);
	}

	synchronized public void B() throws InterruptedException {
		while (i % 2 == 0){
			wait();
		}
		System.out.println("Thread B: i=" + i++);
		TimeUnit.SECONDS.sleep(3);
		notify();
		System.out.println("Thread B: After notifiy");
	}

	public void test() {
		try {
			ExecutorService threadPool = Executors.newCachedThreadPool();
			threadPool.execute(() -> {
				while (true){
					try {
						A();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			threadPool.execute(() -> {
				while (true){
					try {
						B();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			threadPool.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		WaitNotify test = new WaitNotify();
		test.test();
	}

}
