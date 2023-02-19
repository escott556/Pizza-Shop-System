/**
 * Author(s):                  The Pizza Shop Team
 * Last Editted:             2/18/2023
 * 
 * Purpose:                  This is the main controller class for our system. It will control and handle the events
 *                           instantiated between the various frames of the system. [SUBJECT TO CHANGE]
 *                           
 * ***NOTE***:               2/18/2023
 *                           This is not properly implemented just yet. The Login Frame events are not included and
 *                           MUST be added beforehand for the system to work to its fullest extent.
 *                           
 *                           2/.../2023
 */

package application;

import java.io.IOException;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class PizzaShopSystemController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void SwitchToManagerHubFrame(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("ManagerHubFrame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void SwitchToPlaceOrderFrame(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("PlaceOrderFrame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void SwitchToEmployeeInfoFrame(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("EmployeeInfoFrame.fxml"));  // Bhakti, this name is just a suggestion, feel free to change it
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
