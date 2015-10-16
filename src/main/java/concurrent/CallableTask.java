package concurrent;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {
	private int id;
	private int delay;
	public CallableTask(int id, int delay){
		this.id = id;
		this.delay = delay;
	}
	public int getId(){
		return this.id;
	}
	@Override
	public Integer call() throws Exception {
		try {
			Thread.sleep(delay);
			System.out.println(Thread.currentThread()+":Callable #"+id+" finished");
			return id;
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread()+":Callable #"+id+" interupted");
			return -1;
		}
	}
}
