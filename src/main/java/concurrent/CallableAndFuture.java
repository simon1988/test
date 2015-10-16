package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class CallableAndFuture {

	public static void main(String[] args) throws Exception {
		ExecutorService oneTimeThreadPool = Executors.newCachedThreadPool();
		
		oneTimeThreadPool.execute(new RunnableTask(1, 1000));
		oneTimeThreadPool.execute(new RunnableTask(2, 4000));
		oneTimeThreadPool.shutdown();
		System.out.println("Wait for result...");
		//must shutdown before awaitTermination
		boolean finished = oneTimeThreadPool.awaitTermination(3l, TimeUnit.SECONDS);
		if(!finished){
			System.out.println("timeout, shut down pool now...");
			oneTimeThreadPool.shutdownNow();
		}
		System.out.println("----------------------------------");
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		List<Callable<Integer>> list = new ArrayList<>();
		list.add(new CallableTask(1, 1000));
		list.add(new CallableTask(2, 4000));
		List<Future<Integer>> results = threadPool.invokeAll(list, 3l, TimeUnit.SECONDS);
		
		for(int i=0;i<results.size();i++){
			Future<Integer> future = results.get(i);
			if(future.isCancelled()){
				System.out.println("canceled callable #"+((CallableTask)list.get(i)).getId());
			}else{
				System.out.println("finshed callable #"+future.get());
			}
		}
		
		Future<Integer> future = threadPool.submit(new CallableTask(3, 1000));
		System.out.println("finshed callable #"+future.get());
		threadPool.shutdown();
	}
}
