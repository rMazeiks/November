package panels;

import javafx.scene.layout.BorderPane;
import system.Control;
import system.WindowSystem;

public abstract class Panel extends BorderPane {
	WindowSystem system;
	private Control control;

	public Panel(WindowSystem system) {
		this.system = system;

	}

	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public WindowSystem getSystem() {
		return system;
	}
}
