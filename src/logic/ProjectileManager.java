package logic;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import enemies.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import tower.BaseTower;

import static tower.Constants.Towers.*;
import static tower.Constants.Projectiles.*;

public class ProjectileManager {

	private ArrayList<Explosion> explosions = new ArrayList<>();
	private int projId = 0;

	public Image getexploImg(int i) {
		return new WritableImage(RenderableHolder.sprite.getPixelReader(), 160 + 40 * i, 80, 40, 40);
	}

	public void newProjectile(BaseTower t, Enemy e) {
		int type = getProjType(t);

		int xDist = (int) (t.getX() - e.getX());
		int yDist = (int) (t.getY() - e.getY());
		int totDist = Math.abs(xDist) + Math.abs(yDist);

		float xPer = (float) Math.abs(xDist) / totDist;

		float xSpeed = xPer * tower.Constants.Projectiles.GetSpeed(type);
		float ySpeed = tower.Constants.Projectiles.GetSpeed(type) - xSpeed;

		if (t.getX() > e.getX())
			xSpeed *= -1;
		if (t.getY() > e.getY())
			ySpeed *= -1;

		float rotate = 0;

		float arcValue = (float) Math.atan(yDist / (float) xDist);
		rotate = (float) Math.toDegrees(arcValue);

		if (xDist < 0)
			rotate += 180;

		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof Projectile) {
				if (!((Projectile) entity).isActive())
					if (((Projectile) entity).getProjectileType() == type) {
						((Projectile) entity).reuse(t.getX() + 20, t.getY() + 20, xSpeed, ySpeed, t.getDmg(), rotate);
						return;
					}
			}
		}
		RenderableHolder.getInstance().getEntities()
				.add(new Projectile(t.getX() + 20, t.getY() + 20, xSpeed, ySpeed, t.getDmg(), rotate, projId++, type));

	}

	public void update() {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof Projectile) {
				if (((Projectile) entity).isActive()) {
					((Projectile) entity).move();
					if (isProjHittingEnemy(((Projectile) entity))) {
						((Projectile) entity).setActive(false);
						if (((Projectile) entity).getProjectileType() == LASER) {
							explosions.add(new Explosion(((Projectile) entity).getPos()));
							explodeOnEnemies(((Projectile) entity));
						}
					} else if (isProjOutsideBounds(((Projectile) entity))) {
						((Projectile) entity).setActive(false);
					}
				}
			}
		}

		for (Explosion e : explosions)
			if (e.getIndex() < 7)
				e.update();

	}

	private void explodeOnEnemies(Projectile p) {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof Enemy) {
				if (((Enemy) entity).isAlive()) {
					float radius = 40.0f;

					float xDist = Math.abs(p.getPos().x - ((Enemy) entity).getX());
					float yDist = Math.abs(p.getPos().y - ((Enemy) entity).getY());

					float realDist = (float) Math.hypot(xDist, yDist);

					if (realDist <= radius)
						((Enemy) entity).hurt(p.getDmg());
				}
			}
		}

	}

	private boolean isProjHittingEnemy(Projectile p) {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof Enemy) {
				if (((Enemy) entity).isAlive())
					if (((Enemy) entity).getBounds().contains(p.getPos())) {
						((Enemy) entity).hurt(p.getDmg());
						if (p.getProjectileType() == SNOWBALL)
							((Enemy) entity).slow();
						return true;
					}
			}
		}
		return false;
	}

	private boolean isProjOutsideBounds(Projectile p) {
		if (p.getPos().x >= 0)
			if (p.getPos().x <= 800)
				if (p.getPos().y >= 0)
					if (p.getPos().y <= 640)
						return false;
		return true;
	}

	public void draw(GraphicsContext gc) {

		drawExplosions(gc);

	}

	private void drawExplosions(GraphicsContext gc) {
		for (Explosion e : explosions)
			if (e.getIndex() < 7)
				e.draw(gc);
	}

	private int getProjType(BaseTower t) {
		switch (t.getTowerType()) {
		case ELF:
			return ARROW;
		case WITCH:
			return SNOWBALL;
		case ROBOT:
			return LASER;
		}
		return 0;
	}

	public class Explosion extends Entity {

		private Point2D.Float pos;
		private int exploTick, exploIndex;

		public Explosion(Point2D.Float pos) {
			this.pos = pos;
		}

		public void update() {
			exploTick++;
			if (exploTick >= 6) {
				exploTick = 0;
				exploIndex++;
			}
		}

		public int getIndex() {
			return exploIndex;
		}

		public Point2D.Float getPos() {
			return pos;
		}

		@Override
		public int getZ() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public void draw(GraphicsContext gc) {
			// TODO Auto-generated method stub
			gc.drawImage(getexploImg(exploIndex), (int) getPos().x - 16, (int) getPos().y - 16);
		}
	}

	public void reset() {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity instanceof Projectile)
				RenderableHolder.getInstance().getEntities().remove(entity);
		}
		explosions.clear();
		projId = 0;
	}

}
