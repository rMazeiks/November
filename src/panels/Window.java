package panels;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import system.Control;
import system.WindowSystem;
import system.WorkspaceInstance;

/**
 * At the top of the hierarchy. Creates a window within which other Panels can be created
 */
public class Window extends Panel {
	private double pressStartX, pressStartY;
	private StackPane content = new StackPane();
	private Stage stage = new Stage();
	private Control myControl;

	public Window(WindowSystem system) {
		super(system);

		setup();

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

	private void setup() {
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setFullScreen(true);

		content.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
//			System.out.println("Pressed");
			pressStartX = event.getSceneX();
			pressStartY = event.getSceneY();
		});

		content.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
//			System.out.println("Dragged");
			stage.setX(event.getScreenX() - pressStartX);
			stage.setY(event.getScreenY() - pressStartY);
		});
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
