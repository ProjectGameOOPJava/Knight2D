package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import static utilz.Constants.*;
import static utilz.Constants.Directions.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import audio.AudioPlayer;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false, powerAttack = false, slashActive = false;
	private boolean left, right, jump;
	private int[][] lvlData;
	private float xDrawOffset = 45 * Game.SCALE;
	private float yDrawOffset = 50 * Game.SCALE;

	// Jumping / Gravity
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;

	// StatusBarUI
	private BufferedImage statusBarImg;

	private int statusBarWidth = (int) (192 * Game.SCALE);
	private int statusBarHeight = (int) (58 * Game.SCALE);
	private int statusBarX = (int) (10 * Game.SCALE);
	private int statusBarY = (int) (10 * Game.SCALE);

	private int healthBarWidth = (int) (150 * Game.SCALE);
	private int healthBarHeight = (int) (4 * Game.SCALE);
	private int healthBarXStart = (int) (34 * Game.SCALE);
	private int healthBarYStart = (int) (14 * Game.SCALE);
	private int healthWidth = healthBarWidth;

	private int powerBarWidth = (int) (104 * Game.SCALE);
	private int powerBarHeight = (int) (2 * Game.SCALE);
	private int powerBarXStart = (int) (44 * Game.SCALE);
	private int powerBarYStart = (int) (34 * Game.SCALE);
	private int powerWidth = powerBarWidth;
	private int powerMaxValue = 200;
	private int powerValue = powerMaxValue;

	private int flipX = 0;
	private int flipW = 1;

	private boolean attackChecked;
	private Playing playing;

	private int tileY = 0;

	private boolean powerAttackActive;
	private int powerAttackTick;
	private int powerGrowSpeed = 15;
	private int powerGrowTick;

	public Player(float x, float y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		this.state = IDLE;
		this.maxHealth = 100;
		this.currentHealth = maxHealth;
		this.walkSpeed = Game.SCALE * 1.0f;
		loadAnimations();
		initHitbox(22, 28);
		initAttackBox();
	}

	public void setSpawn(Point spawn) {
		this.x = spawn.x;
		this.y = spawn.y;
		hitbox.x = x;
		hitbox.y = y;
	}

	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y, (int) (35 * Game.SCALE), (int) (20 * Game.SCALE));
		resetAttackBox();
	}

	protected void initHitbox(float x, float y, float width, float height) {
		hitbox = new Rectangle2D.Float(x, y, width, height);
	}

	public void update() {
		updateHealthBar();
		updatePowerBar();

		if (currentHealth <= 0) {
			if (state != DEAD) {
				state = DEAD;
				aniTick = 0;
				aniIndex = 0;
				playing.setPlayerDying(true);
				playing.getGame().getAudioPlayer().playEffect(AudioPlayer.DIE);

				// Check if player died in air
				if (!IsEntityOnFloor(hitbox, lvlData)) {
					inAir = true;
					airSpeed = 0;
				}
			} else if (aniIndex == GetSpriteAmount(DEAD) - 1 && aniTick >= ANI_SPEED - 1) {
				playing.setGameOver(true);
				playing.getGame().getAudioPlayer().stopSong();
				playing.getGame().getAudioPlayer().playEffect(AudioPlayer.GAMEOVER);
			} else {
				updateAnimationTick();

				// Fall if in air
				if (inAir)
					if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
						hitbox.y += airSpeed;
						airSpeed += GRAVITY;
					} else
						inAir = false;

			}
			return;
		}
		
		updateAttackBox();
		if (state == HIT) {
			if (aniIndex <= GetSpriteAmount(HIT))
				pushBack(pushBackDir, lvlData, 2.5f);

			updatePushBackDrawOffset();
		} else
			updatePos();

		if (moving) {
			checkPotionTouched();
			checkSpikesTouched();
			tileY = (int) (hitbox.y / Game.TILES_SIZE);
			if (powerAttackActive) {
				powerAttackTick++;
				if (powerAttackTick >= 35) {
					powerAttackTick = 0;
					powerAttackActive = false;
				}
			}

		}
		if (attacking || powerAttackActive) {
			slashActive = false;
			checkAttack();
		}

		if (slashActive) {
			playing.getObjectManager().playerSlash(this);
		}

		updateAnimationTick();
		setAnimation();
	}

	private void checkPotionTouched() {
		playing.checkPotionTouched(hitbox);
	}

	private void checkSpikesTouched() {
		playing.checkSpikesTouched(this);
	}

	private void setAttackBoxOnRightSide() {
		attackBox.x = hitbox.x + hitbox.width - (int) (Game.SCALE * 10);
	}

	private void setAttackBoxOnLeftSide() {
		attackBox.x = hitbox.x - hitbox.width - (int) (Game.SCALE * 10);
	}

	private void checkAttack() {
		if (aniIndex != 1 || aniIndex != 6)
			attackChecked = false;
		if (aniIndex == 1 || aniIndex == 6) {
			attackChecked = true;
			if (powerAttackActive && slashActive)
				attackChecked = false;
			playing.checkEnemyHit(attackBox);
			playing.checkObjectHit(attackBox);
			playing.getGame().getAudioPlayer().playAttackSound();
		}
	}

	private void updateAttackBox() {
		if (right && left || attacking && !right || attacking && !left) {
			if (flipW == 1) {
				setAttackBoxOnRightSide();
			} else {
				setAttackBoxOnLeftSide();
			}

		} else if (right || (powerAttackActive && flipW == 1))
			setAttackBoxOnRightSide();
		else if (left || (powerAttackActive && flipW == -1))
			setAttackBoxOnLeftSide();

		attackBox.y = hitbox.y + (Game.SCALE * 10);
	}

	private void updateHealthBar() {
		healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
	}

	private void updatePowerBar() {
		powerWidth = (int) ((powerValue / (float) powerMaxValue) * powerBarWidth);

		powerGrowTick++;
		if (powerGrowTick >= powerGrowSpeed) {
			powerGrowTick = 0;
			changePower(1);
		}
	}

	public void render(Graphics g, int lvlOffset) {

		g.drawImage(animations[state][aniIndex], 
				(int) (hitbox.x - xDrawOffset) - lvlOffset + flipX, 
				(int) (hitbox.y - yDrawOffset + (int) (pushDrawOffset)), width * flipW, height, null);
		//drawHitbox(g, lvlOffset);
		//drawAttackBox(g, lvlOffset);

		drawUI(g);
	}

	private void drawUI(Graphics g) {

		g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);

		g.setColor(Color.red);
		g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);

		g.setColor(Color.yellow);
		g.fillRect(powerBarXStart + statusBarX, powerBarYStart + statusBarY, powerWidth, powerBarHeight);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= ANI_SPEED) {
			aniTick = 0;
			aniIndex++;
			if (state == HIT) {
				aniIndex = 1;
				attacking = false;
				attackChecked = false;
				slashActive = false;

				if (!IsFloor(hitbox, 0, lvlData)) {
					inAir = true;
					newState(FALLING);
				} else
					newState(IDLE);
			}
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
				attackChecked = false;
				slashActive = false;
			}

		}

	}

	private void setAnimation() {
		int startAni = state;

		if (state == HIT)
			return;

		if (moving) {
			state = RUNNING;

		} else
			state = IDLE;

		if (inAir) {
			if (airSpeed < 0)
				state = JUMP;
			else
				state = FALLING;
		}

		if (powerAttackActive) {
			state = RUSH;
			aniIndex = 1;
			aniTick = 0;
			return;
		}

		if (attacking) {
			state = ATTACK;
			if (startAni != ATTACK) {
				aniIndex = 1;
				aniTick = 0;
				return;
			}
		}

		if (slashActive) {
			state = ATTACK;
			return;

		}

		if (startAni != state)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;

		if (jump)
			jump();
		if (!inAir)
			if (!powerAttackActive)
				if ((!left && !right) || (right && left)
						|| (left && attacking) || (right && attacking)
						|| (attacking && slashActive))

					return;

		float xSpeed = 0;

		if (left && !right) {
			xSpeed -= walkSpeed;
			flipX = width;
			flipW = -1;
		}
		if (right && !left) {
			xSpeed += walkSpeed;
			flipX = 0;
			flipW = 1;
		}

		if (powerAttackActive) {
			if (!left && !right || left && right) {
				if (flipW == -1)
					xSpeed = -walkSpeed;
				else
					xSpeed = walkSpeed;
			}

			xSpeed *= 3;
		}

		if (!inAir)
			if (!IsEntityOnFloor(hitbox, lvlData))
				inAir = true;

		if (inAir && !powerAttackActive) {
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += GRAVITY;
				updateXPos(xSpeed);
			} else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}

		} else
			updateXPos(xSpeed);
		moving = true;
	}

	private void jump() {
		if (inAir)
			return;

		if (powerValue >= 20) {
			inAir = true;
			airSpeed = jumpSpeed;
			changePower(-20);
			playing.getGame().getAudioPlayer().playEffect(AudioPlayer.JUMP);
		}
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
			hitbox.x += xSpeed;
		else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
			if (powerAttackActive) {
				powerAttackActive = false;
				powerAttackTick = 0;
			}

		}
	}

	public void changeHealth(int value) {

		if (value < 0) {
			if (state == HIT)
				return;
			else
				newState(HIT);
		}

		currentHealth += value;

		currentHealth = Math.max(Math.min(currentHealth, maxHealth), 0);
	}

	public void changeHealth(int value, Enemy e) {
		if (state == HIT)
			return;
		changeHealth(value);
		pushBackOffsetDir = UP;
		pushDrawOffset = 0;

		if (e.getHitbox().x < hitbox.x && e.getHitbox().x + e.getHitbox().width < hitbox.x + hitbox.width)
			pushBackDir = RIGHT;
		else
			pushBackDir = LEFT;
	}

	public void kill() {
		currentHealth = 0;
	}

	public void changePower(int value) {
		powerValue += value;
		powerValue = Math.max(Math.min(powerValue, powerMaxValue), 0);
	}

	private void loadAnimations() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[9][10];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 120, j * 82, 120, 82);
		statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);

	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isSlash() {
		return slashActive;
	}

	public void setPushBackDir(int pushBackDir) {
		this.pushBackDir = pushBackDir;
	}

	public int getFlipX() {
		return flipX;
	}

	public int getFLipW() {
		return flipW;
	}

	public int getWidth() {
		return width;
	}

	public void resetAll() {
		resetDirBooleans();
		jump = false;
		inAir = false;
		attacking = false;
		moving = false;
		state = IDLE;
		airSpeed = 0f;
		currentHealth = maxHealth;
		powerAttackActive = false;
		slashActive = false;
		powerAttackTick = 0;
		powerValue = powerMaxValue;

		hitbox.x = x;
		hitbox.y = y;

		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}

	private void resetAttackBox() {
		if (flipW == 1)
			setAttackBoxOnRightSide();
		else
			setAttackBoxOnLeftSide();
	}

	public int getTileY() {
		return tileY;
	}

	public void setPowerAttack(boolean powerAttack) {
		if (powerAttackActive)
			return;
		if (powerValue >= 60) {
			powerAttackActive = true;
			changePower(-60);
		}

	}

	public void setSlash(boolean slash) {

		if (slashActive)
			return;

		if (!inAir)
			if (powerValue >= 190) {

				slashActive = true;
				changePower(-190);
				playing.getGame().getAudioPlayer().playEffect(AudioPlayer.ATTACK_THREE);
			}
	}

}