package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		 
		Parent root = FXMLLoader.load(getClass().getResource("EmployeeInOut.fxml"));
		primaryStage.setScene(new Scene(root, 650, 400));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
