package thread;
import java.util.concurrent.*;
public class ThreadPoolTest implements Runnable, Callable<String> {

	private String name;
	public ThreadPoolTest(String s){
		this.name=s;
	}
	@Override
	public void run() {
		System.out.println("I am "+name);
	}
	@Override
	public String call() throws Exception {
		return "call "+this.name;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScheduledExecutorService ses=Executors.newScheduledThreadPool(2);
		ses.schedule((Runnable)new ThreadPoolTest("thread 1"), 3, TimeUnit.SECONDS);
		ses.scheduleAtFixedRate((Runnable)new ThreadPoolTest("thread 2"), 0, 1, TimeUnit.SECONDS);
		Future<String> future = ses.submit((Callable<String>)new ThreadPoolTest("me"));
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		ses.shutdown();
	}

}
