package drawing;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ControlGridPane extends GridPane {

	public ControlGridPane() {
		this.setBackground(new Background(new BackgroundFill(Color.web("f4cfa8"), CornerRadii.EMPTY, Insets.EMPTY)));
		super.add(ControlPane.getInstance(), 0, 1);

	}
}
