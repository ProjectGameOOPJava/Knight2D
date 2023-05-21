package objects;

import java.awt.geom.Rectangle2D;

import main.Game;

import static utilz.Constants.Projectiles.*;

public class Projectile {
	private Rectangle2D.Float hitbox;
	private int dir;
	private int type;
	private boolean active = true;


	public Projectile(int type, int x, int y, int dir) {
		
		this.type = type;
		
		int xOffset = (int) (-3 * Game.SCALE);
		int yOffset = (int) (5 * Game.SCALE);

		if (dir == 1)
			xOffset = (int) (29 * Game.SCALE);
		
		if(type == SLASH)
			hitbox = new Rectangle2D.Float(x + xOffset, y + yOffset, SLASH_WIDTH, SLASH_HEIGHT);
		else	
			hitbox = new Rectangle2D.Float(x + xOffset, y + yOffset, CANNON_BALL_WIDTH, CANNON_BALL_HEIGHT);
		this.dir = dir;
	}
	
	public void updatePos() {
		
		if(type == SLASH) {
			hitbox.x += dir * SPEED_SLASH;
		}else
			hitbox.x += dir * SPEED;
	}

	public void setPos(int x, int y) {
		hitbox.x = x;
		hitbox.y = y;
	}

	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}
	
	public int getDir() {
		return dir;
	}
	

}