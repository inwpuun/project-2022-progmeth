package enemies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.Entity;
import sharedObject.RenderableHolder;
import java.awt.Rectangle;

public abstract class Enemy extends Entity {
	protected EnemyManager enemyManager;
	protected float x, y;
	protected Rectangle bounds;
	protected int health;
	protected int maxHealth;
	protected int ID;
	protected int enemyType;
	protected int lastDir;
	protected boolean alive = true;
	protected int slowTickLimit = 120;
	protected int slowTick = slowTickLimit;

	public Enemy(float x, float y, int ID, int enemyType, EnemyManager enemyManager) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.enemyType = enemyType;
		this.enemyManager = enemyManager;
		bounds = new Rectangle((int) x, (int) y, 32, 32);
		lastDir = -1;
		setStartHealth();
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(getImage(enemyType), (int)getX(), (int)getY());
	}

	public Image getImage(int enemyType) {
		return new WritableImage(RenderableHolder.sprite.getPixelReader(), 200 + 40 * enemyType, 40, 40, 40);
	}
	
	private void setStartHealth() {
		health = Constants.Enemies.GetStartHealth(enemyType);
		maxHealth = health;
	}

	public void hurt(int dmg) {
		this.health -= dmg;
		if (health <= 0) {
			alive = false;
			this.setDestroyed(true);
			enemyManager.rewardPlayer(enemyType);
		}

	}

	public void kill() {
		// Is for killing enemy, when it reaches the end.
		alive = false;
		health = 0;
		this.setDestroyed(true);
	}

	public void slow() {
		slowTick = 0;
	}

	public void move(float speed, int dir) {
		lastDir = dir;

		if (slowTick < slowTickLimit) {
			slowTick++;
			speed *= 0.5f;
		}

		switch (dir) {
		case Constants.Direction.LEFT:
			this.x -= speed;
			break;
		case Constants.Direction.UP:
			this.y -= speed;
			break;
		case Constants.Direction.RIGHT:
			this.x += speed;
			break;
		case Constants.Direction.DOWN:
			this.y += speed;
			break;
		}

		updateHitbox();
	}

	private void updateHitbox() {
		bounds.x = (int) x;
		bounds.y = (int) y;
	}

	public void setPos(int x, int y) {
		// Don't use this one for moving the enemy.
		this.x = x;
		this.y = y;
	}

	public float getHealthBarFloat() {
		return health / (float) maxHealth;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getHealth() {
		return health;
	}

	public int getID() {
		return ID;
	}

	public int getEnemyType() {
		return enemyType;
	}

	public int getLastDir() {
		return lastDir;
	}

	public boolean isAlive() {
		return alive;
	}

	public boolean isSlowed() {
		return slowTick < slowTickLimit;
	}

	public void setLastDir(int newDir) {
		this.lastDir = newDir;
	}

}
