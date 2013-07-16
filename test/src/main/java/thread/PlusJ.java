package thread;

import java.util.Random;
import java.util.concurrent.*;

public class PlusJ {

	int j=0;
	CyclicBarrier barrier = new CyclicBarrier(1);
	Random random = new Random();
	
	private void plus1(){
		synchronized(this){
			
			try {
				barrier.await();
				while(j>=10){
					wait();
				}
				TimeUnit.MILLISECONDS.sleep(random.nextInt(200));
			System.out.println(Thread.currentThread().getName()+":plus j from "+j+" to "+(++j));
			this.notify();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	private synchronized void minus1(){
		//synchronized(this.getClass()){
		try {
			barrier.await();
		while(j<=0){
			wait();
		}
		TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
			System.out.println(Thread.currentThread().getName()+":minus j from "+j+" to "+(--j));
			this.notify();
				//TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
 catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	class PlusThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<10000;i++)
			plus1();
		}
		
	}
	
	class MinusThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<10000;i++)
			minus1();
		}
		
	}
	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlusJ plusj = new PlusJ();
		ExecutorService exec = Executors.newCachedThreadPool();
		try{
			exec.execute(plusj.new PlusThread());
			exec.execute(plusj.new PlusThread());
			exec.execute(plusj.new MinusThread());
			exec.execute(plusj.new MinusThread());
			TimeUnit.SECONDS.sleep(5);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
