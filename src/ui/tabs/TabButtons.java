package ui.tabs;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import system.WorkspaceInstance;

public class TabButtons extends HBox {
	private IntegerProperty selected = new SimpleIntegerProperty(0);

	public TabButtons(ObservableList<WorkspaceInstance> tabs) {
		tabs.addListener((ListChangeListener<WorkspaceInstance>) c -> {

			getChildren().removeIf(everything -> true);

			ObservableList<? extends WorkspaceInstance> workspaces = c.getList();
			for (int i = 0; i < workspaces.size(); i++) {
				int index = i;

				Tab t = new Tab(selected, index, workspaces.get(index), () -> {
					if (index < selected.get()) selected.set(selected.get() - 1);
					tabs.remove(index);
				});

				getChildren().add(t);
			}
		});
	}

	public int getSelected() {
		return selected.get();
	}

	public void setSelected(int selected) {
		this.selected.set(selected);
	}

	public IntegerProperty selectedProperty() {
		return selected;
	}
}
