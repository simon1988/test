package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestCallableAndFuture {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<String> future = threadPool.submit(new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(3000);
				return "Hello";
			};
		});
		System.out.println("Wait for result...");
		ScheduledExecutorService quartz = Executors.newSingleThreadScheduledExecutor();
		quartz.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				System.out.println("quartz started");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("quartz ended");
			}
			
		}, 500, 4000, TimeUnit.MILLISECONDS);
		try {
			System.out.println("Get the result：" + future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
