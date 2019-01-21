package system;

import javafx.scene.Node;

public class WorkspaceInstance {
	private Node content;
	private String name;

	public WorkspaceInstance(Workspace workspace) {
		name = workspace.name();
		content = workspace.make();
	}

	public Node getContent() {
		return content;
	}

	public String getName() {
		return name;
	}
}
