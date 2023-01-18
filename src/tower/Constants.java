package tower;

public class Constants {

	public static class Projectiles {
		public static final int ARROW = 0;
		public static final int SNOWBALL = 1;
		public static final int LASER = 2;

		public static float GetSpeed(int type) {
			switch (type) {
			case ARROW:
				return 25f;
			case SNOWBALL:
				return 20f;
			case LASER:
				return 30f;
			}
			return 0f;
		}
	}

	public static class Towers {
		public static final int ELF = 0;
		public static final int WITCH = 1;
		public static final int ROBOT = 2;

		public static int GetTowerCost(int towerType) {
			switch (towerType) {
			case ELF:
				return 50;
			case WITCH:
				return 100;
			case ROBOT:
				return 150;
			}
			return 0;
		}

		public static String GetName(int towerType) {
			switch (towerType) {
			case ELF:
				return "Elf";
			case WITCH:
				return "Witch";
			case ROBOT:
				return "Robot";
			}
			return "";
		}

		public static int GetStartDmg(int towerType) {
			switch (towerType) {
			case ELF:
				return 50;
			case WITCH:
				return 40;
			case ROBOT:
				return 140;
			}

			return 0;
		}

		public static float GetDefaultRange(int towerType) {
			switch (towerType) {
			case ELF:
				return 75;
			case WITCH:
				return 120;
			case ROBOT:
				return 90;
			}

			return 0;
		}

		public static float GetDefaultCooldown(int towerType) {
			switch (towerType) {
			case ELF:
				return 40;
			case WITCH:
				return 70;
			case ROBOT:
				return 100;
			}

			return 0;
		}
	}

	public static class Tiles {
		public static final int GRASS_TILE = 0;
		public static final int ROAD_TILE = 1;
//		public static final int ROAD_TILE = 2;
	}

}