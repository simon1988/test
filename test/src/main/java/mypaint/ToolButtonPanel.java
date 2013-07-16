package mypaint;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ToolButtonPanel extends JPanel
{
	static final long serialVersionUID=0;
	private JButton lineBtn, squareBtn, ovalBtn, polygonBtn, roundRectBtn, freeHandBtn, undoBtn, redoBtn, clearBtn;		
	
	private JCheckBox fullChk;
	private CanvasPanel canvasPanel;
	private JComboBox jcb;
	
	private JButton getPicResource(String filename){
		return new JButton("",new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(filename)));
	}
	public ToolButtonPanel(CanvasPanel inCanvasPanel)
	{
		canvasPanel = inCanvasPanel;
/*----------------------------------------------------------------------------*/		 
		lineBtn			= getPicResource("images/lineBtn.gif");
		squareBtn		= getPicResource("images/squareBtn.gif");
		ovalBtn	 		= getPicResource("images/ovalBtn.gif");
		polygonBtn		= getPicResource("images/polygonBtn.gif");
		roundRectBtn	= getPicResource("images/roundRectBtn.gif");
		freeHandBtn		= getPicResource("images/freeHandBtn.gif");
		undoBtn			= getPicResource("images/undoBtn.gif");
		redoBtn			= getPicResource("images/redoBtn.gif");
		clearBtn		= getPicResource("images/clearBtn.gif");
		
		lineBtn.addActionListener(new ToolButtonListener());
		lineBtn.setToolTipText("Line");
		squareBtn.addActionListener(new ToolButtonListener());
		squareBtn.setToolTipText("Retangle");
		ovalBtn.addActionListener(new ToolButtonListener());
		ovalBtn.setToolTipText("Oval");
		polygonBtn.addActionListener(new ToolButtonListener());
		polygonBtn.setToolTipText("Polygon");
		roundRectBtn.addActionListener(new ToolButtonListener());
		roundRectBtn.setToolTipText("Rectangle");
		freeHandBtn.addActionListener(new ToolButtonListener());
		freeHandBtn.setToolTipText("Free Hand");
		undoBtn.addActionListener(new ToolButtonListener());
		undoBtn.setToolTipText("Undo");
		redoBtn.addActionListener(new ToolButtonListener());
		redoBtn.setToolTipText("Redo");
		clearBtn.addActionListener(new ToolButtonListener());
		clearBtn.setToolTipText("Clear Canvas");
/*----------------------------------------------------------------------------*/		
		fullChk = new JCheckBox("Full");
		fullChk.addItemListener(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent event)
				{
					if(fullChk.isSelected())
						canvasPanel.setSolidMode(Boolean.TRUE);
					else
						canvasPanel.setSolidMode(Boolean.FALSE);
				}	
			}
		);
		
		String s[]=new String[24];
		for(int i=1;i<=24;i++)s[i-1]=i+"";
		jcb = new JComboBox(s); 
        jcb.setSelectedIndex(0); 
        jcb.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                JComboBox cb = (JComboBox)e.getSource(); 
                String cho = (String)cb.getSelectedItem(); 
                canvasPanel.setPensize(Integer.parseInt(cho));
            } 
        });
/*----------------------------------------------------------------------------*/		
		this.setLayout(new GridLayout(1,10)); // 8 Buttons & 1 CheckBox & 1 select
		this.add(lineBtn);
		this.add(squareBtn);
		this.add(ovalBtn);
		this.add(polygonBtn);
		this.add(roundRectBtn);
		this.add(freeHandBtn);
		this.add(undoBtn);
		this.add(redoBtn);
		this.add(clearBtn);
		this.add(fullChk);	
		this.add(jcb);
	}
/*----------------------------------------------------------------------------*/
	private class ToolButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{	
			if(canvasPanel.isExistPolygonBuffer()!= false)
			{
				canvasPanel.flushPolygonBuffer();
			}
			if(event.getSource() == lineBtn)
			{
				canvasPanel.setDrawMode(CanvasPanel.LINE);		
			}
			if(event.getSource() == squareBtn)
			{
				canvasPanel.setDrawMode(CanvasPanel.SQUARE);
			}
			if(event.getSource() == ovalBtn)
			{
				canvasPanel.setDrawMode(CanvasPanel.OVAL);
			}
			if(event.getSource() == polygonBtn)
			{
				canvasPanel.setDrawMode(CanvasPanel.POLYGON);
			}
			if(event.getSource() == roundRectBtn)
			{
				canvasPanel.setDrawMode(CanvasPanel.TEXT);
			}
			if(event.getSource() == freeHandBtn)
			{
				canvasPanel.setDrawMode(CanvasPanel.FREE_HAND);
			}
			if(event.getSource() == undoBtn)
			{
				canvasPanel.undo();
			}
			if(event.getSource() == redoBtn)
			{
				canvasPanel.redo();
			}
			if(event.getSource() == clearBtn)
			{
				canvasPanel.clearCanvas();
			}
		}
	}
}
