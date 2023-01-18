package drawing;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;
import tower.TowerManager;

public class ControlPane extends VBox {

	private static ControlPane instance = new ControlPane();

	private final String IMAGE_HEARTH_PATH = ClassLoader.getSystemResource("images/hearth.png").toString();
	private final String IMAGE_WAVE_PATH = ClassLoader.getSystemResource("images/wave.png").toString();
	private final String IMAGE_COIN_PATH = ClassLoader.getSystemResource("images/coin.png").toString();

	private final String IMAGE_ELF_PATH = ClassLoader.getSystemResource("images/buttonElf.png").toString();
	private final String IMAGE_WITCH_PATH = ClassLoader.getSystemResource("images/buttonWitch.png").toString();
	private final String IMAGE_ROBOT_PATH = ClassLoader.getSystemResource("images/buttonRobot.png").toString();	
	
	private final Font font = Font.loadFont(ClassLoader.getSystemResource("font/SAOUITT-Regular.ttf").toString(), 42);

	private Text healthText;
	private Text waveText;
	private Text coinText;

	private TextBox healthTextBox;
	private TextBox waveTextBox;
	private TextBox coinTextBox;

	private Button witchButton;
	private Button robotButton;
	private Button elfButton;
	private GameScene gameScene;

//	private final int ZOOM_SIZE = 60;
	private final int NORMAL_SIZE = 120;
	private final int ICON_SIZE = 40;
	private final int SPACING = 10;

	public ControlPane() {
		setAlignment(Pos.TOP_CENTER);
		setPrefSize(GameScreen.GAMEWIDTH - GameScreen.GAMEHIGHT, GameScreen.GAMEHIGHT);
		setMaxSize(GameScreen.GAMEWIDTH - GameScreen.GAMEHIGHT, GameScreen.GAMEHIGHT);
		setMinSize(GameScreen.GAMEWIDTH - GameScreen.GAMEHIGHT, GameScreen.GAMEHIGHT);
		setSpacing(SPACING);

		initialHealthText();
		initialWaveText();
		initialCoinText();
		initialWitchButton();
		initialElfButton();
		initialRobotButton();

		getChildren().add(healthTextBox);
		getChildren().add(waveTextBox);
		getChildren().add(coinTextBox);

		getChildren().add(elfButton);
		getChildren().add(witchButton);
		getChildren().add(robotButton);

	}

	private void initialHealthText() {
		healthText = new Text("" + GameLogic.getHealth());
		ImageView view = new ImageView(new Image(IMAGE_HEARTH_PATH));
		view.setFitHeight(ICON_SIZE);
		view.setFitWidth(ICON_SIZE);
		healthTextBox = new TextBox(view, healthText);
		setMargin(healthTextBox, new Insets(SPACING, 0, 0, 0));
	}

	public void updateHealth(int health) {
		healthText.setText("" + health);
	}

	private void initialWaveText() {
		waveText = new Text("" + GameLogic.getWave());
		ImageView view = new ImageView(new Image(IMAGE_WAVE_PATH));
		view.setFitHeight(ICON_SIZE);
		view.setFitWidth(ICON_SIZE);
		waveTextBox = new TextBox(view, waveText);
	}

	public void updateWave(int wave) {
		waveText.setText("" + wave);
	}

	private void initialCoinText() {
		coinText = new Text("" + GameLogic.getCoin());
		ImageView view = new ImageView(new Image(IMAGE_COIN_PATH));
		view.setFitHeight(ICON_SIZE);
		view.setFitWidth(ICON_SIZE);
		coinTextBox = new TextBox(view, coinText);
	}

	public void updateCoin(int coin) {
		coinText.setText("" + coin);
	}

	private void initialElfButton() {
		elfButton = new Button();
		ImageView view = new ImageView(new Image(IMAGE_ELF_PATH));
		view.setFitHeight(NORMAL_SIZE);
		view.setFitWidth(NORMAL_SIZE);
		elfButton.setPrefSize(NORMAL_SIZE, NORMAL_SIZE);
		elfButton.setGraphic(view);
		elfButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		elfButton.setCursor(Cursor.HAND);
		elfButton.setText("");
		elfButton.setFont(font);

		elfButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				elfButton.setGraphic(null);
				elfButton.setEffect(new DropShadow());
				elfButton.setText("" + tower.Constants.Towers.GetTowerCost(0));
			}
		});

		elfButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				elfButton.setEffect(null);
				elfButton.setGraphic(view);
				
				elfButton.setText(null);
			}
		});

		elfButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub

				if (GameLogic.getCoin() >= tower.Constants.Towers.GetTowerCost(0)) {
					GameLogic.getInstance().getTowerManager().setWaitPlaceTower(true);
					TowerManager.setTowerType(0);
				}
			}
		});
	}

	private void initialWitchButton() {
		witchButton = new Button();
		ImageView view = new ImageView(new Image(IMAGE_WITCH_PATH));
		view.setFitHeight(NORMAL_SIZE);
		view.setFitWidth(NORMAL_SIZE);
		witchButton.setPrefSize(NORMAL_SIZE, NORMAL_SIZE);
		witchButton.setGraphic(view);
		witchButton
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		witchButton.setCursor(Cursor.HAND);
		witchButton.setText("");
		witchButton.setFont(font);

		witchButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				witchButton.setGraphic(null);
				witchButton.setEffect(new DropShadow());
				witchButton.setText("" + tower.Constants.Towers.GetTowerCost(1));
			}
		});

		witchButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				witchButton.setEffect(null);
				witchButton.setGraphic(view);
				witchButton.setText(null);
			}
		});

		witchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (GameLogic.getCoin() >= tower.Constants.Towers.GetTowerCost(1)) {
					GameLogic.getInstance().getTowerManager().setWaitPlaceTower(true);
					TowerManager.setTowerType(1);
				}
			}
		});
	}

	private void initialRobotButton() {
		robotButton = new Button();
		ImageView view = new ImageView(new Image(IMAGE_ROBOT_PATH));
		view.setFitHeight(NORMAL_SIZE);
		view.setFitWidth(NORMAL_SIZE);
		robotButton.setPrefSize(NORMAL_SIZE, NORMAL_SIZE);
		robotButton.setGraphic(view);
		robotButton
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		robotButton.setCursor(Cursor.HAND);
		robotButton.setText("");
		robotButton.setFont(font);
		
		robotButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				robotButton.setGraphic(null);
				robotButton.setEffect(new DropShadow());
				robotButton.setText("" + tower.Constants.Towers.GetTowerCost(2));
			}
		});

		robotButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				robotButton.setEffect(null);
				robotButton.setGraphic(view);
				robotButton.setText(null);
			}
		});

		robotButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (GameLogic.getCoin() >= tower.Constants.Towers.GetTowerCost(2)) {
					GameLogic.getInstance().getTowerManager().setWaitPlaceTower(true);
					TowerManager.setTowerType(2);
				}
			}
		});
	}

	public Text getHealthText() {
		return healthText;
	}

	public void setHealthText(Text healthText) {
		this.healthText = healthText;
	}

	public Button getWitchButton() {
		return witchButton;
	}

	public void setWitchButton(Button witchButton) {
		this.witchButton = witchButton;
	}

	public Button getRobotButton() {
		return robotButton;
	}

	public void setRobotButton(Button robotButton) {
		this.robotButton = robotButton;
	}

	public Button getElfButton() {
		return elfButton;
	}

	public void setElfButton(Button elfButton) {
		this.elfButton = elfButton;
	}

	public GameScene getGameScene() {
		return gameScene;
	}

	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
	}

	public static ControlPane getInstance() {
		return instance;
	}

}
