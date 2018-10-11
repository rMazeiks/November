package ui.buttons;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import ui.ControlBar;

public class IconButton extends Button {
	ImageView visual;
	Image white, black;

	private static final double OPACITY = 0.5;
	private static final double OPACITY_HOVER = 0.9;


	public IconButton(String resource, String resourceBlack) {
		white = new Image(getClass().getResourceAsStream(resource));
		black = new Image(getClass().getResourceAsStream(resourceBlack));

		visual = new ImageView(white);
		visual.setFitHeight(ControlBar.BAR_HEIGHT);
		visual.setPreserveRatio(true);

		setGraphic(visual);
		setBackground(Background.EMPTY);
		this.setPadding(Insets.EMPTY);
		this.setAlignment(Pos.CENTER);

		setOpacity(OPACITY);
		setOnMouseEntered(e -> {
			setOpacity(OPACITY_HOVER);
		});
		setOnMouseExited(e->{
			setOpacity(OPACITY);
		});
	}

	public void setBlack(boolean b) {
		if (b) {
			visual.setImage(black);
		} else {
			visual.setImage(white);
		}
	}
}
