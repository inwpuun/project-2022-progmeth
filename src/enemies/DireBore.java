package enemies;

import static enemies.Constants.Enemies.DIREBORE;

public class DireBore extends Enemy {

	public DireBore(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, DIREBORE, em);
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
