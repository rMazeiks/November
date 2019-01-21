package panels;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import system.Control;
import system.WindowSystem;
import system.Workspace;
import system.WorkspaceInstance;
import ui.ControlBar;
import ui.buttons.AddButton;
import ui.buttons.CloseButton;
import ui.overlay.AddOverlay;

import java.util.ArrayList;

/**
 * A <code>Panel</code> that contains one or more <code>WorkspaceInstance</code>s. The user can view any of these, one at a time, by clicking the appropriate tab in a bar of tabs representing all the <code>WorkspaceInstance</code>s this <code>Panel</code> contains.
 */
class TabPanel extends Panel {

	/**
	 * A <code>StackPane</code> that contains the contentArea. It may also contain an <code>AddOverlay</code>.
	 */
	private StackPane contentArea = new StackPane();
	/**
	 * A <code>StackPane</code> that contains only the currently open tab
	 */
	private StackPane openTab = new StackPane();
	private ObservableList<WorkspaceInstance> tabs = FXCollections.observableList(new ArrayList<>());
	private ControlBar bar = new ControlBar(tabs);
	private WorkspaceInstance toBeAdded;

	private ChangeListener resizeListener_redrawOverlay = new ChangeListener() {
		@Override
		public void changed(ObservableValue observable, Object oldValue, Object newValue) {
			enterAddMode(toBeAdded);
		}
	};

	public TabPanel(WorkspaceInstance w, WindowSystem system) {
		super(system);
		setupBar();

		contentArea.getChildren().add(openTab);

		tabs.addListener((InvalidationListener) e -> updateTab());

		tabs.add(w);

		bar.getTabs().selectedProperty().addListener(e -> updateTab());

		setCenter(contentArea);
	}

	private void updateTab() {
		IntegerProperty selected = bar.getTabs().selectedProperty();

		if (tabs.size() == 0) {
			getControl().close();
			return;
		}

		if (selected.get() >= tabs.size()) selected.set(tabs.size() - 1);

		showTab(tabs.get(selected.get()).getContent());
	}

	private void setupBar() {
		{
			AddButton a = new AddButton();
			a.setOnAction(e -> {
				ContextMenu menu = new ContextMenu();
				for (Workspace workspace : getSystem().getWorkspaces()) {
					MenuItem item = new MenuItem(workspace.name());
					item.setOnAction(event -> enterAddMode(new WorkspaceInstance(workspace)));
					menu.getItems().add(item);
				}

				menu.show(a, Side.BOTTOM, 0, 0);
			});
			bar.rightChildren().add(a);
		}

		{
			CloseButton b = new CloseButton();
			b.setOnAction(e -> getControl().close());
			bar.rightChildren().add(b);
		}
		setTop(bar);

	}

	private void showTab(Node element) {
		openTab.getChildren().removeIf(everything -> true);
		openTab.getChildren().add(element);
	}

	private void enterAddMode(WorkspaceInstance w) {
		exitAddMode();
		toBeAdded = w;

		AddOverlay overlay = new AddOverlay(side -> {
			if (side == ui.overlay.Side.CENTER) {
				tabs.add(w);
			} else {
				Control oldControl = getControl();
				Panel other = new TabPanel(w, getSystem());
				Panel p;
				switch (side) {
					case TOP:
						p = new Split(other, this, getSystem(), Orientation.VERTICAL);

						break;
					case BOTTOM:
						p = new Split(this, other, getSystem(), Orientation.VERTICAL);

						break;
					case LEFT:
						p = new Split(other, this, getSystem(), Orientation.HORIZONTAL);

						break;
					case RIGHT:
					default:
						p = new Split(this, other, getSystem(), Orientation.HORIZONTAL);

						break;
				}
				oldControl.replace(p);
			}

			exitAddMode();
		}, openTab.getWidth(), openTab.getHeight());
		contentArea.getChildren().add(overlay);

		widthProperty().addListener(resizeListener_redrawOverlay);
		heightProperty().addListener(resizeListener_redrawOverlay);
	}

	private void exitAddMode() {
		contentArea.getChildren().removeIf(node -> node != openTab);
		widthProperty().removeListener(resizeListener_redrawOverlay);
		heightProperty().removeListener(resizeListener_redrawOverlay);
	}
}
