package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class EmployeeController1 {
	ObservableList<String> employeeList = FXCollections.observableArrayList("John", "Mary", "Alise", "Tim");

	private Stage stage;
	private Parent root;
	private Scene scene;

	@FXML
	private ChoiceBox employeeInfoBox;
	
	public void switchtoFrame1(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("EmployeeFrame1.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchtoFrame2(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("EmployeeFrame2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void initialize() {
		employeeInfoBox.setValue(null);
		employeeInfoBox.setItems(employeeList);
	}

}
