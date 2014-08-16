package patterns.mvc;

import java.awt.*;
import java.util.*;
    
public class Model extends Observable {
    
    public Color color = new Color(0x010000);
    private int count = 10;
    
    void doSomething() {
        if (--count <= 0) {
            color = color.brighter();
            count = 10;
            // 
            setChanged();
            notifyObservers();
        }
    }
}

