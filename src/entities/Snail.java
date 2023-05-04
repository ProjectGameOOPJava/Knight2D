package entities;

import static utilz.Constants.EnemyConstants.*;

import static utilz.Constants.Directions.*;

import static utilz.Constants.*;

import main.Game;

public class Snail extends Enemy {
	
	public Snail(float x, float y) {
		super(x, y, SNAIL_WIDTH, SNAIL_HEIGHT, SNAIL);
		initHitbox(25, 19);
		this.state = RUNNING;
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
			switch (state) {
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
		if (aniTick >= ANI_SPEED) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(enemyType, state)) {
				aniIndex = 0;
				if(state == ATTACK)
					aniIndex = 7;
				switch (state) {
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
