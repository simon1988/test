package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallableAndFuture {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<String> future = threadPool.submit(new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Hello";
			};
		});
		System.out.println("Wait for result...");
		try {
			System.out.println("Get the result：" + future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
