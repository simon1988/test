package test;

import java.awt.*;
import javax.swing.*;

public class TestGraphics {

	public static void main(String[] args) {
		myframe my = new myframe();
		my.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		my.setVisible(true);
		System.out.println("zhu");
	}

}

class myframe extends JFrame {
	public myframe() {
		setTitle("yjy hello world!");
		setSize(300, 200);
		mypanel myp = new mypanel();
		Container con = getContentPane();
		con.add(myp);
	}
}

class mypanel extends JPanel {
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawString("hello world!", 75, 50);
	}

}

// end

