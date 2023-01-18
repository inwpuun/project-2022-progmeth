package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Main extends Application {

	public static AudioClip sound = new AudioClip(ClassLoader.getSystemResource("audio/homeSweetHome.mp3").toString());
	public static AnimationTimer animationTimer;
	
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Tower Defense");
			String image_path = ClassLoader.getSystemResource("images/cat.png").toString();
			stage.getIcons().add(new Image(image_path));
			stage.show();
			sound.play();
			animationTimer = new AnimationTimer() {
				
				@Override
				public void handle(long arg0) {
					// TODO Auto-generated method stub
					if (!sound.isPlaying()) sound.play();
				}
			};
			animationTimer.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}