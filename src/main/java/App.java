import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application{
	public static void main(String[]args) {
		launch();
	}

	public void start(Stage primaryStage) throws Exception {
		Pane pane = FXMLLoader.load(getClass().getResource("views/MainView.fxml"));
		primaryStage.setTitle("FastTyper");
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
	}
}
