package ui.overlay;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import ui.SideChosenAction;


class OverlayButton extends Group {
	private Polygon polygon = new Polygon();
	Label label;

	public OverlayButton(String name, Side side, double w, double h, SideChosenAction action) {

		if (side == Side.CENTER) {
			polygon.getPoints().addAll(
					0.3, 0.3,
					0.7, 0.3,
					0.7, 0.7,
					0.3, 0.7
			);
		} else {
			polygon.getPoints().addAll(
					0.0, 0.0,
					1.0, 0.0,
					0.7, 0.3,
					0.3, 0.3
			);
		}


		Rotate rot = new Rotate(side.angle(), 0.5, 0.5);
		Scale scale = new Scale(w, h);

		// applied in reverse order for some reason.
		polygon.getTransforms().addAll(scale, rot);

		label = new Label(name);
		label.setAlignment(Pos.CENTER); // todo How can I make this centered??
		label.setTextAlignment(TextAlignment.CENTER);
		StackPane labelPane = new StackPane(label);
		Point2D point = new Point2D(0.5, side == Side.CENTER ? 0.5 : 0.15);
		point = scale.transform(rot.transform(point));
		labelPane.setTranslateX(point.getX());
		labelPane.setTranslateY(point.getY());
		labelPane.setAlignment(Pos.CENTER);


		getChildren().addAll(polygon, labelPane);


		polygon.setOnMouseClicked(e -> action.chosen(side));

		setOnMouseEntered(e -> fill(1, 0.9));
		setOnMouseExited(e -> fill(0.2, 0.8));
		fill(0.2, 0.8);
		setOnMousePressed(e -> fill(1, 1));
	}


	private void fill(double gray, double opacity) {
		polygon.setFill(Color.gray(gray, opacity));
		if(gray>0.5)  {
			label.setTextFill(Color.BLACK);
		} else  {
			label.setTextFill(Color.WHITE);
		}
	}
}
