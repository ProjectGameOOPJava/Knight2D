package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 8;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;
	private float playerSpeed = 3.0f;
	private int[][] lvlData;
	private float xDrawOffset = 45 * Game.SCALE;
	private float yDrawOffset = 51 * Game.SCALE;

	// Jumping / Gravity
	private float airSpeed = 0f;
	private float gravity = 0.05f * Game.SCALE;
	private float jumpSpeed = -3.0f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, 22 * Game.SCALE, 28 * Game.SCALE);
	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
		drawHitbox(g);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}

		}

	}

	private void setAnimation() {
		int startAni = playerAction;
		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;

		if (inAir) {
			if (airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALLING;
		}

		if (attacking)
			playerAction = ATTACK;

		if (startAni != playerAction)
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
		if (!left && !right && !inAir) return;
			float xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		if (right)
				xSpeed += playerSpeed;
	
		if (!inAir)
			if (!IsEntityOnFloor(hitbox, lvlData))
					inAir = true;
	
			if (inAir) {
				if (CollisionCheck(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
					hitbox.y += airSpeed;
					airSpeed += gravity;
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
		inAir = true;
		airSpeed = jumpSpeed;
	
	}
	
	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	
	}

	private void loadAnimations() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[8][10];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 120, j * 82, 120, 82);

	}

	private void updateXPos(float xSpeed) {
		if (CollisionCheck(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		} else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
		}

	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean getLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean getUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean getRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean getDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}
}