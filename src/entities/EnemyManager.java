package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] snailArr;
	private ArrayList<Snail> snails = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		snails = LoadSave.GetSnails();
		
	}

	public void update(int[][] lvlData) {
		for (Snail c : snails)
			c.update(lvlData);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawSnails(g, xLvlOffset);
	}

	private void drawSnails(Graphics g, int xLvlOffset) {
		for (Snail c : snails) {
			g.drawImage(snailArr[c.getEnemyState()][c.getAniIndex()], 
					(int) c.getHitbox().x - xLvlOffset - SNAIL_DRAWOFFSET_X, 
					(int) c.getHitbox().y - SNAIL_DRAWOFFSET_Y, SNAIL_WIDTH,
					SNAIL_HEIGHT, null);
			c.drawHitbox(g, xLvlOffset);
		}

	}

	private void loadEnemyImgs() {
		snailArr = new BufferedImage[3][8];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SNAIL_SPRITE);
		for (int j = 0; j < snailArr.length; j++)
			for (int i = 0; i < snailArr[j].length; i++)
				snailArr[j][i] = temp.getSubimage(i * SNAIL_WIDTH, j * SNAIL_HEIGHT, SNAIL_WIDTH_DEFAULT, SNAIL_HEIGHT_DEFAULT);
	}
}