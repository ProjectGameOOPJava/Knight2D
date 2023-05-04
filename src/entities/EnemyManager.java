package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] snailArr;
	private ArrayList<Snail> snails = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();

	}

	public void loadEnemies(Level level) {
		snails = level.getSnails();
	}

	public void update(int[][] lvlData, Player player) {
		boolean isAnyActive = false;
		for (Snail c : snails)
			if (c.isActive()) {
				c.update(lvlData, player);
				isAnyActive = true;
			}
		if(!isAnyActive)
			playing.setLevelCompleted(true);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawSnails(g, xLvlOffset);
	}

	private void drawSnails(Graphics g, int xLvlOffset) {
		for (Snail c : snails)
			if (c.isActive()) {
				g.drawImage(snailArr[c.getState()][c.getAniIndex()], 
						(int) c.getHitbox().x - xLvlOffset - SNAIL_DRAWOFFSET_X + c.flipX(), 
						(int) c.getHitbox().y - SNAIL_DRAWOFFSET_Y,
						SNAIL_WIDTH * c.flipW(), SNAIL_HEIGHT, null);
			c.drawHitbox(g, xLvlOffset);
		}

	}

	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Snail c : snails)
			if (c.isActive())
				if (attackBox.intersects(c.getHitbox())) {
					c.hurt(10);
					return;
				}
	}

	private void loadEnemyImgs() {
		snailArr = new BufferedImage[3][8];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SNAIL_SPRITE);
		for (int j = 0; j < snailArr.length; j++)
			for (int i = 0; i < snailArr[j].length; i++)
				snailArr[j][i] = temp.getSubimage(i * SNAIL_WIDTH, j * SNAIL_HEIGHT, SNAIL_WIDTH_DEFAULT, SNAIL_HEIGHT_DEFAULT);
	}
	
	public void resetAllEnemies() {
		for (Snail c : snails)
			c.resetEnemy();
	}
}