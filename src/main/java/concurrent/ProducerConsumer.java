package concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer {
	
	private ExecutorService threadPool = Executors.newCachedThreadPool();
	private ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<Integer>(4);
	private Random random = new Random();
	private int item = 0;
	
	public void test(){
		try {
			threadPool.execute(() -> {
				while (true){
					try {
						abq.put(item++);
						System.out.println("offered: #"+item+", current items in queue:"+abq.size());
						TimeUnit.SECONDS.sleep(random.nextInt(5));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			threadPool.execute(() -> {
				while (true){
					try {
						System.out.println("took item #"+abq.take()+", current items in queue:"+abq.size());
						TimeUnit.SECONDS.sleep(random.nextInt(3));
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
		new ProducerConsumer().test();
	}

}
