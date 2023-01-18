package enemies;

import static enemies.Constants.Enemies.BOSS;

public class Boss extends Enemy {

	public Boss(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, BOSS, em);

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
