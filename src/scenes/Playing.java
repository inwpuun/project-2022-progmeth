package scenes;

import drawing.ControlPane;
import drawing.GameScene;
import input.InputUtility;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class Playing {
	private static int frame = 0;

	public static void playing() {
		if (frame == 60) {
			frame = 0;
			GameLogic.setCoin(GameLogic.getCoin() + 1);
			ControlPane.getInstance().updateCoin(GameLogic.getCoin());
		}
		frame++;

		GameScene.getInstance().paintComponent();
		GameLogic.getInstance().logicUpdate();
		RenderableHolder.getInstance().update();
		InputUtility.updateInputState();

	}

	public static void pausing() {

	}
}
