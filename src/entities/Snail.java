package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class Snail extends Enemy {
	
	public Snail(float x, float y) {
		super(x, y, SNAIL_WIDTH, SNAIL_HEIGHT, SNAIL);
		initHitbox(x, y, (int) (25 * Game.SCALE), (int) (19 * Game.SCALE));
	}
	
	public void update(int[][] lvlData, Player player) {
		updateMove(lvlData, player);
		updateAnimationTick();

	}

	private void updateMove(int[][] lvlData, Player player) {
		if (firstUpdate)
			firstUpdateCheck(lvlData);

		if (inAir)
			updateInAir(lvlData);
		else {
			switch (enemyState) {
			case RUNNING:
		
				if (isPlayerInRange(player))
					newState(ATTACK);
				move(lvlData);
				break;
			case ATTACK: 
				if (!isPlayerInRange(player))
					newState(RUNNING);
			}
		}

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
			}
		}
	}
}
