package system;

import javafx.scene.Node;

public interface Workspace {

	/**
	 * The name of the workspace, to be displayed to the user.
	 * @return
	 */
	String name();

	/**
	 * Generates the Node that represents the workspace. Every time this method is called, a new Node should be generated, because the same workspace can appear on the screen at multiple locations
	 * @return
	 */
	Node make();
}
