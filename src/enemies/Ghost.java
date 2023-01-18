package enemies;

import static enemies.Constants.Enemies.GHOST;

public class Ghost extends Enemy {

	public Ghost(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, GHOST, em);

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
