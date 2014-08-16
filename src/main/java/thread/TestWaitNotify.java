package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class JobClass {
	
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
}

public class TestWaitNotify {

	JobClass jobClass = new JobClass();

	public void test() {
		try {

			ExecutorService es = Executors.newCachedThreadPool();
			es.execute(new Runnable() {

				@Override
				public void run() {
					while (true){
						try {
							jobClass.A();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			});
			es.execute(new Runnable() {

				@Override
				public void run() {
					while (true){
						try {
							jobClass.B();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestWaitNotify test = new TestWaitNotify();
		test.test();
	}

}
