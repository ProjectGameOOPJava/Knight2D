package utilz;

public class Constants {
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
