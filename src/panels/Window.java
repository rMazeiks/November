package panels;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import system.Control;
import system.WindowSystem;
import system.WorkspaceInstance;

public class Window extends Panel {
	StackPane content = new StackPane();
	Stage stage = new Stage();

	Control myControl;

	public Window(WindowSystem system) {
		super(system);

		stage.initStyle(StageStyle.UNDECORATED);
		stage.setFullScreen(true);

		Scene s = new Scene(content);

		myControl = new Control() {
			@Override
			public void close() {
				stage.close();
			}

			@Override
			public void replace(Panel panel) {
				take(panel);
			}
		};
		take(new TabPanel(new WorkspaceInstance(system.getWorkspaces().get(0)), system));

		stage.setScene(s);
	}

	private void take(Panel panel) {
		content.getChildren().removeIf(all -> true);
		content.getChildren().add(panel);
		panel.setControl(myControl);
	}

	public void show() {
		stage.show();
	}
}
