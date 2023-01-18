package main;

import drawing.GameScreen;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
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
import sharedObject.IRenderable;
import tower.BaseTower;

public class InformationTower extends VBox {

	private final int ZOOM_SIZE_WIDTH = 60;
	private final int ZOOM_SIZE_HEIGHT = 60;

	private final int SIZE_WIDTH = 50;
	private final int SIZE_HEIGHT = 50;

	private Button closedButton;

	public InformationTower(IRenderable entity) {
		this.setMaxSize(400, 300);
		this.setPrefSize(400, 300);
		this.setAlignment(Pos.CENTER);

		String fontPath = ClassLoader.getSystemResource("font/SAOUITT-Regular.ttf").toString();

		closedButton = new Button();

		Text towerNameAndTier = new Text();
		String name;
		switch (((BaseTower) entity).getTowerType()) {
		case 0:
			name = "Elf Tower";
			break;
		case 1:
			name = "Witch Tower";
			break;
		case 2:
			name = "Robot Tower";
			break;
		default:
			name = "";
		}

		towerNameAndTier = new Text(name + " " + ((BaseTower) (entity)).getTier());
		Text towerDamage = new Text("Damage : " + ((BaseTower) (entity)).getDmg());
		Text towerRange = new Text("Range : " + ((BaseTower) (entity)).getRange());
		Text towerCooldown = new Text("Atk Cooldown : " + ((BaseTower) (entity)).getCooldown());

		towerNameAndTier.setFont(Font.loadFont(fontPath, 30));
		towerDamage.setFont(Font.loadFont(fontPath, 20));
		towerRange.setFont(Font.loadFont(fontPath, 20));
		towerCooldown.setFont(Font.loadFont(fontPath, 20));

		initialClosedButton();

		this.getChildren().add(towerNameAndTier);
		this.getChildren().add(towerDamage);
		this.getChildren().add(towerRange);
		this.getChildren().add(towerCooldown);
		this.getChildren().add(closedButton);

		GameScreen.backRoot.getChildren().add(this);

	}

	public void initialClosedButton() {
		String image_path = ClassLoader.getSystemResource("images/exitButton.png").toString();
		ImageView view = new ImageView(new Image(image_path));
		view.setFitHeight(SIZE_HEIGHT);
		view.setFitWidth(SIZE_WIDTH);

		closedButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
		closedButton.setGraphic(view);
		closedButton
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		closedButton.setCursor(Cursor.HAND);

		closedButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(ZOOM_SIZE_HEIGHT);
				view.setFitWidth(ZOOM_SIZE_WIDTH);
				closedButton.setPrefSize(ZOOM_SIZE_WIDTH, ZOOM_SIZE_HEIGHT);
				Bloom bloom = new Bloom();
				bloom.setThreshold(0.5);
				closedButton.setEffect(bloom);
			}
		});

		closedButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(SIZE_HEIGHT);
				view.setFitWidth(SIZE_WIDTH);
				closedButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
				closedButton.setEffect(null);
			}
		});

		closedButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				EditTowerPane.informationTower.setVisible(false);
			}
		});
	}
}
