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


public class OverlayButton extends Group {
	Polygon polygon = new Polygon();
	private double w;
	private double h;

	public OverlayButton(String name, Side side, double w, double h, SideChosenAction action) {
		this.w = w;
		this.h = h;

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
		Scale scal = new Scale(w, h);

		// applied in reverse order for some reason.
		polygon.getTransforms().addAll(scal, rot);

		Label label = new Label(name);
		label.setAlignment(Pos.CENTER); // todo How can I make this centered??
		label.setTextAlignment(TextAlignment.CENTER);
		StackPane labelPane = new StackPane(label);
		Point2D point = new Point2D(0.5, side == Side.CENTER ? 0.5 : 0.15);
		point = scal.transform(rot.transform(point));
		labelPane.setTranslateX(point.getX());
		labelPane.setTranslateY(point.getY());
		labelPane.setAlignment(Pos.CENTER);


		getChildren().addAll(polygon, labelPane);


		polygon.setOnMouseClicked(e -> action.chosen(side));

		setOnMouseEntered(e -> fill(0.8));
		setOnMouseExited(e -> fill(0.9));
		fill(0.9);
		setOnMousePressed(e -> fill(0.7));
	}

	private double scaleX(double x) {
		x -= w / 2;
		x /= 2;
		return x + w / 2;
	}

	private double scaleY(double y) {
		y -= h / 2;
		y /= 2;
		return y + h / 2;
	}


	private void fill(double f) {
		polygon.setFill(Color.gray(f));
	}
}
