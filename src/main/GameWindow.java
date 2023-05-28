package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameWindow {
    
    private JFrame jframe;
    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));

    public GameWindow(GamePanel gamePanel){

        jframe = new JFrame();
        
        jframe.setTitle("Knight2D");
        jframe.setIconImage(icon.getImage());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
		jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        jframe.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
                
            }
            @Override
            public void windowLostFocus(WindowEvent e) {

                gamePanel.getGame().windowFocusLost();
            }
            
        });
    }
}
