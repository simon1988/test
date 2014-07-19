package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MockMouse {

	public static void main(String[] args) {
		try {
			Thread.sleep(5000);
			final Robot rb = new Robot();
			while (true)
				pressMouse(rb, InputEvent.BUTTON1_MASK, 3000);
//			int[] ks = new int[] { KeyEvent.VK_E, KeyEvent.VK_X, KeyEvent.VK_I, KeyEvent.VK_T, KeyEvent.VK_ENTER };
//			pressKeys(rb, ks, 500);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void pressMouse(Robot r, int m, int delay) {
		r.mousePress(m);
		r.delay(10);
		r.mouseRelease(m);
		r.delay(delay);
	}

	private static void pressKeys(Robot r, int[] ks, int delay) {
		for (int i = 0; i < ks.length; i++) {
			r.keyPress(ks[i]);
			r.delay(10);
			r.keyRelease(ks[i]);
			r.delay(delay);
		}
	}
}
