import javafx.scene.control.Label;
import system.WindowSystem;
import system.Workspace;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Example extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		WindowSystem ws = new WindowSystem();
		ws.getWorkspaces().add(
				new Workspace() {
					@Override
					public String name() {
						return "red";
					}

					@Override
					public Node make() {
						Label l = new Label("Hello!");
						l.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0))));
						return l;
					}
				}
		);

		ws.getWorkspaces().add(
				new Workspace() {
					@Override
					public String name() {
						return "green";
					}

					@Override
					public Node make() {
						Label l = new Label("Hello!");
						l.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(0), new Insets(0))));
						return l;
					}
				}
		);
		ws.getWorkspaces().add(
				new Workspace() {
					@Override
					public String name() {
						return "Orange";
					}

					@Override
					public Node make() {
						Label l = new Label("Hello!");
						l.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(0), new Insets(0))));
						l.setPadding(new Insets(30));
						return l;
					}
				}
		);
		ws.open();
	}
}