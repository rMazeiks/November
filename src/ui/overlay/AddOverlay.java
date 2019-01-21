package ui.overlay;

import javafx.scene.Group;
import javafx.scene.layout.*;
import ui.SideChosenAction;

public class AddOverlay extends Pane {

	public AddOverlay(SideChosenAction action, double w, double h) {
		
		Group top = new OverlayButton("Top", Side.TOP, w, h, action);
		Group bottom = new OverlayButton("Bottom", Side.BOTTOM, w, h, action);
		Group right = new OverlayButton("Right", Side.RIGHT, w, h, action);
		Group left = new OverlayButton("Left", Side.LEFT, w, h, action);
		Group center = new OverlayButton("New Tab", Side.CENTER, w, h, action);

		getChildren().addAll(top, bottom, right, left, center);
	}

}
