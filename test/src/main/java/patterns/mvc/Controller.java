package patterns.mvc;

import java.awt.*;

public class Controller extends Frame {
    
    static Model model = new Model();
    static View view = new View();

    public static void main(String args[]) {
        Frame f = new Frame();
        f.add(view);
        f.setSize(200, 220);
        f.setVisible(true);
        
        model.addObserver(view);
        
        for (int i = 0; i < 200; i++) {
            model.doSomething();
            try { Thread.sleep(20); } catch (InterruptedException e) {}
        }
        f.dispose();
    }
}

