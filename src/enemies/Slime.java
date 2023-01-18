package enemies;

import static enemies.Constants.Enemies.SLIME;

public class Slime extends Enemy {

	public Slime(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, SLIME,em);
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}
