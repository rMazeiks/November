package ui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import system.WorkspaceInstance;
import ui.tabs.TabButtons;

public class ControlBar extends BorderPane {
	public static final double BAR_HEIGHT = 20;
	private TabButtons tabs;
	private HBox right = new HBox();
	public ControlBar(ObservableList<WorkspaceInstance> tabs) {
		this.tabs = new TabButtons(tabs);

		setBackground(new Background(new BackgroundFill(Color.gray(0.4), new CornerRadii(0), new Insets(0))));

		this.tabs.setAlignment(Pos.CENTER_LEFT);
		right.setAlignment(Pos.CENTER_RIGHT);

		setRight(right);
		setCenter(this.tabs);

		setMinHeight(BAR_HEIGHT);
		setMaxHeight(BAR_HEIGHT);
		setPrefHeight(BAR_HEIGHT);
	}

	public TabButtons getTabs() {
		return tabs;
	}

	public ObservableList<Node> leftChildren() {
		return tabs.getChildren();
	}

	public ObservableList<Node> rightChildren() {
		return right.getChildren();
	}
}
