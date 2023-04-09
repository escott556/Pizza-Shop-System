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
 *                           2/19/2023
 *                           Added the two methods to communicate with the Employee Info Hub and Employee Info Access Info frames.
 *                           It should be noted that the names of the files used are only an assumption. They can be changed, but must match
 *                           in this controller, the Main class, SceneBuilder, and in each of the respective .fxml files.
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
	
	// Place Order Route
	
	public void SwitchToPlaceOrderFrame(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("PlaceOrderFrame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void SwitchToPaymentFrame(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("PaymentFrame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// Employee Management Route
	
	public void SwitchToEmployeeInfoHubFrame(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("EmployeeInfoHubFrame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void SwitchToEmployeeInfoAccessInfoFrame(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("EmployeeInfoAccessInfoFrame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}











