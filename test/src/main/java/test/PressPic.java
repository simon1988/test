package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PressPic extends JFrame {

	public PressPic(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("demo");
		this.setLocationByPlatform(true);
		JPanel panel=new JPanel();
		this.add(panel);
		panel.addMouseListener(new MyMouseListener());
		ImageIcon image=new ImageIcon("pic.jpg");
		panel.add(new JLabel(image));
		this.setSize(image.getIconWidth(), image.getIconHeight());
	}
	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("270-278,292-312");
		PressPic pp = new PressPic();
		pp.setVisible(true);
	}
	
	class MyMouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			System.out.println(e.getX()+","+e.getY());
			if(e.getX()>=270&&e.getX()<=278&&e.getY()>=292&&e.getY()<=312){
				JOptionPane.showMessageDialog(PressPic.this, "pressed!");
			}
		}
	}

}
