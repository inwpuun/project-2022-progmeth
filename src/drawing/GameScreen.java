package drawing;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import main.EditTowerPane;
import main.EndGameScene;
import main.GameState;
import main.PausePane;
import scenes.Playing;

public class GameScreen extends Scene {

	public HBox root;
	public static StackPane backRoot;
	private ControlGridPane controlGridPane;
	public static AnimationTimer animation;

	public static final int GAMEWIDTH = 800;
	public static final int GAMEHIGHT = 640;

	public static AudioClip sound = new AudioClip(ClassLoader.getSystemResource("audio/interstellarTravel.mp3").toString());
	
	private final double FPS = 60.0;
	private long lastFrame = System.nanoTime();
	private double timePerFrame = 1000000000.0 / FPS;

	public GameScreen() {
		super(new Pane());
		backRoot = new StackPane();
		root = new HBox();
		root.setPrefSize(GAMEWIDTH, GAMEHIGHT);

		controlGridPane = new ControlGridPane();

		root.getChildren().add(GameScene.getInstance());
		root.getChildren().add(controlGridPane);

		backRoot.getChildren().add(root);
		backRoot.getChildren().add(PausePane.getInstance());
		backRoot.getChildren().add(EditTowerPane.getInstance());
		GameScreen.backRoot.getChildren().add(EndGameScene.getInstance());
		
		setRoot(backRoot);
		sound.play();

		animation = new AnimationTimer() {

			public void handle(long now) {
				now = System.nanoTime();
				if (now - lastFrame >= timePerFrame) {
//					System.out.println("FPS : " + (int) Math.ceil(1000000000.0 / (now - lastFrame)));
					if (!sound.isPlaying()) sound.play();
					lastFrame = now;
					GameScene.getInstance().requestFocus();

					switch (GameState.getGameState()) {
					case PLAYING:
						Playing.playing();
						break;
					case PAUSE:
						break;
					case ENDING:
						break;
					default:
						break;

					}

				}
			}
		};
		animation.start();

	}

}
