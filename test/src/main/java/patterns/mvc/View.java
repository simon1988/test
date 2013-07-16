package patterns.mvc;

import java.awt.*;
import java.util.*;

public class View extends Canvas implements Observer {
    
    Color color;
    
    public void paint(Graphics g) {
        if (color ==  null) return;
        g.setColor(color);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
    
    public void update(Observable observable, Object arg) {
        color = ((Model)observable).color;
        repaint();
    }
}

