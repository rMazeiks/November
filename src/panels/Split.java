package panels;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import system.Control;
import system.WindowSystem;

/**
 * A <code>Panel</code> that contains exactly two other <code>Panel</code>s, either side by side or on top of each other
 */
class Split extends Panel {

	private SplitPane pane;
	private Panel panel0;
	private Panel panel1;

	private Control control0 = new Control() {
		@Override
		public void close() {
			getControl().replace(panel1);
		}

		@Override
		public void replace(Panel panel) {
			set0(panel);
		}
	};

	private Control control1 = new Control() {
		@Override
		public void close() {
			getControl().replace(panel0);
		}

		@Override
		public void replace(Panel panel) {
			set1(panel);
		}
	};

	/**
	 * Creates a <code>Split</code> panel, with the specified contents organized in the specified <code>Orientation</code>, and a reference to the specified <code>WindowSystem</code>
	 *
	 * @param panel0      the left or top <code>Panel</code>
	 * @param panel1      the right or bottom <code>Panel</code>
	 * @param system      the <code>WindowSystem</code> this panel is a part of
	 * @param orientation the orientation of the panels
	 */
	Split(Panel panel0, Panel panel1, WindowSystem system, Orientation orientation) {
		super(system);

		pane = new SplitPane(panel0, panel1);

		pane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			System.out.println("mouse pressed on panel");

		});

		pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
			System.out.println("mouse dragged on panel");
		});

		set0(panel0);
		set1(panel1);

		pane.setOrientation(orientation);

		setCenter(pane);
	}

	/**
	 * Sets the top/left <code>Panel</code> to the one specified, and provides it with its parent's (this <code>Panel</code>'s) <code>Control</code>.
	 *
	 * @param zero the panel to set
	 */
	private void set0(Panel zero) {
		this.panel0 = zero;
		pane.getItems().set(0, zero);
		zero.setControl(control0);
	}

	/**
	 * Sets the bottom/right <code>Panel</code> to the one specified, and provides it with its parent's (this <code>Panel</code>'s) <code>Control</code>.
	 *
	 * @param one the panel to set
	 */
	private void set1(Panel one) {
		this.panel1 = one;
		pane.getItems().set(1, one);
		one.setControl(control1);
	}
}
