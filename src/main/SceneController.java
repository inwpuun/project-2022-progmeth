package main;

import java.io.IOException;
import drawing.GameScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private GameScreen gameScreen;

	public void switchToMenuScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToGameScene(ActionEvent event) throws Exception {
		Main.animationTimer.stop();
		Main.sound.stop();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		gameScreen = new GameScreen();
		stage.setScene(gameScreen);
		stage.show();
	}

	public void switchToHelpScene1(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Help1.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToHelpScene2(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Help2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}