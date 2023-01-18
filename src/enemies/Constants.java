package enemies;

public class Constants {

	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class Enemies {

		public static final int SLIME = 0;
		public static final int GHOST = 1;
		public static final int BORE = 2;
		public static final int DIREBORE = 3;
		public static final int BOSS = 4;

		public static int GetReward(int enemyType) {
			switch (enemyType) {
			case SLIME:
				return 5;
			case GHOST:
				return 20;
			case BORE:
				return 25;
			case DIREBORE:
				return 40;
			case BOSS:
				return 300;
			}
			return 0;
		}

		public static float GetSpeed(int enemyType) {
			switch (enemyType) {
			case SLIME:
				return 1.8f;
			case GHOST:
				return 1.2f;
			case BORE:
				return 1.0f;
			case DIREBORE:
				return 2.8f;
			case BOSS:
				return 0.5f;
			}
			return 0;
		}

		public static int GetStartHealth(int enemyType) {
			switch (enemyType) {
			case SLIME:
				return 80;
			case GHOST:
				return 300;
			case BORE:
				return 500;
			case DIREBORE:
				return 250;
			case BOSS:
				return 2000;
			}
			return 0;
		}
	}

	public static class Tiles {
		public static final int GRASS_TILE = 0;
		public static final int ROAD_TILE = 1;
	}

}
