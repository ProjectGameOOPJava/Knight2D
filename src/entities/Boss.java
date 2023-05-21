package entities;

import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.IsFloor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class Boss extends Enemy {
	
	private BufferedImage statusBarImg;
	
	private int statusBarWidth = (int) (192 * Game.SCALE);
	private int statusBarHeight = (int) (58 * Game.SCALE);
	private int statusBarX = (int) (600 * Game.SCALE);
	private int statusBarY = (int) (10 * Game.SCALE);

	private int healthBarWidth = (int) (150 * Game.SCALE);
	private int healthBarHeight = (int) (4 * Game.SCALE);
	private int healthBarXStart = (int) (34 * Game.SCALE);
	private int healthBarYStart = (int) (14 * Game.SCALE);

	private int healthWidth = healthBarWidth;

	public Boss(float x, float y) {
		super(x, y, BOSS_WIDTH, BOSS_HEIGHT, BOSS);
		initHitbox(22, 28);
		initAttackBox(25, 30, 20);
		this.state = IDLE;
		this.walkSpeed = 1.0f * Game.SCALE;
		loadAnimations();
	}
	
	

	public void update(int[][] lvlData, Playing playing) {
		updateBehavior(lvlData, playing);
		updateAnimationTick();
		updateAttackBoxFlip();
		updateHealthBar();
	}

	private void updateBehavior(int[][] lvlData, Playing playing) {
		if (firstUpdate)
			firstUpdateCheck(lvlData);

		if (inAir)
			inAirChecks(lvlData, playing);
		else {
			switch (state) {
				
			case IDLE:
				if (IsFloor(hitbox, lvlData)) {
					
					if (canSeePlayer(lvlData, playing.getPlayer())) {
						turnTowardsPlayer(playing.getPlayer());
						newState(RUNNING);
					}
				}
				break;
				
			case RUNNING:
				
				if (IsFloor(hitbox, lvlData)) {
				
	
				if (isPlayerCloseForAttack(playing.getPlayer()))
						newState(ATTACK);
				
				}else
					inAir = true;

				move(lvlData);
				
				break;
			case ATTACK:
				
				if (aniIndex == 0)
					attackChecked = false;
					
						
				if (!attackChecked) {
					checkPlayerHit(attackBox, playing.getPlayer());
					attackMove(lvlData, playing);
				}
				
						
				break;
			}
		}
	}
	
	private void updateHealthBar() {
		healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
	}
	
	protected void attackMove(int[][] lvlData, Playing playing) {
		float xSpeed = 0;

		if (walkDir == LEFT)
			xSpeed = -walkSpeed;
		else
			xSpeed = walkSpeed;

		if (CanMoveHere(hitbox.x + xSpeed * 2, hitbox.y, hitbox.width, hitbox.height, lvlData))
			if (IsFloor(hitbox, xSpeed * 2, lvlData)) {
				hitbox.x += xSpeed * 2;
				return;
			}
		newState(IDLE);
	}
	
	protected void drawUI(Graphics g) {
		
		g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
		
		g.setColor(Color.red);
		g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
		
	}
	
	private void loadAnimations() {
		statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
	}
	

	@Override
	public int flipX() {
		if (walkDir == RIGHT)
			return 0;
		else
			return width;
	}
	
	@Override
	public int flipW() {
		if (walkDir == RIGHT)
			return 1;
		else
			return -1;
	}
}
