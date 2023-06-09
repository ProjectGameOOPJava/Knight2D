package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;
import static utilz.HelpMethods.IsFloor;
import static utilz.Constants.Directions.*;
import static utilz.Constants.*;

import java.awt.geom.Rectangle2D;

import gamestates.Playing;
import main.Game;

public abstract class Enemy extends Entity {
	protected int enemyType;
	protected boolean firstUpdate = true;
	protected int walkDir = LEFT;
	protected int tileY;
	protected float attackDistance = Game.TILES_SIZE;
	protected boolean active = true;
	protected boolean attackChecked;
	protected int attackBoxOffsetX;
	
	public Enemy(float x, float y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		maxHealth = GetMaxHealth(enemyType);
		currentHealth = maxHealth;
		walkSpeed = Game.SCALE * 0.35f;
		
	}
	
	protected void updateAttackBox() {
		attackBox.x = hitbox.x - attackBoxOffsetX;
		attackBox.y = hitbox.y;
	}
	
	protected void updateAttackBoxFlip() {
		if (walkDir == RIGHT)
			attackBox.x = hitbox.x + hitbox.width;
		else
			attackBox.x = hitbox.x - attackBoxOffsetX;

		attackBox.y = hitbox.y;
	}
	
	protected void initAttackBox(int w, int h, int attackBoxOffsetX) {
		attackBox = new Rectangle2D.Float(x, y, (int) (w * Game.SCALE), (int) (h * Game.SCALE));
		this.attackBoxOffsetX = (int) (Game.SCALE * attackBoxOffsetX);
	}

	protected void firstUpdateCheck(int[][] lvlData) {
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
		firstUpdate = false;
	}
	
	protected void inAirChecks(int[][] lvlData, Playing playing) {
		if (state != HIT) {
			updateInAir(lvlData);
			playing.getObjectManager().checkSpikesTouched(this);
		}
	}

	protected void updateInAir(int[][] lvlData) {
		if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
			hitbox.y += airSpeed;
			airSpeed += GRAVITY;
		} else {
			inAir = false;
			hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
			tileY = (int) (hitbox.y / Game.TILES_SIZE);
		}
	}
	
	protected void move(int[][] lvlData) {
		float xSpeed = 0;

		if (walkDir == LEFT)
			xSpeed = -walkSpeed;
		else
			xSpeed = walkSpeed;

		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
			if (IsFloor(hitbox, xSpeed, lvlData)) {
				hitbox.x += xSpeed;
				return;
			}

		changeWalkDir();
	}
	
	protected void turnTowardsPlayer(Player player) {
		if (player.hitbox.x > hitbox.x)
			walkDir = RIGHT;
		else
			walkDir = LEFT;
	}
	
	protected boolean canSeePlayer(int[][] lvlData, Player player) {
		int playerTileY = (int) (player.getHitbox().y / Game.TILES_SIZE);
		if (playerTileY == tileY)
			if (isPlayerInRange(player)) {
				if (IsSightClear(lvlData, hitbox, player.hitbox, tileY))
					return true;
			}

		return false;
	}
	
	protected boolean isPlayerInRange(Player player) {
		int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
		if(enemyType == BOSS) return absValue <= attackDistance * 10;
		return absValue <= attackDistance * 5;
	}
	
	protected boolean isPlayerCloseForAttack(Player player) {
		int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
		switch(enemyType) {
			case SNAIL: break;
			case BOAR:
				return absValue <= attackDistance * 5;
			case BEE: 
				return absValue <= attackDistance * 2;
			case BOSS:
				return absValue <= attackDistance * 2;
		}
		return false;
	}
	
	public void hurt(int amount) {
		currentHealth -= amount;
	
			newState(HIT);
			if (walkDir == LEFT)
				pushBackDir = RIGHT;
			else
				pushBackDir = LEFT;
			pushBackOffsetDir = UP;
			pushDrawOffset = 0;
		
	}

	protected void checkPlayerHit(Rectangle2D.Float attackBox, Player player) {
		if (attackBox.intersects(player.hitbox)) {
			
			player.changeHealth(-GetEnemyDmg(enemyType), this);
			
		}
		
		attackChecked = true;

	}
	
	
		
	protected void updateAnimationTick() {
		aniTick++;
		if (aniTick >= ANI_SPEED) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(enemyType, state)) {
				if (enemyType == SNAIL || enemyType == BEE ) {
					aniIndex = 0;
				switch (state) {
				case ATTACK:
					state = RUNNING;
					break;
				case HIT:
					state = RUNNING;
					if(currentHealth <= 0 ) active = false;
					break;
				
					}
				}else if (enemyType == BOAR) {
					if (state == ATTACK) {
						aniIndex = 3;
					}
						
					else {
						aniIndex = 0;
						if (state == HIT) {
							state = RUNNING;
							if(currentHealth <= 0 ) active = false;
						} 
					}
				}else if (enemyType == BOSS) {
					
					aniIndex = 0;
					switch (state) {
					case IDLE:
						heal(50);
						break;
					case ATTACK:
						//System.out.println(currentHealth);
						state = IDLE;
						break;
					case HIT:
						state = IDLE;
						if(currentHealth == 150 || currentHealth == 100 ||
								currentHealth == 50 || currentHealth == 25) state = ATTACK;
						if(currentHealth <= 0 ) {
							state = DEATH;
							active = false;
						}
						break;
					case DEATH: active = false;
						}
				}
			}
		}
	}
	
	protected void heal(int value) {
		currentHealth += value;
		if(currentHealth >= maxHealth) currentHealth = maxHealth;
	}

	protected void changeWalkDir() {
		if (walkDir == LEFT)
			walkDir = RIGHT;
		else
			walkDir = LEFT;

	}
	
	public void resetEnemy() {
		hitbox.x = x;
		hitbox.y = y;
		firstUpdate = true;
		currentHealth = maxHealth;
		newState(RUNNING);
		active = true;
		airSpeed = 0;
		pushDrawOffset = 0;
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
	
	public boolean isActive() {
		return active;
	}
	
	public float getPushDrawOffset() {
		return pushDrawOffset;
	}

}
