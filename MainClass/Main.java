package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	// stage for use in the change scene method
	private static Stage stg;

	@Override
	public void start(Stage primaryStage) {
		try {

			stg = primaryStage;

			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			primaryStage.setTitle("Pizza Shop Accounting System");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for changing scene
	 * 
	 * @param fxml
	 * @throws IOException
	 */
	public void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);
	
	}

	public static void main(String[] args) throws Exception{
		launch(args);
		Test_Calculations.testCalculateCost();
		Test_Calculations.testCalculateSubTotal();
		Test_Calculations.testCalculateTotal();
	}
}
