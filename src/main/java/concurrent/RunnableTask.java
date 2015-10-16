package concurrent;

public class RunnableTask implements Runnable {
	private int id;
	private int delay;
	public RunnableTask(int id, int delay){
		this.id = id;
		this.delay = delay;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(delay);
			System.out.println(Thread.currentThread()+":Runnable #"+id+" finished");
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread()+":Runnable #"+id+" interupted");
		}
	}
}
