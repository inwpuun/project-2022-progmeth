package input;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import main.GameState;
import main.PausePane;

public class InputUtility {

	public static double mouseX, mouseY;
	public static boolean mouseOnScreen = true;
	private static boolean isLeftDown = false;
	private static boolean isLeftClickedLastTick = false;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();

	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}
	public static void setKeyPressed(KeyCode keycode,boolean pressed) {
		if(pressed){
			if(!keyPressed.contains(keycode)){
				if (keycode == KeyCode.ESCAPE)  {
					if (GameState.getGameState() == GameState.PLAYING) {
						PausePane.getInstance().setVisible(true);
						GameState.setGameState(GameState.PAUSE);
						System.out.println("GamePause");
					} else if (GameState.getGameState() == GameState.PAUSE) {
						GameState.setGameState(GameState.PLAYING);
						PausePane.getInstance().setVisible(false);
//						GameScene.getInstance().requestFocus();
					}
				}
				keyPressed.add(keycode);
			}
		}else{
			keyPressed.remove(keycode);
		}
		System.out.println(keyPressed);
	}
	
	public static void mouseLeftDown() {
		isLeftDown = true;
		isLeftClickedLastTick = true;
	}

	public static void mouseLeftRelease() {
		isLeftDown = false;
	}

	public static boolean isLeftClickTriggered() {
		return isLeftClickedLastTick;
	}

	public static void updateInputState() {
		isLeftClickedLastTick = false;
	}

}
