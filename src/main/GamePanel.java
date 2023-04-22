package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;


public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
	private int xDelta = 200, yDelta = 200;
	private BufferedImage img, subImg;

    
    public GamePanel(){
        mouseInputs = new MouseInputs(this);
		importImg();

		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
    }
    
    private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setPreferredSize(size);
	}

	private void importImg() {
			File f = new File("res/playersheet.png");
	
			try {
				img = ImageIO.read(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}

	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}

	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        subImg = img.getSubimage(2 * 120, 0 * 82, 120, 82);
		g.drawImage(subImg, (int) xDelta, (int) yDelta, 240, 164, null);
	}
}
