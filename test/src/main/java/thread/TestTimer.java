package thread;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {

	private Timer timer=new Timer();
	private Random random=new Random();
	
	private void dothis(){
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				System.out.println("I am the best!");
				if(random.nextInt(100)==99)timer.cancel();
			}
			
		}, 1000, 100);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestTimer tt=new TestTimer();
		tt.dothis();
	}

}
