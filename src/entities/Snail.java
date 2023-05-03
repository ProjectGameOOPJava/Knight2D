package entities;

import static utilz.Constants.EnemyConstants.*;

import static utilz.Constants.Directions.*;

import main.Game;

public class Snail extends Enemy {
	
	public Snail(float x, float y) {
		super(x, y, SNAIL_WIDTH, SNAIL_HEIGHT, SNAIL);
		initHitbox(x, y, (int) (25 * Game.SCALE), (int) (19 * Game.SCALE));
	}
	
	public void update(int[][] lvlData, Player player) {
		updateBehavior(lvlData, player);
		updateAnimationTick();

	}

	private void updateBehavior(int[][] lvlData, Player player) {
		if (firstUpdate)
			firstUpdateCheck(lvlData);

		if (inAir)
			updateInAir(lvlData);
		else {
			switch (enemyState) {
			case RUNNING:
				if (canSeePlayer(lvlData, player)) {
					if (isPlayerInRange(player))
						newState(ATTACK);
				}
				move(lvlData);
				break;
			case ATTACK: 
				if (!isPlayerInRange(player))
					newState(RUNNING);
				break;
			
			}
		}

	}
	
	public int flipX() {
		if (walkDir == RIGHT)
			return width;
		else
			return 0;
	}

	public int flipW() {
		if (walkDir == RIGHT)
			return -1;
		else
			return 1;

	}
	
	@Override
	protected void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
				aniIndex = 0;
				if(enemyState == ATTACK)
					aniIndex = 7;
				switch (enemyState) {
				case ATTACK:  aniIndex = 7; break;
				case DEAD : active = false; break;
				}
			}
		}
	}
	
	@Override
	public void hurt(int amount) {
		currentHealth -= amount;
		if (currentHealth <= 0)
			newState(DEAD);
	}
}
