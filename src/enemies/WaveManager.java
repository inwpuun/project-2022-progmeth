package enemies;

import java.util.ArrayList;
import java.util.Arrays;

import logic.GameLogic;

public class WaveManager {

	private ArrayList<Wave> waves = new ArrayList<>();
	private int enemySpawnTickLimit = 60 * 1;
	private int enemySpawnTick = enemySpawnTickLimit;
	private int enemyIndex, waveIndex;
	private int waveTickLimit = 60 * 5;
	private int waveTick = 0;
	private boolean waveStartTimer, waveTickTimerOver;

	public WaveManager() {
		createWaves();
	}

	public void update() {
		if (enemySpawnTick < enemySpawnTickLimit)
			enemySpawnTick++;

		if (waveStartTimer) {
			waveTick++;
			if (waveTick >= waveTickLimit) {
				waveTickTimerOver = true;
			}
		}

	}

	public void increaseWaveIndex() {
		waveIndex++;
		GameLogic.setWave(waveIndex + 1);
		waveTick = 0;
		waveTickTimerOver = false;
		waveStartTimer = false;
	}

	public boolean isWaveTimerOver() {

		return waveTickTimerOver;
	}

	public void startWaveTimer() {
		waveStartTimer = true;
	}

	public int getNextEnemy() {
		enemySpawnTick = 0;
		return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
	}


	
	private void createWaves() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 1, 0, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 2, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 1, 2, 1, 2, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 2, 1, 1, 0, 0, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 1, 1, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 2, 3, 2, 3, 2, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 3, 3, 3, 0, 0, 0, 3, 3, 3, 0, 3, 3, 3, 3, 0, 3, 0, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 3, 1, 1, 1, 1, 0, 3, 3, 2, 2, 3, 1, 0, 3, 3, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 2, 1, 2, 3, 3, 3, 2, 1, 2, 1, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 0, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 3, 3, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 3, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 3, 4, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 2, 3, 4, 1, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 2, 1, 1, 4, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 1, 1, 2, 4, 3, 3, 3, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 4, 0, 1, 1, 1, 1, 2, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 4, 2, 1, 1, 3, 3, 2, 1, 3, 2, 1, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 1, 4, 4))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 1, 1, 2, 4, 4, 2, 1, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 4, 1, 2, 1, 4, 4, 2, 3, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 4, 4, 3, 4, 3, 3, 3, 3, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 3, 3, 4, 4, 3, 3, 3, 3, 3, 3, 4, 4, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 3, 2, 1, 0, 4, 3, 2, 1, 0, 4, 3, 2, 1, 0, 4, 3, 2, 1, 0, 4, 3, 2, 1, 0, 4, 3, 2, 1, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 1, 4, 3, 3, 3, 3, 3, 4, 2, 1, 4, 2, 4, 2, 3, 4))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 1, 3, 4, 1, 1, 1, 4, 3, 3, 3, 4, 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 1, 3, 4, 4, 4, 3, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 3, 4, 3, 4, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4))));

	}
	
	public ArrayList<Wave> getWaves() {
		return waves;
	}

	public boolean isTimeForNewEnemy() {
		return enemySpawnTick >= enemySpawnTickLimit;
	}

	public boolean isThereMoreEnemiesInWave() {
		return enemyIndex < waves.get(waveIndex).getEnemyList().size();
	}

	public boolean isThereMoreWaves() {
		return waveIndex + 1 < waves.size();
	}

	public void resetEnemyIndex() {
		enemyIndex = 0;
	}

	public int getWaveIndex() {
		return waveIndex;
	}

	public float getTimeLeft() {
		float ticksLeft = waveTickLimit - waveTick;
		return ticksLeft / 60.0f;
	}

	public boolean isWaveTimerStarted() {
		return waveStartTimer;
	}

	public void reset() {
		waves.clear();
		createWaves();
		enemyIndex = 0;
		waveIndex = 0;
		waveStartTimer = false;
		waveTickTimerOver = false;
		waveTick = 0;
		enemySpawnTick = enemySpawnTickLimit;
	}

}
