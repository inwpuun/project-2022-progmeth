package logic;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {

	protected double x, y;
	protected int z;
	protected boolean visible, destroyed, placed;

	protected Entity() {
		visible = true;
		destroyed = false;
		placed = false;
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public boolean isPlaced() {
		return placed;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
	
	

}
