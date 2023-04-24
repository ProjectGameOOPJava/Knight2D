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

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
	private int xDelta = 200, yDelta = 200;
	private BufferedImage img;
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 30;
	private int playerAction = IDLE;
	private int playerDir = -1;
	private boolean moving = false;
    
    public GamePanel(){
        mouseInputs = new MouseInputs(this);
		importImg();
		loadAnimations();

		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
    }
    
	private void loadAnimations() {
		animations = new BufferedImage[8][10];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 120, j * 82, 120, 82);
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

	public void setDirection(int direction) {
		this.playerDir = direction;
		moving = true;
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction))
				aniIndex = 0;
		}

	}

	private void setAnimation() {
		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;
	}

	private void updatePos() {
		if (moving) {
			switch (playerDir) {
			case LEFT:
				xDelta -= 5;
				break;
			case UP:
				yDelta -= 5;
				break;
			case RIGHT:
				xDelta += 5;
				break;
			case DOWN:
				yDelta += 5;
				break;
			}
		}
	}

	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
    }

	public void updateGame() {

		updateAnimationTick();
		setAnimation();
		updatePos();
	}
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, 240, 164, null);
	}
}
