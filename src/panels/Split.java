package panels;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import system.Control;
import system.WindowSystem;

public class Split extends Panel {

	SplitPane pane;
	private Panel left;
	private Panel right;
	Control leftControl = new Control() {
		@Override
		public void close() {
			getControl().replace(right);
		}

		@Override
		public void replace(Panel panel) {
			set0(panel);
		}
	};
	Control rightControl = new Control() {
		@Override
		public void close() {
			getControl().replace(left);
		}

		@Override
		public void replace(Panel panel) {
			set1(panel);
		}
	};

	public Split(Panel left, Panel right, WindowSystem system, Orientation orientation) {
		super(system);

		pane = new SplitPane(left, right);

		set0(left);
		set1(right);

		pane.setOrientation(orientation);

		setCenter(pane);
	}

	public void set0(Panel zero) { // top or left
		this.left = zero;
		pane.getItems().set(0, zero);
		zero.setControl(leftControl);
	}

	public void set1(Panel one) { // bottom or right
		this.right = one;
		pane.getItems().set(1, one);
		one.setControl(rightControl);
	}
}
