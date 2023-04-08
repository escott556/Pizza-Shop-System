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











/**
Trying not to delete anyone's work, but here is my controller class
NOTE : Need to have employee class, vehicle class and Database connection classes in order for this class to work without any errors

 * Author: Bhakti Patel
 * Topic: Semester Project: PizzaShop controller class
 * Date Created: 03/01/2023
 * Last Edited: 04/07/2023
 
*/
package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import java.awt.EventQueue;

public class Controller {

	private void ShowMessage(String message) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, message);
			}
		});
	}

//------------------------------------------------- Login Frame start --------------------------------------------------------
	@FXML
	private Button button;
	@FXML
	private Label wrongLogin;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

	@FXML
	public void userLogin(ActionEvent event) throws IOException {
		Main m = new Main();
		if (username.getText().toString().equals("admin") && password.getText().toString().equals("password")) {
			wrongLogin.setText("Logging in.");

			m.changeScene("ManagerHub.fxml");
		} else if (username.getText().isEmpty() && password.getText().isEmpty()) {
			wrongLogin.setText("Enter your username and password.");
		} else {
			wrongLogin.setText("Incorrect username or password.");
		}
	}
//------------------------------------------------- Login Frame end --------------------------------------------------------

//------------------------------------------------- Manager Hub Frame start --------------------------------------------------------
	@FXML
	private Button logout;

	@FXML
	public void userLogout(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("Login.fxml");
	}

	@FXML
	private Button done;

	@FXML
	public void manager_hub(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("ManagerHub.fxml");

	}

	@FXML
	private Button emp_manage;

	@FXML
	public void change_to_emp_manage(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("EmployeeManagement.fxml");

	}

	@FXML
	private Button vehicle_manage;

	@FXML
	public void change_to_vehicle_manage(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("VehicleManagement.fxml");

	}

//------------------------------------------------- Manager Hub Frame end --------------------------------------------------------

//------------------------------------------------- Employee management Frame start --------------------------------------------------------
	private Stage stage;
	private Parent root;
	private Scene scene;

	@FXML
	private TableColumn<Employee, Integer> col_ID;

	@FXML
	private TableColumn<Employee, String> col_add;

	@FXML
	private TableColumn<Employee, String> col_name;

	@FXML
	private TableColumn<Employee, String> col_phone;

	@FXML
	private TableView<Employee> table_emp;

	@FXML
	private TextField txt_add;

	@FXML
	private TextField txt_name;

	@FXML
	private TextField txt_phone;

	@FXML
	private TextField txt_ID;

	public void updateTable() {

		ObservableList<Employee> listE;
		listE = Employee.getEmployeeInfo();

		col_ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("empID"));
		col_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("empName"));
		col_add.setCellValueFactory(new PropertyValueFactory<Employee, String>("empAdd"));
		col_phone.setCellValueFactory(new PropertyValueFactory<Employee, String>("empPhone"));

		table_emp.setItems(listE);

	}

	public void add_emp() {

		Connection con = DatabaseConnection.getConnection();
		String sql = "insert into employee (emp_name,emp_add,emp_phone) values (?,?,?);";
		try {
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ps.setString(1, txt_name.getText());
			ps.setString(2, txt_add.getText());
			ps.setString(3, txt_phone.getText());
			ps.execute();

			ShowMessage("Employee Added!");
			updateTable();
		} catch (Exception e) {

		}
	}

	public void update() {
		try {
			Connection con = DatabaseConnection.getConnection();
			String value1 = txt_ID.getText();
			String value2 = txt_name.getText();
			String value3 = txt_phone.getText();
			String value4 = txt_add.getText();

			String sql = "update employee set emp_name = '" + value2 + "', emp_phone = '" + value3 + "', emp_add = '"
					+ value4 + "' where emp_ID = '" + value1 + "'";

			PreparedStatement ps;
			ps = con.prepareStatement(sql);

			if (!value2.isEmpty() && !value3.isEmpty() && !value4.isEmpty()) {
				ps.execute();
				ShowMessage("Employee Updated!");
				updateTable();
			} else {
				if (value2.isEmpty() || value3.isEmpty() || value4.isEmpty()) {
					if (value2.isEmpty()) {
						ShowMessage("Enter employee name!");
					} else if (value3.isEmpty()) {
						ShowMessage("Enter employee phone number!");
					} else if (value2.isEmpty()) {
						ShowMessage("Enter employee address!");
					} else {
						ShowMessage("Error!");
					}
				}
			}
			updateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		Connection con = DatabaseConnection.getConnection();
		String sql = "delete from employee where emp_ID = ?";
		try {
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ps.setString(1, txt_ID.getText());
			ps.execute();

			ShowMessage("Employee Deleted!");
			updateTable();
		} catch (Exception e) {

		}
	}

	@FXML
	void getSelected(MouseEvent event) {

		Employee clickedEmp = table_emp.getSelectionModel().getSelectedItem();
		txt_name.setText(String.valueOf(clickedEmp.getEmpName()));
		txt_ID.setText(String.valueOf(clickedEmp.getEmpID()));
		txt_add.setText(String.valueOf(clickedEmp.getEmpAdd()));
		txt_phone.setText(String.valueOf(clickedEmp.getEmpPhone()));
	}


//------------------------------------------------- Employee management Frame end --------------------------------------------------------

//------------------------------------------------- Vehicle management Frame start --------------------------------------------------------

	@FXML
	private TableColumn<Vehicle, Integer> col_V_ID;

	@FXML
	private TableColumn<Vehicle, String> col_type;

	@FXML
	private TableColumn<Vehicle, Integer> col_emp_ID;

	@FXML
	private TableView<Vehicle> table_vehicle;

	@FXML
	private TextField txt_V_ID;

	@FXML
	private TextField txt_emp_ID;

	@FXML
	private TextField txt_VType;

	ObservableList<Vehicle> listV;

	public void updateTable1() {

		listV = Vehicle.getVehicleInfo();

		col_V_ID.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("vehicleID"));
		col_type.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleType"));
		col_emp_ID.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("empID"));

		table_vehicle.setItems(listV);

	}

	public void add_vehicle() {

		try {

			Connection con = DatabaseConnection.getConnection();

			String sql = "insert into vehicle (emp_ID,vehicle_type) values (?,?);";
			String sql2 = "select * from employee where emp_ID = ?";

			PreparedStatement ps;

			ps = con.prepareStatement(sql);
			PreparedStatement ps2 = con.prepareStatement(sql2);

			ps.setString(1, txt_emp_ID.getText());
			ps.setString(2, txt_VType.getText());

			if (txt_emp_ID.getText().isEmpty() || txt_VType.getText().isEmpty()) {

				if (txt_emp_ID.getText().isEmpty()) {
					ShowMessage("Enter an employee ID!");
				} else if (txt_VType.getText().isEmpty()) {
					ShowMessage("Enter vehicle type!");
				} else {
					ShowMessage("Enter employee ID and vehicle type!");
				}
			}

			ps2.setString(1, txt_emp_ID.getText());
			ResultSet rs = ps2.executeQuery();

			if (!txt_emp_ID.getText().isEmpty() && !txt_VType.getText().isEmpty()) {
				if (!(rs.next()) && !(txt_emp_ID.getText().isEmpty())) {
					ShowMessage("Employee ID does not exists!");
				}
				ps.execute();
				ShowMessage("Vehicle Added!");
			}
			// ps.execute();
			updateTable1();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateVehicle() {
		try {

			Connection con = DatabaseConnection.getConnection();

			String value1 = txt_V_ID.getText();
			String value2 = txt_emp_ID.getText();
			String value3 = txt_VType.getText();

			String sql = "update vehicle set emp_ID = '" + value2 + "', vehicle_type = '" + value3
					+ "' where vehicle_ID = '" + value1 + "'";
			String sql2 = "select * from employee where emp_ID = ?";

			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			PreparedStatement ps2 = con.prepareStatement(sql2);

			ps2.setString(1, value2);
			ResultSet rs = ps2.executeQuery();

			if (value2.isEmpty() || value3.isEmpty()) {

				if (value2.isEmpty()) {
					ShowMessage("Enter an employee ID!");

				} else if (value3.isEmpty()) {
					ShowMessage("Enter vehicle type!");
				} else {
					ShowMessage("Enter employee ID and vehicle type!");
				}
			}
			if (!(rs.next()) && !(value2.isEmpty())) {
				ShowMessage("Employee ID does not exists!");

			}
			if (!value2.isEmpty() && !value3.isEmpty()) {
				ShowMessage("Vehicle updated!");
			}
			ps.execute();
			updateTable1();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void deleteVehicle() {
		Connection con = DatabaseConnection.getConnection();
		String sql = "delete from vehicle where vehicle_ID = ?";
		try {
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ps.setString(1, txt_V_ID.getText());

			ps.execute();

			ShowMessage("Vehicle Deleted!");

			updateTable1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void getSelectedV(MouseEvent event) {

		Vehicle clickedV = table_vehicle.getSelectionModel().getSelectedItem();
		txt_V_ID.setText(String.valueOf(clickedV.getVehicleID()));
		txt_emp_ID.setText(String.valueOf(clickedV.getEmpID()));
		txt_VType.setText(String.valueOf(clickedV.getVehicleType()));
	}


//------------------------------------------------- Vehicle management Frame end --------------------------------------------------------
}

