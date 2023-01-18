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

public class PausePane extends StackPane {

	private static PausePane instance = new PausePane();
	
	public static PausePane getInstance() {
		return instance;
	}

	private StackPane root;
	private VBox rootTop;
	
	private Button resumeButton; 
	private Button homeButton; 
	
	private final int ZOOM_SIZE_WIDTH = 340;
	private final int ZOOM_SIZE_HEIGHT = 140;
	
	private final int SIZE_WIDTH = 300;
	private final int SIZE_HEIGHT = 100;
	

	public PausePane() {
		root = new StackPane();
		rootTop = new VBox();
		resumeButton = new Button(); 
		homeButton = new Button(); 

		root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		root.setAlignment(Pos.CENTER);
		root.setPrefSize(GameScreen.GAMEWIDTH, GameScreen.GAMEHIGHT);
		root.setOpacity(0.6);

		rootTop.setAlignment(Pos.CENTER);

		initialResumeButton();
		initialHomeButton();
		
		rootTop.getChildren().add(resumeButton);
		rootTop.getChildren().add(homeButton);

		rootTop.setSpacing(40);

		this.getChildren().add(root);
		this.getChildren().add(rootTop);
		this.setVisible(false);
	}
	
	public void initialResumeButton() {

		String image_path = ClassLoader.getSystemResource("images/resumeButton.png").toString();
		ImageView view = new ImageView(new Image(image_path));
		view.setFitHeight(SIZE_HEIGHT);
		view.setFitWidth(SIZE_WIDTH);
		
		resumeButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
		resumeButton.setGraphic(view);
		resumeButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		resumeButton.setCursor(Cursor.HAND);
		
		resumeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(ZOOM_SIZE_HEIGHT);
			    view.setFitWidth(ZOOM_SIZE_WIDTH);
			    resumeButton.setPrefSize(ZOOM_SIZE_WIDTH, ZOOM_SIZE_HEIGHT);
			    Bloom bloom = new Bloom();
			    bloom.setThreshold(0.5);
			    resumeButton.setEffect(bloom);
			}
		});
		
		resumeButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				view.setFitHeight(SIZE_HEIGHT);
			    view.setFitWidth(SIZE_WIDTH);
			    resumeButton.setPrefSize(SIZE_WIDTH, SIZE_HEIGHT);
			    resumeButton.setEffect(null);
			}
		});
		
		resumeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				GameState.setGameState(GameState.PLAYING);
				PausePane.getInstance().setVisible(false);
				
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
				PausePane.getInstance().setVisible(false);
				
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
