package system;

import panels.Window;

import java.util.ArrayList;
import java.util.List;

public class WindowSystem {
	List<Workspace> workspaces = new ArrayList<>();

	public List<Workspace> getWorkspaces() {
		return workspaces;
	}

	public void open() {
		Window w = new Window(this);
		w.show();
	}
}
