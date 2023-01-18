package main;

import java.io.IOException;
import drawing.GameScreen;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.GameLogic;

public class EndGameScene extends StackPane {

	private static EndGameScene instance;
	
	public static EndGameScene getInstance() {
		if (instance == null) instance = new EndGameScene();
		return instance;
	}

	private StackPane root;
	private VBox rootTop;
	
	private Button restartButton; 
	private Button homeButton; 
	
	private final int ZOOM_SIZE_WIDTH = 340;
	private final int ZOOM_SIZE_HEIGHT = 140;
	
	private final int SIZE_WIDTH = 300;
	private final int SIZE_HEIGHT = 100;
	

	public EndGameScene() {
		root = new StackPane();
		rootTop = new VBox();
		restartButton = new Button(); 
		homeButton = new Button(); 

		root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		root.setAlignment(Pos.CENTER);
		root.setPrefSize(GameScreen.GAMEWIDTH, GameScreen.GAMEHIGHT);
		root.setOpacity(0.6);

		rootTop.setAlignment(Pos.CENTER);

		initialRestartButton();
		initialHomeButton();
		
		rootTop.getChildren().add(restartButton);
		rootTop.getChildren().add(homeButton);

		rootTop.setSpacing(40);

		this.getChildren().add(root);
		this.getChildren().add(rootTop);
		this.setVisible(false);
	}
	
	public void initialRestartButton() {

		String image_path = ClassLoader.getSystemResource("images/restartButton.png").toString();
		ImageView view = new ImageView(new Image(image_path));
		view.setFitHeight(SIZE_HEIGHT);
		view.setFitWidth(SIZE_WIDTH);
		
		restartButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
		restartButton.setGraphic(view);
		restartButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		restartButton.setCursor(Cursor.HAND);
		
		restartButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(ZOOM_SIZE_HEIGHT);
			    view.setFitWidth(ZOOM_SIZE_WIDTH);
			    restartButton.setPrefSize(ZOOM_SIZE_WIDTH, ZOOM_SIZE_HEIGHT);
			    Bloom bloom = new Bloom();
			    bloom.setThreshold(0.5);
			    restartButton.setEffect(bloom);
			}
		});
		
		restartButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(SIZE_HEIGHT);
			    view.setFitWidth(SIZE_WIDTH);
			    restartButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
			    restartButton.setEffect(null);
			}
		});
		
		restartButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				GameLogic.reset();
				GameState.setGameState(GameState.PLAYING);
				EndGameScene.getInstance().setVisible(false);
				
				GameScreen.animation.stop();
				GameScreen.animation = null;
				
				Stage stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
				
				GameScreen gameScreen = new GameScreen();
				stage.setScene(gameScreen);
				stage.show();
				
			}
		});
	}

	public void initialHomeButton() {
		String image_path = ClassLoader.getSystemResource("images/homeButton2.png").toString();
		ImageView view = new ImageView(new Image(image_path));
		view.setFitHeight(SIZE_HEIGHT);
		view.setFitWidth(SIZE_WIDTH);

		homeButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
		homeButton.setGraphic(view);
		homeButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		homeButton.setCursor(Cursor.HAND);
		
		homeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(ZOOM_SIZE_HEIGHT);
			    view.setFitWidth(ZOOM_SIZE_WIDTH);
			    homeButton.setPrefSize(ZOOM_SIZE_WIDTH, ZOOM_SIZE_HEIGHT);
			    Bloom bloom = new Bloom();
			    bloom.setThreshold(0.5);
			    homeButton.setEffect(bloom);
			}
		});
		
		homeButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(SIZE_HEIGHT);
			    view.setFitWidth(SIZE_WIDTH);
			    homeButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
			    homeButton.setEffect(null);
			}
		});
		
		homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				GameLogic.reset();
				GameState.setGameState(GameState.PLAYING);
				EndGameScene.getInstance().setVisible(false);
				
				GameScreen.animation.stop();
				GameScreen.animation = null;
				
				GameScreen.sound.stop();
				Main.sound.play();
				Main.animationTimer.start();
				
				try {
					Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
					Stage stage = (Stage) ((Node) arg0.getSource()).getScene().getWindow();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
}
