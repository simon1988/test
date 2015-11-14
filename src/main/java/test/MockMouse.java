package test;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MockMouse {

	public static void main(String[] args) throws Exception{
		Thread.sleep(15000);
		Robot r = new Robot();
		mxdWK(r);
	}
	private static void holdKey(Robot r, int key, int delay){
		long cur = System.currentTimeMillis();
		while(System.currentTimeMillis()-cur<delay){
			r.keyPress(key);
			r.delay(10);
		}
		r.keyRelease(key);
	}
	private static void mxdWK(Robot r) throws Exception{
		while(true){
			holdKey(r, KeyEvent.VK_RIGHT, 3000);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.delay(3000);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_SPACE);
			r.delay(3000);
			r.keyRelease(KeyEvent.VK_SPACE);
			pressMouse(r);
		}
		
	}
	private static void pressMouse(Robot r) throws Exception{
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.delay(10);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
