package panels;

import javafx.scene.layout.BorderPane;
import system.Control;
import system.WindowSystem;

/**
 * A <code>Panel</code> is a section of the graphical user interface that contains either other <code>Panels</code> or <code>WorkspaceInstance</code>s.
 *
 */
public abstract class Panel extends BorderPane {
	/**
	 * The <code>WindowSystem</code> to which this <code>Panel</code> belongs to. Any <code>WorkspaceInstance</code>s in this Panel will be created using <code>Workspace</code>s taken from this <code>WindowSystem</code>
	 */
	private WindowSystem system;
	/**
	 * This <code>Control</code> lets the <code>Panel</code> manipulate itself from its parent <code>Panel</code>'s perspective. It can be used to close the <code>Panel</code> (remove it from its parent) or to replace it with another <code>Panel</code>.
	 */
	private Control control;

	Panel(WindowSystem system) {
		this.system = system;
	}

	/**
	 * @return the <code>Control</code> that lets the <code>Panel</code> manipulate itself from its parent <code>Panel</code>'s perspective. It can be used to close the <code>Panel</code> (remove it from its parent) or to replace it with another <code>Panel</code>.
	 */
	protected Control getControl() {
		return control;
	}

	/**
	 * Sets the <code>Control</code> that this <code>Panel</code> uses to manipulate itself.
	 * @param control
	 */
	void setControl(Control control) {
		this.control = control;
	}

	WindowSystem getSystem() {
		return system;
	}
}
