package enemies;

import javafx.scene.canvas.GraphicsContext;
import logic.Field;
import logic.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class EnemyManager {

	private PathPoint start, end;
	private int[][] roadDirArr;

	public EnemyManager(PathPoint start, PathPoint end) {
		this.start = start;
		this.end = end;
		loadRoadDirArr();

	}

	private void loadRoadDirArr() {
		roadDirArr = Field.dir;
	}

	public void update() {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof Enemy) {
				if (((Enemy) entity).isAlive()) {
					updateEnemyMoveNew((Enemy) entity);
				}
			}
		}

	}

	private void updateEnemyMoveNew(Enemy e) {
		PathPoint currTile = getEnemyTile(e);
		int dir = roadDirArr[currTile.getyCord()][currTile.getxCord()];

		e.move(Constants.Enemies.GetSpeed(e.getEnemyType()), dir);

		PathPoint newTile = getEnemyTile(e);

		if (!isTilesTheSame(currTile, newTile)) {
			if (isTilesTheSame(newTile, end)) {
				e.kill();
				GameLogic.setHealth(GameLogic.getHealth() - 1);
				return;
			}
			int newDir = roadDirArr[newTile.getyCord()][newTile.getxCord()];
			if (newDir != dir) {
				e.setPos(newTile.getxCord() * 40, newTile.getyCord() * 40);
				e.setLastDir(newDir);
			}
		}

	}

	private PathPoint getEnemyTile(Enemy e) {

		switch (e.getLastDir()) {
		case Constants.Direction.LEFT:
			return new PathPoint((int) ((e.getX() + 39) / 40), (int) (e.getY() / 40));
		case Constants.Direction.UP:
			return new PathPoint((int) (e.getX() / 40), (int) ((e.getY() + 39) / 40));
		case Constants.Direction.RIGHT:
		case Constants.Direction.DOWN:
			return new PathPoint((int) (e.getX() / 40), (int) (e.getY() / 40));

		}

		return new PathPoint((int) (e.getX() / 40), (int) (e.getY() / 40));
	}

	private boolean isTilesTheSame(PathPoint currTile, PathPoint newTile) {
		if (currTile.getxCord() == newTile.getxCord())
			if (currTile.getyCord() == newTile.getyCord())
				return true;
		return false;
	}

	public void spawnEnemy(int nextEnemy) {
		addEnemy(nextEnemy);
	}

	public void addEnemy(int enemyType) {

		int x = start.getxCord() * 40;
		int y = start.getyCord() * 40;

		switch (enemyType) {
		case Constants.Enemies.SLIME:
			RenderableHolder.getInstance().add(new Slime(x, y, 0, this));
			break;
		case Constants.Enemies.GHOST:
			RenderableHolder.getInstance().add(new Ghost(x, y, 0, this));
			break;
		case Constants.Enemies.BORE:
			RenderableHolder.getInstance().add(new Bore(x, y, 0, this));
			break;
		case Constants.Enemies.DIREBORE:
			RenderableHolder.getInstance().add(new DireBore(x, y, 0, this));
			break;
		case Constants.Enemies.BOSS:
			RenderableHolder.getInstance().add(new Boss(x, y, 0, this));
			break;
		}

	}

	public void draw(GraphicsContext gc) {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof Enemy) {
				if (((Enemy) entity).isAlive()) {
					((Enemy) entity).draw(gc);					
				}
			}
		}
	}
	
	public static int getAmountOfAliveEnemies() {
		int size = 0;
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof Enemy) {
				if (((Enemy) entity).isAlive()) {
					size++;					
				}
			}
		}
		return size;
	}

	public void rewardPlayer(int enemyType) {
		GameLogic.setCoin(GameLogic.getCoin() + Constants.Enemies.GetReward(enemyType));
	}

	public static void reset() {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof Enemy) {
				RenderableHolder.getInstance().getEntities().remove(entity);
			}
		}
	}

}
