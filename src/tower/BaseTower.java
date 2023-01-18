package tower;

import static tower.Constants.Towers.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.Entity;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class BaseTower extends Entity {
	private int x, y, id, towerType, cdTick, dmg;
	private float range, cooldown;
	private int tier;
	private Image image;

	public BaseTower(int x, int y, int id, int towerType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		tier = 1;
		setImage();
		setDefaultDmg();
		setDefaultRange();
		setDefaultCooldown();
	}

	public void update() {
		cdTick++;
	}

	public void upgradeTower() {

		switch (towerType) {
		case ELF:
			if (GameLogic.getCoin() >= 80 && tier < 6) {
				GameLogic.setCoin(GameLogic.getCoin() - 80);
				this.tier++;
				dmg += 20;
				range += 20;
				cooldown -= 5;
			}
			break;
		case WITCH:
			if (GameLogic.getCoin() >= 100 && tier < 4) {
				GameLogic.setCoin(GameLogic.getCoin() - 100);
				this.tier++;
				dmg += 5;
				range += 20;
				cooldown -= 20;
			}
			break;
		case ROBOT:
			if (GameLogic.getCoin() >= 150 && tier < 4) {
				GameLogic.setCoin(GameLogic.getCoin() - 150);
				this.tier++;
				dmg += 50;
				range += 15;
				cooldown -= 15;
			}
			break;
		}
		if (cooldown < 15) cooldown = 15;
	}

	public boolean isCooldownOver() {

		return cdTick >= cooldown;
	}

	public void resetCooldown() {
		cdTick = 0;
	}

	private void setDefaultCooldown() {
		cooldown = Constants.Towers.GetDefaultCooldown(towerType);

	}

	private void setDefaultRange() {
		range = Constants.Towers.GetDefaultRange(towerType);

	}

	private void setDefaultDmg() {
		dmg = Constants.Towers.GetStartDmg(towerType);

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTowerType() {
		return towerType;
	}

	public void setTowerType(int towerType) {
		this.towerType = towerType;
	}

	public int getDmg() {
		return dmg;
	}

	public float getRange() {
		return range;
	}

	public float getCooldown() {
		return cooldown;
	}

	public int getTier() {
		return tier;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(image, getX(), getY());
	}

	public static Image getImage(int towerType) {
		return new WritableImage(RenderableHolder.sprite.getPixelReader(), 40 * towerType, 0, 40, 40);
	}

	public void setImage() {
		this.image = new WritableImage(RenderableHolder.sprite.getPixelReader(), 40 * towerType, 0, 40, 40);
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
