package main;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import tower.BaseTower;

public class EditTowerPane extends StackPane {

	private static EditTowerPane instance = new EditTowerPane();

	public static EditTowerPane getInstance() {
		return instance;
	}
	
	private HBox rootTop;
	public static InformationTower informationTower;
	
	private Button upgradeButton;
	private Button sellButton;
	private Button informationButton;

	private final int ZOOM_SIZE_WIDTH = 60;
	private final int ZOOM_SIZE_HEIGHT = 60;

	private final int SIZE_WIDTH = 50;
	private final int SIZE_HEIGHT = 50;

	private IRenderable entity;
	
	

	public EditTowerPane() {
		this.setPrefSize(200, 80);
		this.setMaxSize(200, 80);

		rootTop = new HBox();
		
		upgradeButton = new Button();
		sellButton = new Button();
		informationButton = new Button();

		rootTop.setAlignment(Pos.CENTER);
		rootTop.setPrefSize(200, 80);

		initialUpgradeButton();
		initialSellButton();
		initialInformationButton();

		rootTop.getChildren().add(upgradeButton);
		rootTop.getChildren().add(sellButton);
		rootTop.getChildren().add(informationButton);

		rootTop.setSpacing(5);

		this.getChildren().add(rootTop);
		this.setVisible(false);

	}

	public void setXYAndMovePane(int x, int y, IRenderable entity) {
		this.entity = entity;
		if (x > 440)
			x = 440;
		if (y < 40)
			y = 40;
		this.setTranslateX(x - 320);
		this.setTranslateY(y - 320);
	}

	public void initialUpgradeButton() {

		String image_path = ClassLoader.getSystemResource("images/upgradeButton.png").toString();
		ImageView view = new ImageView(new Image(image_path));
		view.setFitHeight(SIZE_HEIGHT);
		view.setFitWidth(SIZE_WIDTH);

		upgradeButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
		upgradeButton.setGraphic(view);
		upgradeButton
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		upgradeButton.setCursor(Cursor.HAND);

		upgradeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(ZOOM_SIZE_HEIGHT);
				view.setFitWidth(ZOOM_SIZE_WIDTH);
				upgradeButton.setPrefSize(ZOOM_SIZE_WIDTH, ZOOM_SIZE_HEIGHT);
				Bloom bloom = new Bloom();
				bloom.setThreshold(0.5);
				upgradeButton.setEffect(bloom);
			}
		});

		upgradeButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(SIZE_HEIGHT);
				view.setFitWidth(SIZE_WIDTH);
				upgradeButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
				upgradeButton.setEffect(null);
			}
		});

		upgradeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
					if (entity instanceof BaseTower) {
						if (entity.equals(getEntity())) {
							((BaseTower) entity).upgradeTower();
						}
					}
				}
				EditTowerPane.getInstance().setVisible(false);

			}
		});
	}

	public IRenderable getEntity() {
		return entity;
	}

	public void initialSellButton() {
		String image_path = ClassLoader.getSystemResource("images/sellButton.png").toString();
		ImageView view = new ImageView(new Image(image_path));
		view.setFitHeight(SIZE_HEIGHT);
		view.setFitWidth(SIZE_WIDTH);

		sellButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
		sellButton.setGraphic(view);
		sellButton
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		sellButton.setCursor(Cursor.HAND);

		sellButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(ZOOM_SIZE_HEIGHT);
				view.setFitWidth(ZOOM_SIZE_WIDTH);
				sellButton.setPrefSize(ZOOM_SIZE_WIDTH, ZOOM_SIZE_HEIGHT);
				Bloom bloom = new Bloom();
				bloom.setThreshold(0.5);
				sellButton.setEffect(bloom);
			}
		});

		sellButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(SIZE_HEIGHT);
				view.setFitWidth(SIZE_WIDTH);
				sellButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
				sellButton.setEffect(null);
			}
		});

		sellButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				GameLogic.getInstance().getTowerManager().sellTower(entity);
				EditTowerPane.getInstance().setVisible(false);
			}
		});
	}
	
	public void initialInformationButton() {
		String image_path = ClassLoader.getSystemResource("images/informationButton.png").toString();
		ImageView view = new ImageView(new Image(image_path));
		view.setFitHeight(SIZE_HEIGHT);
		view.setFitWidth(SIZE_WIDTH);

		informationButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
		informationButton.setGraphic(view);
		informationButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		informationButton.setCursor(Cursor.HAND);

		informationButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(ZOOM_SIZE_HEIGHT);
				view.setFitWidth(ZOOM_SIZE_WIDTH);
				informationButton.setPrefSize(ZOOM_SIZE_WIDTH, ZOOM_SIZE_HEIGHT);
				Bloom bloom = new Bloom();
				bloom.setThreshold(0.5);
				informationButton.setEffect(bloom);
			}
		});

		informationButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(SIZE_HEIGHT);
				view.setFitWidth(SIZE_WIDTH);
				informationButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
				informationButton.setEffect(null);
			}
		});

		informationButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				informationTower = new InformationTower(entity);
				
				EditTowerPane.getInstance().setVisible(false);
			}
		});
	}

}
