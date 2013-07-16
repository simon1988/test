package test;

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicArrowButton;

public class TestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 131144668946860628L;

	public TestFrame(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Frame");
		setBounds(200, 200, 200, 200);
		setLayout(new FlowLayout());
		add(new BasicArrowButton(BasicArrowButton.EAST));
		add(new JToggleButton("toggle"));
		add(new JRadioButton("radio"));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				TestFrame frame = new TestFrame();
				
				frame.setVisible(true);
			}
			
		});
		
	}

}
