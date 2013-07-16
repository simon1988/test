package thread;

import java.util.concurrent.*;

public class SingleLine{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("zhu");
				try {
					TimeUnit.SECONDS.sleep(6);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("gou");
			}});
		es.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("zhu");
				try {
					TimeUnit.SECONDS.sleep(6);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("gou");
			}});
		es.shutdown();
	}

}
