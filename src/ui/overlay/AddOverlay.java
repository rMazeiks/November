package ui.overlay;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import ui.SideChosenAction;

public class AddOverlay extends Pane {
	private SideChosenAction action;

	public AddOverlay(SideChosenAction action, double w, double h) {
		this.action = action;
		double w1 = w;
		double h1 = h;

		Point2D topLeft = new Point2D(0, 0),
				topRight = new Point2D(w, 0),
				bottomRight = new Point2D(w, h),
				bottomLeft = new Point2D(0, h);

		Group top = new OverlayButton("Top", Side.TOP, w, h, action);
		Group bottom = new OverlayButton("Bottom", Side.BOTTOM, w, h, action);
		Group right = new OverlayButton("Right", Side.RIGHT, w, h, action);
		Group left = new OverlayButton("Left", Side.LEFT, w, h, action);
		Group center = new OverlayButton("New Tab", Side.CENTER, w, h, action);

		getChildren().addAll(top, bottom, right, left, center);
	}

}
