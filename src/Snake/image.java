package Snake;

import javax.swing.*;
import java.awt.*;

public class image extends JFrame {
    Image tou = Toolkit.getDefaultToolkit().getImage("Image/-crocodile.png");
    public void paint(Graphics g){
        g.drawImage(tou,0,0,null);
    }
}
