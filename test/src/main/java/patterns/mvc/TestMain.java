package patterns.mvc;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JColorChooser;

/**
 * Java��CardLayout���ֹ�����ʹ�õ�С����
 *
 * @author �嶷�� <��ת���뱣�����ߺͳ���>
 * @blog http://blog.csdn.net/mq612
 */
public class TestMain extends JFrame {
    private JPanel pane = null; // ��Ҫ��JPanel����JPanel�Ĳ��ֹ��?�����ó�CardLayout
    private JPanel p = null; // �Ű�ť��JPanel
    private CardLayout card = null; // CardLayout���ֹ�����
    private JButton button_1 = null; // ��һ��
    private JButton button_2 = null; // ��һ��
    private JButton b_1 = null, b_2 = null, b_3 = null; // �����ֱ�ӷ�ת��JPanel����İ�ť
    private JPanel p_1 = null, p_2 = null, p_3 = null; // Ҫ�л������JPanel
    
    public TestMain() {
        super("CardLayout Test");
        try {
            // ��LookAndFeel���ó�Windows��ʽ
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /**����һ������ָ����ˮƽ�ʹ�ֱ��϶���¿�Ƭ����*/
        card = new CardLayout(5, 5);
        pane = new JPanel(card); // JPanel�Ĳ��ֹ��?�����ó�CardLayout
        p = new JPanel(); // ����Ű�ť��JPanel
        button_1 = new JButton("< ��һ��");
        button_2 = new JButton("��һ�� >");
        b_1 = new JButton("1");
        b_2 = new JButton("2");
        b_3 = new JButton("3");
        b_1.setMargin(new Insets(2,2,2,2));
        b_2.setMargin(new Insets(2,2,2,2));
        b_3.setMargin(new Insets(2,2,2,2));
        p.add(button_1);
        p.add(b_1);
        p.add(b_2);
        p.add(b_3);
        p.add(button_2);
        p_1 = new JPanel();
        p_2 = new JPanel();
        p_3 = new JPanel();
        p_1.setBackground(Color.RED);
        p_2.setBackground(Color.BLUE);
        p_3.setBackground(Color.GREEN);
        p_1.add(new JLabel("JPanel_1"));
        p_2.add(new JLabel("JPanel_2"));
        p_3.add(new JLabel("JPanel_3"));
        pane.add(p_1, "p1");
        pane.add(p_2, "p2");
        pane.add(p_3, "p3");
        /**�����Ƿ�ת����Ƭ���ֵ�ĳ��������ɲο�API�е��ĵ�*/
        button_1.addActionListener(new ActionListener(){ // ��һ���İ�ť����
            public void actionPerformed(ActionEvent e) {
                card.previous(pane);
            }
        });
        button_2.addActionListener(new ActionListener(){ // ��һ���İ�ť����
            public void actionPerformed(ActionEvent e) {
                card.next(pane);
            }
        });
        b_1.addActionListener(new ActionListener() { // ֱ�ӷ�ת��p_1
            public void actionPerformed(ActionEvent e) {
                card.show(pane, "p1");
            }
        });
        b_2.addActionListener(new ActionListener() { // ֱ�ӷ�ת��p_2
            public void actionPerformed(ActionEvent e) {
                card.show(pane, "p2");
            }
        });
        b_3.addActionListener(new ActionListener() { // ֱ�ӷ�ת��p_3
            public void actionPerformed(ActionEvent e) {
                card.show(pane, "p3");
                Color c=JColorChooser.showDialog(TestMain.this, "wrwr", Color.red);
                if(c!=null)System.out.println(c.toString());
            }
        });
        this.getContentPane().add(pane);
        this.getContentPane().add(p, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new TestMain();
    }
    
}


