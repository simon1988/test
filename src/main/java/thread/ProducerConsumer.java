package thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {
	
	ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<Integer>(10);
	Random random = new Random();
	
	public void test(){
		try {
			ExecutorService es = Executors.newFixedThreadPool(2);
			es.execute(new Runnable() {
				@Override
				public void run() {
					while (true){
						try {
							int item = random.nextInt();
							System.out.println("Producer produces: "+item);
							abq.put(item);
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
							System.out.println("Consumer take: "+abq.take());
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
		new ProducerConsumer().test();
	}

}
