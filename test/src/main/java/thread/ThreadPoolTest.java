package thread;
import java.util.concurrent.*;
public class ThreadPoolTest implements Runnable {

	private String name;
	public ThreadPoolTest(String s){
		this.name=s;
	}
	@Override
	public void run() {
		System.out.println("I am "+name);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScheduledExecutorService ses=Executors.newScheduledThreadPool(2);
		ses.schedule(new ThreadPoolTest("thread 1"), 2, TimeUnit.SECONDS);
		ses.schedule(new ThreadPoolTest("thread 2"), 4, TimeUnit.SECONDS);
		ses.scheduleAtFixedRate(new ThreadPoolTest("thread 3"), 0, 1, TimeUnit.SECONDS);

		ses.shutdown();
	}

}
