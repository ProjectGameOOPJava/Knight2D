package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import levels.Level;
import objects.Projectile;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] snailArr, boarArr, beeArr, bossArr;
	private Level currentLevel;

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();

	}

	public void loadEnemies(Level level) {
		this.currentLevel = level;
	}

	public void update(int[][] lvlData) {
		boolean isAnyActive = false;
		for (Snail snail : currentLevel.getSnails())
			if (snail.isActive()) {
				snail.update(lvlData, playing);
				isAnyActive = true;
			}

		for (Bee bee : currentLevel.getBees())
			if (bee.isActive()) {
				bee.update(lvlData, playing);
				isAnyActive = true;
			}

		for (Boar boar : currentLevel.getBoars())
			if (boar.isActive()) {
				boar.update(lvlData, playing);
				isAnyActive = true;
			}

		for (Boss boss : currentLevel.getBosses())
			if (boss.isActive()) {
				boss.update(lvlData, playing);
				isAnyActive = true;
			}

		if (!isAnyActive)
			playing.setLevelCompleted(true);
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawSnails(g, xLvlOffset);
		drawBees(g, xLvlOffset);
		drawBoars(g, xLvlOffset);
		drawBosses(g, xLvlOffset);
	}

	private void drawSnails(Graphics g, int xLvlOffset) {
		for (Snail snail : currentLevel.getSnails())
			if (snail.isActive()) {

				g.drawImage(snailArr[snail.getState()][snail.getAniIndex()],
						(int) snail.getHitbox().x - xLvlOffset - SNAIL_DRAWOFFSET_X + snail.flipX(),
						(int) snail.getHitbox().y - SNAIL_DRAWOFFSET_Y + (int) snail.getPushDrawOffset(),
						SNAIL_WIDTH * snail.flipW(), SNAIL_HEIGHT, null);
				// snail.drawHitbox(g, xLvlOffset);
			}

	}

	private void drawBees(Graphics g, int xLvlOffset) {
		for (Bee boar : currentLevel.getBees())
			if (boar.isActive()) {

				g.drawImage(beeArr[boar.getState()][boar.getAniIndex()],
						(int) boar.getHitbox().x - xLvlOffset - BEE_DRAWOFFSET_X + boar.flipX(),
						(int) boar.getHitbox().y - BEE_DRAWOFFSET_Y + (int) boar.getPushDrawOffset(),
						BEE_WIDTH * boar.flipW(), BEE_HEIGHT, null);
				// boar.drawHitbox(g, xLvlOffset);
				// boar.drawAttackBox(g, xLvlOffset);
			}
	}

	private void drawBoars(Graphics g, int xLvlOffset) {
		for (Boar bee : currentLevel.getBoars())
			if (bee.isActive()) {

				g.drawImage(boarArr[bee.getState()][bee.getAniIndex()],
						(int) bee.getHitbox().x - xLvlOffset - BOAR_DRAWOFFSET_X + bee.flipX(),
						(int) bee.getHitbox().y - BOAR_DRAWOFFSET_Y + (int) bee.getPushDrawOffset(),
						BOAR_WIDTH * bee.flipW(), BOAR_HEIGHT, null);
				// bee.drawHitbox(g, xLvlOffset);
			}
	}

	private void drawBosses(Graphics g, int xLvlOffset) {
		for (Boss boss : currentLevel.getBosses())
			if (boss.isActive()) {
				g.drawImage(bossArr[boss.getState()][boss.getAniIndex()],
						(int) boss.getHitbox().x - xLvlOffset - BOSS_DRAWOFFSET_X + boss.flipX(),
						(int) boss.getHitbox().y - BOSS_DRAWOFFSET_Y + (int) boss.getPushDrawOffset(),
						BOSS_WIDTH * boss.flipW(), BOSS_HEIGHT, null);
				// boss.drawHitbox(g, xLvlOffset);
				// boss.drawAttackBox(g, xLvlOffset);
				// boss.drawUI(g);
			}
	}

	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Snail snail : currentLevel.getSnails())
			if (snail.isActive())
				if (snail.getState() != HIT)
					if (attackBox.intersects(snail.getHitbox())) {
						snail.hurt(10);
						return;

		for (Bee bee : currentLevel.getBees())
			if (bee.isActive()) {
				if (bee.getState() == ATTACK && bee.getAniIndex() >= 3)
					return;
				else {
					if (bee.getState() != HIT)
						if (attackBox.intersects(bee.getHitbox())) {
							bee.hurt(20);
							return;
						}
				}
			}

		for (Boar boar : currentLevel.getBoars())
			if (boar.isActive()) {
				if (boar.getState() != HIT)
					if (attackBox.intersects(boar.getHitbox())) {
						boar.hurt(20);
						return;

					}
			}

		for (Boss boss : currentLevel.getBosses())
			if (boss.isActive()) {
				if (boss.getState() != HIT)
					if (attackBox.intersects(boss.getHitbox())) {
						boss.hurt(10);
						return;

					}
			}
	}

	public void checkEnemyHit(Projectile projectile) {
		for (Snail snail : currentLevel.getSnails())
			if (snail.isActive())
				if (snail.getState() != HIT)
					if (projectile.getHitbox().intersects(snail.getHitbox())) {
						snail.hurt(100);
						return;

		for (Bee bee : currentLevel.getBees())
			if (bee.isActive()) {
				if (bee.getState() == ATTACK && bee.getAniIndex() >= 3)
					return;
				else {
					if (bee.getState() != HIT)
						if (projectile.getHitbox().intersects(bee.getHitbox())) {
							bee.hurt(100);
							return;
						}
				}
			}

		for (Boar boar : currentLevel.getBoars())
			if (boar.isActive()) {
				if (boar.getState() != HIT)
					if (projectile.getHitbox().intersects(boar.getHitbox())) {
						boar.hurt(100);
						return;

					}
			}

		for (Boss boss : currentLevel.getBosses())
			if (boss.isActive()) {
				if (boss.getState() != HIT)
					if (projectile.getHitbox().intersects(boss.getHitbox())) {
						boss.hurt(0);
						return;

					}
			}
	}

	private void loadEnemyImgs() {
		snailArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.SNAIL_ATLAS), 8, 3, SNAIL_WIDTH_DEFAULT,
				SNAIL_HEIGHT_DEFAULT);
		beeArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.BEE_ATLAS), 4, 3, BEE_WIDTH_DEFAULT, BEE_HEIGHT_DEFAULT);
		boarArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.BOAR_ATLAS), 6, 3, BOAR_WIDTH_DEFAULT,
				BOAR_HEIGHT_DEFAULT);
		bossArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.BOSS_ATLAS), 10, 7, BOSS_WIDTH_DEFAULT,
				BOSS_HEIGHT_DEFAULT);
	}

	private BufferedImage[][] getImgArr(BufferedImage atlas, int xSize, int ySize, int spriteW, int spriteH) {
		BufferedImage[][] tempArr = new BufferedImage[ySize][xSize];
		for (int j = 0; j < tempArr.length; j++)
			for (int i = 0; i < tempArr[j].length; i++)
				tempArr[j][i] = atlas.getSubimage(i * spriteW, j * spriteH, spriteW, spriteH);
		return tempArr;
	}

	public void resetAllEnemies() {
		for (Snail snail : currentLevel.getSnails())
			snail.resetEnemy();
		for (Bee bee : currentLevel.getBees())
			bee.resetEnemy();
		for (Boar boar : currentLevel.getBoars())
			boar.resetEnemy();
		for (Boss boss : currentLevel.getBosses())
			boss.resetEnemy();
	}
}