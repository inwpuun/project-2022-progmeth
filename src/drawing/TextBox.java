package drawing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextBox extends HBox {

	public TextBox(ImageView image, Text text) {
		setAlignment(Pos.CENTER_LEFT);

		this.getChildren().add(image);
		setMargin(image, new Insets(0, 10, 0, 20));

		String fontPath = ClassLoader.getSystemResource("font/SAOUITT-Regular.ttf").toString();
		Font font = Font.loadFont(fontPath, 30);
		text.setFont(font);

		this.getChildren().add(text);

	}
}
