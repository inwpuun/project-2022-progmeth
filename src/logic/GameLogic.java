package logic;

import java.util.ArrayList;
import java.util.List;
import drawing.ControlPane;
import drawing.GameScene;
import enemies.Enemy;
import enemies.EnemyManager;
import enemies.PathPoint;
import enemies.WaveManager;
import input.InputUtility;
import main.EditTowerPane;
import main.EndGameScene;
import main.GameState;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import tower.BaseTower;
import tower.TowerManager;

public class GameLogic {

	private List<Entity> gameObjectContainer;

	private static int coin = 120;
	private static int health = 20;
	private static int wave = 1;

	private static GameLogic instance = null;
	private TowerManager towerManager;
	private EnemyManager enemyManager;
	private static WaveManager waveManager;
	private ProjectileManager projectileManager;
	
	private static PathPoint start = new PathPoint(0, 12);
	private static PathPoint end = new PathPoint(15, 3);

	public GameLogic() {
		this.gameObjectContainer = new ArrayList<Entity>();

		Field field = new Field();
		RenderableHolder.getInstance().add(field);

		towerManager = new TowerManager();
		System.out.println(start.getxCord() + " " + start.getyCord());
		enemyManager = new EnemyManager(start, end);
		waveManager = new WaveManager();
		projectileManager = new ProjectileManager();
	}

	public static GameLogic getInstance() {
		if (instance == null) instance = new GameLogic();
		return instance;
	}

	protected void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	public void logicUpdate() {
		waveManager.update();

		if (isAllEnemiesDead()) {
			if (isThereMoreWaves()) {
				waveManager.startWaveTimer();
				if (isWaveTimerOver()) {
					waveManager.increaseWaveIndex();
					EnemyManager.reset();
					waveManager.resetEnemyIndex();

				}
			}
		}

		if (isTimeForNewEnemy()) {
			if (!waveManager.isWaveTimerOver())
				spawnEnemy();
		}
			enemyManager.update();
			towerManager.update();
			projectileManager.update();
		

		if (InputUtility.isLeftClickTriggered()) {
			int x = ((int) (InputUtility.mouseX / 40)) * 40;
			int y = ((int) (InputUtility.mouseY / 40)) * 40;
			if (EditTowerPane.getInstance().isVisible()) {
				EditTowerPane.getInstance().setVisible(false);
			}
			
			for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
				if (entity instanceof BaseTower) {
					if (((BaseTower) entity).getX() == x && ((BaseTower) entity).getY() == y) {
						System.out.println(x + " " + y);
						EditTowerPane.getInstance().setXYAndMovePane(x, y, entity);
						EditTowerPane.getInstance().setVisible(true);
					}
				}
			}
		}
			
		if (getTowerManager().isWaitPlaceTower()) {
			getTowerManager().placeTower();
		}
		
		if (GameLogic.getHealth() <= 0) {
			GameState.setGameState(GameState.ENDING);
			EndGameScene.getInstance().setVisible(true);
		}
		
		ControlPane.getInstance().updateCoin(GameLogic.getCoin());
		ControlPane.getInstance().updateHealth(GameLogic.getHealth());
		ControlPane.getInstance().updateWave(GameLogic.getWave());
	}

	private boolean isWaveTimerOver() {

		return waveManager.isWaveTimerOver();
	}

	private boolean isThereMoreWaves() {
		return waveManager.isThereMoreWaves();

	}

	private boolean isAllEnemiesDead() {

		if (waveManager.isThereMoreEnemiesInWave())
			return false;

		if (EnemyManager.getAmountOfAliveEnemies() != 0)
			return false;

		return true;
	}

	private void spawnEnemy() {
		enemyManager.spawnEnemy(waveManager.getNextEnemy());
	}

	private boolean isTimeForNewEnemy() {
		if (waveManager.isTimeForNewEnemy()) {
			if (waveManager.isThereMoreEnemiesInWave())
				return true;
		}

		return false;
	}
	
	public void shootEnemy(BaseTower baseTower, Enemy enemy) {
		projectileManager.newProjectile(baseTower, enemy);
	}

	public static int getCoin() {
		return coin;
	}

	public static void setCoin(int coin) {
		GameLogic.coin = coin;
	}

	public static int getHealth() {
		return health;
	}

	public static void setHealth(int health) {
		GameLogic.health = health;
	}

	public static int getWave() {
		return wave;
	}

	public static void setWave(int wave) {
		GameLogic.wave = wave;
	}

	public TowerManager getTowerManager() {
		return towerManager;
	}

	public void setTowerManager(TowerManager towerManager) {
		this.towerManager = towerManager;
	}

	public static void reset() {
		// TODO Auto-generated method stub
		setCoin(120);
		health = 20;
		wave = 1;
		waveManager.reset();
		TowerManager.towerAmount = 0;

		System.out.println(RenderableHolder.getInstance().getEntities());
		RenderableHolder.getInstance().getEntities().clear();
		System.out.println(RenderableHolder.getInstance().getEntities());

		Field field = new Field();
		RenderableHolder.getInstance().add(field);

		GameScene.getInstance().paintComponent();
		GameLogic.getInstance().logicUpdate();
		RenderableHolder.getInstance().update();
		InputUtility.updateInputState();
	}

}
