package enemies;

import static enemies.Constants.Enemies.BORE;

public class Bore extends Enemy {

	public Bore(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, BORE, em);

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
