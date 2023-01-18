package tower;

import enemies.Enemy;
import input.InputUtility;
import logic.Field;
import logic.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class TowerManager {

	private boolean waitPlaceTower;
	private static int towerType;
	public static int towerAmount;

	public TowerManager() {
		waitPlaceTower = false;
	}

	public boolean placeTower() {
		if (InputUtility.isLeftClickTriggered()) {
			int x = ((int) (InputUtility.mouseX / 40)) * 40;
			int y = ((int) (InputUtility.mouseY / 40)) * 40;

			if (tileIsPlaced(x, y))
				return false;

			switch (towerType) {
			case 0:
				RenderableHolder.getInstance().getEntities().add(new ElfTower(x, y, towerAmount++));
				break;
			case 1:
				RenderableHolder.getInstance().getEntities().add(new WitchTower(x, y, towerAmount++));
				break;
			case 2:
				RenderableHolder.getInstance().getEntities().add(new RobotTower(x, y, towerAmount++));
				break;
			}

			GameLogic.setCoin(GameLogic.getCoin() - tower.Constants.Towers.GetTowerCost(towerType));
			waitPlaceTower = false;
			return true;
		}
		return false;
	}

	public boolean tileIsPlaced(int x, int y) {
		// if field[y][x] == 1 is path can't place
		if (Field.getTerrain(x / 40, y / 40) == 1)
			return true;
		// if x y placed tower already can't place
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof BaseTower) {
				if (((BaseTower) entity).getX() == x && ((BaseTower) entity).getY() == y) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isWaitPlaceTower() {
		return waitPlaceTower;
	}

	public void setWaitPlaceTower(boolean waitPlaceTower) {
		this.waitPlaceTower = waitPlaceTower;
	}

	public static int getTowerType() {
		return towerType;
	}

	public static void setTowerType(int towerType) {
		TowerManager.towerType = towerType;
	}

	public void sellTower(IRenderable entity) {
		System.out.println("sell tower");
		System.out.println(entity);
		System.out.println(RenderableHolder.getInstance().getEntities());
		boolean b = RenderableHolder.getInstance().getEntities().removeIf((e) -> {
			return e == entity;
		});
		System.out.println(b);
		System.out.println(RenderableHolder.getInstance().getEntities());
		GameLogic.setCoin(GameLogic.getCoin() + 30);
	}

	public void update() {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof BaseTower) {
				((BaseTower) entity).update();
				attackEnemyIfClose(((BaseTower) entity));
			}
		}
	}

	private void attackEnemyIfClose(BaseTower t) {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof Enemy) {
				if (((Enemy) entity).isAlive()) {
					if (isEnemyInRange(t, ((Enemy) entity))) {
						if (t.isCooldownOver()) {
							GameLogic.getInstance().shootEnemy(t, ((Enemy) entity));
							t.resetCooldown();
						}
					}
				}
			}
		}
	}

	public static int getHypoDistance(float x1, float y1, float x2, float y2) {

		float xDiff = Math.abs(x1 - x2);
		float yDiff = Math.abs(y1 - y2);

		return (int) Math.hypot(xDiff, yDiff);

	}

	private boolean isEnemyInRange(BaseTower t, Enemy e) {
		int range = getHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
		return range < t.getRange();
	}

	public BaseTower getTowerAt(int x, int y) {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof BaseTower) {
				if (((BaseTower) entity).getX() == x && ((BaseTower) entity).getY() == y);
					return ((BaseTower) entity);
			}
		}
		return null;
	}
}
