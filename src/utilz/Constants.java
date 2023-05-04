package utilz;

import main.Game;

public class Constants {
	
	public static final float GRAVITY = 0.04f * Game.SCALE;
	public static final int ANI_SPEED = 25;

	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

		}

		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}
	}
	
	public static class EnemyConstants {
		public static final int SNAIL = 0;
		
		public static final int IDLE = 3;
		public static final int RUNNING = 2;
		public static final int ATTACK = 1;
		public static final int DEAD = 0;

		public static final int SNAIL_WIDTH_DEFAULT = 48;
		public static final int SNAIL_HEIGHT_DEFAULT = 34;

		public static final int SNAIL_WIDTH = (int) (SNAIL_WIDTH_DEFAULT * Game.SCALE);
		public static final int SNAIL_HEIGHT = (int) (SNAIL_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int SNAIL_DRAWOFFSET_X = (int) (12 * Game.SCALE);
		public static final int SNAIL_DRAWOFFSET_Y = (int) (12 * Game.SCALE);

		public static int GetSpriteAmount(int enemy_type, int enemy_state) {

			switch (enemy_type) {
			case SNAIL:
				switch (enemy_state) {
				case RUNNING:
					return 8;
				case ATTACK:
					return 8;
				case DEAD:
					return 8;

				}
			}

			return 0;

		}
		
		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case SNAIL:
				return 1;
			default:
				return 1;
			}
		}
		
		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case SNAIL:
				return 0;
			default:
				return 0;
			}

		}
		
	}

	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int ATTACK = 0;
		public static final int GROUND = 1;
		public static final int DEAD = 2;
		public static final int FALLING = 3;
		public static final int HIT = 4;
		public static final int IDLE = 5;
		public static final int JUMP = 6;
		public static final int RUNNING = 7;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
				case RUNNING:
					return 10;
				case IDLE:
					return 10;
				case HIT:
					return 1;
				case JUMP:
					return 3;
				case GROUND:
					return 1;
				case FALLING:
					return 3;
				case ATTACK:
					return 10;
				case DEAD:
					return 10;
				default:
					return 1;
			}
		}
	}
	
	

}
