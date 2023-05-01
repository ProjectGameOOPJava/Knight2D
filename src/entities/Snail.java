package entities;

import static utilz.Constants.EnemyConstants.*;

import main.Game;

public class Snail extends Enemy {

	public Snail(float x, float y) {
		super(x, y, SNAIL_WIDTH, SNAIL_HEIGHT, SNAIL);
		initHitbox(x, y, (int) (25 * Game.SCALE), (int) (19 * Game.SCALE));
	}

}