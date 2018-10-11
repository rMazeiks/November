package ui.tabs;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import system.WorkspaceInstance;
import ui.buttons.CloseButton;

public class Tab extends BorderPane {
	Label label;
	CloseButton close;

	public Tab(IntegerProperty selected, int index, WorkspaceInstance workspaceInstance, TabAction action) {
		setOnMouseClicked(event -> selected.set(index));

		label = new Label(workspaceInstance.getName());
		setCenter(label);

		close = new CloseButton();
		close.setOnAction(e->action.closed());
		setRight(close);

		setPadding(new Insets(0, 4, 0, 4));

		BooleanBinding amSelected = selected.isEqualTo(index);
		amSelected.addListener(
				(observable, oldValue, newValue) -> setSelected(newValue)
		);

		setSelected(amSelected.get());
	}

	void setSelected(boolean selected) {
		if (selected) {
			setBackground(new Background(new BackgroundFill(
					Color.WHITE,
					new CornerRadii(4, 4, 0, 0, false),
					Insets.EMPTY)
			));
			label.setTextFill(Color.BLACK);
			close.setBlack(true);
		} else {
			setBackground(Background.EMPTY);
			close.setBlack(false);
			label.setTextFill(Color.WHITE);
		}
	}
}
