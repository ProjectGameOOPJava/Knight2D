import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    public GamePanel(){

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(200, 200, 200, 100);
       
    }
}
