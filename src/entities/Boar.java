package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.IsFloor;
import static utilz.Constants.Directions.*;

import gamestates.Playing;

public class Boar extends Enemy {

	private boolean preRush = true;
	private int tickSinceLastDmgToPlayer;
	private int tickAfterRush;
	private int rushDurationTick, rushDuration = 300;

	public Boar(float x, float y) {
		super(x, y, BOAR_WIDTH, BOAR_HEIGHT, BOAR);
		initHitbox(24, 21);
		this.state = RUNNING;
	}

	public void update(int[][] lvlData, Playing playing) {
		updateBehavior(lvlData, playing);
		updateAnimationTick();
	}

	private void updateBehavior(int[][] lvlData, Playing playing) {
		if (firstUpdate)
			firstUpdateCheck(lvlData);

		if (inAir)
			inAirChecks(lvlData, playing);
		else {
			switch (state) {
	
			case RUNNING:
				preRush = true;
				if (tickAfterRush >= 120) {
					if (IsFloor(hitbox, lvlData))
						newState(RUNNING);
					else
						inAir = true;
					tickAfterRush = 0;
					tickSinceLastDmgToPlayer = 60;
				} else
					tickAfterRush++;
				if (canSeePlayer(lvlData, playing.getPlayer())) {
					newState(ATTACK);
					setWalkDir(playing.getPlayer());
				}
				move(lvlData, playing);
				break;
				
			case ATTACK:
				
				if(!attackChecked) {
					checkPlayerHit(hitbox, playing.getPlayer());
					attackChecked = false;
				}
				
				if (preRush) {
					if (aniIndex >= 3)
						preRush = false;
				} else {
					move(lvlData, playing);
					checkDmgToPlayer(playing.getPlayer());
					checkRush(playing);
				}
				break;
			case HIT:
				if (aniIndex <= GetSpriteAmount(enemyType, state) - 2)
					pushBack(pushBackDir, lvlData, 2f);
				updatePushBackDrawOffset();
				tickAfterRush = 120;

				break;
			}
		}
	}

	private void checkDmgToPlayer(Player player) {
		if (hitbox.intersects(player.getHitbox()))
			if (tickSinceLastDmgToPlayer >= 60) {
				tickSinceLastDmgToPlayer = 0;
				player.changeHealth(-GetEnemyDmg(enemyType), this);
			} else
				tickSinceLastDmgToPlayer++;
	}

	private void setWalkDir(Player player) {
		if (player.getHitbox().x > hitbox.x)
			walkDir = RIGHT;
		else
			walkDir = LEFT;

	}

	protected void move(int[][] lvlData, Playing playing) {
		float xSpeed = 0;

		if (walkDir == LEFT)
			xSpeed = -walkSpeed;
		else
			xSpeed = walkSpeed;

		if (state == ATTACK)
			xSpeed *= 2;

		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
			if (IsFloor(hitbox, xSpeed, lvlData)) {
				hitbox.x += xSpeed;
				return;
			}

		if (state == ATTACK) {
			rushOver(playing);
			rushDurationTick = 0;
		}

		changeWalkDir();

	}

	private void checkRush(Playing playing) {
		rushDurationTick++;
		if (rushDurationTick >= rushDuration) {
			rushOver(playing);
			rushDurationTick = 0;
		}
	}

	private void rushOver(Playing playing) {
		newState(RUNNING);
	}
}
