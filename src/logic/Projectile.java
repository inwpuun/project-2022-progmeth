package logic;

import java.awt.geom.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class Projectile extends Entity {

	private Point2D.Float pos;
	private int id, projectileType, dmg;
	private float xSpeed, ySpeed, rotation;
	private boolean active = true;

	public Projectile(float x, float y, float xSpeed, float ySpeed, int dmg, float rotation, int id, int projectileType) {
		pos = new Point2D.Float(x, y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.dmg = dmg;
		this.rotation = rotation;
		this.id = id;
		this.projectileType = projectileType;
	}

	public void reuse(int x, int y, float xSpeed, float ySpeed, int dmg, float rotate) {
		pos = new Point2D.Float(x, y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.dmg = dmg;
		this.rotation = rotate;
		active = true;
	}

	public void move() {
		pos.x += xSpeed;
		pos.y += ySpeed;
	}

	public Point2D.Float getPos() {
		return pos;
	}

	public void setPos(Point2D.Float pos) {
		this.pos = pos;
	}

	public int getId() {
		return id;
	}

	public int getProjectileType() {
		return projectileType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getDmg() {
		return dmg;
	}

	public float getRotation() {
		return rotation;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isActive()) {
			gc.translate(getPos().x, getPos().y);
			gc.rotate(getRotation() + 180);
			gc.drawImage(getProjImg(getProjectileType()), -20, -20);
			gc.rotate(-getRotation() - 180);
			gc.translate(-getPos().x, -getPos().y);
		}
	}
	
	public Image getProjImg(int towerType) {
		return new WritableImage(RenderableHolder.sprite.getPixelReader(), 40 * towerType, 40 * 2, 40, 40);
	}

}
