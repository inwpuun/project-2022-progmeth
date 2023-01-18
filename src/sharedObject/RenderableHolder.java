package sharedObject;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListSet;
import javafx.scene.image.Image;

public class RenderableHolder {
	private static RenderableHolder instance = null;

	private ConcurrentSkipListSet<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image sprite;

	static {
		loadResource();
	}

	public RenderableHolder() {
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			if (o1.getZ() == o2.getZ()) {
				if (o1 == o2) {
					return 0;
				} else if (o1.hashCode() > o2.hashCode()) {
					return 1;
				} else {
					return -1;
				}
			}
			return -1;
		};
		entities = new ConcurrentSkipListSet<IRenderable>(comparator);
	}

	public static RenderableHolder getInstance() {
		if (instance == null)
			instance = new RenderableHolder();
		return instance;
	}

	public static void loadResource() {
		sprite = new Image(ClassLoader.getSystemResource("images/tile.png").toString());
	}

	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
	}

	public void update() {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isDestroyed())
				RenderableHolder.getInstance().getEntities().remove(entity);
		}
	}

	public ConcurrentSkipListSet<IRenderable> getEntities() {
		return entities;
	}
}
