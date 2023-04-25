///**
// * Author(s):                The Pizza Shop Team
// * Last Editted:             4/22/2023
// * 
// * Purpose:                  This is the main controller class for our system. It will control and handle the events
// *                           instantiated between the various frames of the system. [SUBJECT TO CHANGE]
// *                     
// */


package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import java.awt.EventQueue;

public class Controller implements Initializable {
	/**
	 * method for showing message (dialogue box)
	 * 
	 * @param message
	 */
	private void ShowMessage(String message) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, message);
			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// updating the vehicle and employee information tables
		updateTable();
		updateTable1();

		// Initializing the sales reports from the database
		initializeReports();
		
		// List all of the items for each choice box
		pizzaList();
		

	}

//--------------------------------------------------------- Login Frame start ------------------------------------------------------------------------
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
		// checking to see if have the right credentials for the manager and log in as
		// manager
		if (username.getText().toString().equals("admin") && password.getText().toString().equals("password")) {
			wrongLogin.setText("Logging in!");
			m.changeScene("ManagerHub.fxml");
		} else if (username.getText().toString().equals("employee") // checking to see if have the right credentials for
																	// the employee

				&& password.getText().toString().equals("password")) {
			wrongLogin.setText("Logging in!");
			m.changeScene("EmployeeHub.fxml");
		} else if (username.getText().isEmpty() && password.getText().isEmpty()) { // if empty fields, shows error
																					// messages
			ShowMessage("Enter your username and password!");
		} else if (username.getText().isEmpty()) {
			ShowMessage("Enter your username!");
		} else if (password.getText().isEmpty()) {
			ShowMessage("Enter your password!");
		} else {
			ShowMessage("Incorrect username and/or password!");
		}
	}

//--------------------------------------------------------- Login Frame end ------------------------------------------------------------------------

//--------------------------------------------------------- ManagerHub Frame start ------------------------------------------------------------------------

	@FXML
	private Button logout;

	/**
	 * change to login frame
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void userLogout(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("Login.fxml");
	}

	@FXML
	private Button done;

	/**
	 * change to manager hub frame
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void manager_hub(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("ManagerHub.fxml");

	}

	@FXML
	private Button placeOrder;

	/**
	 * change to placing order frame
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void change_to_placeOrder(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("PlaceOrder.fxml");
	}

	@FXML
	private Button emp_manage;

	/**
	 * change to employee info frame
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void change_to_emp_manage(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("EmployeeManagement.fxml");

	}

	@FXML
	private Button vehicle_manage;

	/**
	 * change to vehicle info frame
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void change_to_vehicle_manage(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("VehicleManagement.fxml");

	}

	/**
	 * change to generate reports frame
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void change_to_generateReports(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("GenerateReports.fxml");
	}

	/**
	 * change to graph sales frame
	 * 
	 * @param event
	 * @throws IOException
	 */

	@FXML
	public void change_to_graphSales(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("GraphSales.fxml");
	}
//--------------------------------------------------------- ManagerHub Frame end ------------------------------------------------------------------------

//--------------------------------------------------------- EmployeeHub Frame start ------------------------------------------------------------------------
	/**
	 * change to employee hub frame
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void employee_hub(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("EmployeeHub.fxml");

	}

	/**
	 * change to placing order frame
	 * 
	 * @param event
	 * @throws IOException
	 */

	@FXML
	public void change_to_placeOrderE(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("PlaceOrder_E.fxml");
	}

//--------------------------------------------------------- EmployeeHub Frame end ------------------------------------------------------------------------

//--------------------------------------------------------- EmployeeManagement Frame start ------------------------------------------------------------------------

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

	/**
	 * Updating the employee info table
	 */
	@FXML
	public void updateTable() {
		try {
			// Setting the respective columns with the class attributes for showing them
			// into the table view.
			col_ID.setCellValueFactory(new PropertyValueFactory<>("empID"));
			col_name.setCellValueFactory(new PropertyValueFactory<>("empName"));
			col_add.setCellValueFactory(new PropertyValueFactory<>("empAdd"));
			col_phone.setCellValueFactory(new PropertyValueFactory<>("empPhone"));

			table_emp.setItems(Employee.getEmployeeInfo());

		} catch (Exception e) {

		}

	}

	/**
	 * Updating employee info
	 */
	@FXML
	public void update_emp() {
		try {
			// Connecting with the database
			Connection con = DatabaseConnection.getConnection();

			String value1 = txt_ID.getText();
			String value2 = txt_name.getText();
			String value3 = txt_phone.getText();
			String value4 = txt_add.getText();
			// Doing SQL query to update into the database
			String sql = "update employee set emp_name = '" + value2 + "', emp_phone = '" + value3 + "', emp_add = '"
					+ value4 + "' where emp_ID = '" + value1 + "";

			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			// Information will not be updated if any one of the below if true
			if (value2.isEmpty() || value4.isEmpty() || value3.isEmpty()) {
				if (value2.isEmpty() && value4.isEmpty() && value3.isEmpty()) {
					ShowMessage("Please enter name, address and phone number!");
				} else if (value2.isEmpty() && value4.isEmpty()) {
					ShowMessage("Please enter name and address!");
				} else if (value2.isEmpty() && value3.isEmpty()) {
					ShowMessage("Please enter name and phone number!");
				} else if (value3.isEmpty() && value4.isEmpty()) {
					ShowMessage("Please enter phone number and address!");
				} else if (value2.isEmpty()) {
					ShowMessage("Please enter name!");
				} else if (value4.isEmpty()) {
					ShowMessage("Please enter address!");
				} else {
					ShowMessage("Please enter phone number!");
				}
			} else { // if none of the above if true the statement will execute and update into the
						// database
				ps.execute();
				ShowMessage("Employee Updated!");
				updateTable();
			}

		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	/**
	 * Adding employee info
	 */
	@FXML
	public void add_emp() {
		// Connecting to the database
		Connection con = DatabaseConnection.getConnection();
		// Doing SQL query for inserting the info in database
		String sql = "insert into employee (emp_name,emp_add,emp_phone) values (?,?,?);";
		try {
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ps.setString(1, txt_name.getText());
			ps.setString(2, txt_add.getText());
			ps.setString(3, txt_phone.getText());
			// Information will not be updated if any one of the below if true
			if (txt_name.getText().isEmpty() || txt_add.getText().isEmpty() || txt_phone.getText().isEmpty()) {
				if (txt_name.getText().isEmpty() && txt_add.getText().isEmpty() && txt_phone.getText().isEmpty()) {
					ShowMessage("Please enter name, address and phone number!");
				} else if (txt_name.getText().isEmpty() && txt_add.getText().isEmpty()) {
					ShowMessage("Please enter name and address!");
				} else if (txt_name.getText().isEmpty() && txt_phone.getText().isEmpty()) {
					ShowMessage("Please enter name and phone number!");
				} else if (txt_phone.getText().isEmpty() && txt_add.getText().isEmpty()) {
					ShowMessage("Please enter phone number and address!");
				} else if (txt_name.getText().isEmpty()) {
					ShowMessage("Please enter name!");
				} else if (txt_add.getText().isEmpty()) {
					ShowMessage("Please enter address!");
				} else {
					ShowMessage("Please enter phone number!");
				}
			} else { // if none of the above if true the statement will execute and update into the
						// database
				
				//input validation for Employee Class
				if(Employee.checkEmployeeName(txt_name.getText()) && Employee.checkEmployeeAddress(txt_add.getText()) && Employee.checkEmployeePhone(txt_phone.getText())) {
				ps.execute();
				ShowMessage("Employee Added!");
				}
				else {
					ShowMessage("Invalid input for one/all of the field(s)");
				}
				// updating the table to show into the frame
				updateTable();
			}


		} catch (Exception e) {

		}
	}

	/**
	 * Deleting employee info
	 */
	@FXML
	public void deleteEmployee() {
		// Connecting to the database
		Connection con = DatabaseConnection.getConnection();
		// Doing SQL query to delete employee info
		String sql = "delete from employee where emp_ID = ?";
		try {

			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ps.setString(1, txt_ID.getText());
			// If nothing is selected, shows error message
			if (txt_ID.getText().isEmpty()) {
				ShowMessage("Please select an employee!");
			} else { // otherwise execute the SQL statement and delete
				ps.execute();
				ShowMessage("Employee Deleted!");
				updateTable();
			}

		} catch (Exception e) {
			//System.out.println(e);
		}
	}

	/**
	 * get access to the text fields which are selected
	 * 
	 * @param event
	 */
	@FXML
	void getSelected(MouseEvent event) {

		Employee clickedEmp = table_emp.getSelectionModel().getSelectedItem();
		txt_name.setText(String.valueOf(clickedEmp.getEmpName()));
		txt_ID.setText(String.valueOf(clickedEmp.getEmpID()));
		txt_add.setText(String.valueOf(clickedEmp.getEmpAdd()));
		txt_phone.setText(String.valueOf(clickedEmp.getEmpPhone()));
	}

//--------------------------------------------------------- EmployeeManagement Frame end ------------------------------------------------------------------------

//--------------------------------------------------------- VehicleManagement Frame start ------------------------------------------------------------------------

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

	/**
	 * Update vehicle info table
	 */
	@FXML
	public void updateTable1() {
		try {
			// Setting the respective columns with the class attributes for showing them
			// into the tables.
			col_V_ID.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("vehicleID"));
			col_type.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleType"));
			col_emp_ID.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("empID"));

			table_vehicle.setItems(Vehicle.getVehicleInfo());
		} catch (Exception e) {
		}

	}

	/**
	 * adding vehicle info
	 */
	@FXML
	public void add_vehicle() {

		try {
			// connecting to the database
			Connection con = DatabaseConnection.getConnection();
			// Doing SQL query for inserting into vehicle table
			String sql = "insert into vehicle (emp_ID,vehicle_type) values (?,?);";
			// Doing SQL query to make sure employee ID exists
			String sql2 = "select * from employee where emp_ID = ?";

			PreparedStatement ps;

			ps = con.prepareStatement(sql);
			PreparedStatement ps2 = con.prepareStatement(sql2);

			ps2.setString(1, txt_emp_ID.getText());
			ResultSet rs = ps2.executeQuery();

			ps.setString(1, txt_emp_ID.getText());
			ps.setString(2, txt_VType.getText());
			// checking to see if have any empty fields
			if (txt_emp_ID.getText().isEmpty() || txt_VType.getText().isEmpty()) {

				if (txt_emp_ID.getText().isEmpty() && txt_VType.getText().isEmpty()) {
					ShowMessage("Enter employee ID and vehicle type!");
				} else if (txt_VType.getText().isEmpty()) {
					ShowMessage("Enter vehicle type!");
				} else {
					ShowMessage("Enter employee ID!");
				}
			}
			// if not execute
			if (!txt_emp_ID.getText().isEmpty() && !txt_VType.getText().isEmpty()) {
				// checking to see if employee ID exists
				if (!(rs.next()) && !(txt_emp_ID.getText().isEmpty())) {
					ShowMessage("Employee ID does not exists!");
				} else {
					ps.execute();
					ShowMessage("Vehicle Added!");
					updateTable1();
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * updating vehicle info
	 */
	@FXML
	public void updateVehicle() {
		try {
			// connecting to the database
			Connection con = DatabaseConnection.getConnection();
			// assigning values to each text field
			String value1 = txt_V_ID.getText();
			String value2 = txt_emp_ID.getText();
			String value3 = txt_VType.getText();
			// Doing SQL query to update vehicle info
			String sql = "update vehicle set emp_ID = '" + value2 + "', vehicle_type = '" + value3
					+ "' where vehicle_ID = '" + value1 + "'";
			// doing SQL query to make sure employee id exists
			String sql2 = "select * from employee where emp_ID = ?";

			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			PreparedStatement ps2 = con.prepareStatement(sql2);

			ps2.setString(1, value2);
			ResultSet rs = ps2.executeQuery();
			// if any of the below statements are true (empty fields), show an error message
			if (value2.isEmpty() || value3.isEmpty()) {

				if (value2.isEmpty() && value3.isEmpty()) {
					ShowMessage("Enter an employee ID and vehicle type!");

				} else if (value3.isEmpty()) {
					ShowMessage("Enter vehicle type!");
				} else {
					ShowMessage("Enter employee ID!");
				}
			}

			if (!value2.isEmpty() && !value3.isEmpty()) {
				//// check to see if user entered valid employee ID
				if (!(rs.next())) {
					ShowMessage("Employee ID does not exists!");

				} else {
					ShowMessage("Vehicle updated!");
					ps.execute();
					updateTable1();
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * deleting vehicle info
	 */
	@FXML
	public void deleteVehicle() {
		// connecting to the database
		Connection con = DatabaseConnection.getConnection();
		String sql = "delete from vehicle where vehicle_ID = ?";
		try {
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ps.setString(1, txt_V_ID.getText());
			if (txt_V_ID.getText().isEmpty()) {
				ShowMessage("Please select a vehicle");
			} else {
				// execute the sql statement and update the table
				ps.execute();

				ShowMessage("Vehicle Deleted!");

				updateTable1();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get access to the text fields which are selected
	 * 
	 * @param event
	 */
	@FXML
	void getSelectedV(MouseEvent event) {

		Vehicle clickedV = table_vehicle.getSelectionModel().getSelectedItem();
		txt_V_ID.setText(String.valueOf(clickedV.getVehicleID()));
		txt_emp_ID.setText(String.valueOf(clickedV.getEmpID()));
		txt_VType.setText(String.valueOf(clickedV.getVehicleType()));
	}

//--------------------------------------------------------- VehicleManagement Frame end ------------------------------------------------------------------------

//--------------------------------------------------------- Generate reports Frame start ------------------------------------------------------------------------

	@FXML
	private TextField txt_delivery;

	@FXML
	private TextField txt_grandtotal;

	@FXML
	private TextField txt_inhouse;

	@FXML
	private TextField txt_takeout;

	public void initializeReports() {

		Connection con = DatabaseConnection.getConnection(); // getting connection from the database

		try {
			Statement stmt = con.createStatement(); // creating a statement in order to generate a result set
			String sql_inhouse = "select sum(Order_Total) from orders where Order_Type = \"InHouse\";"; // quering to
																										// find order
																										// total for in
																										// house service
			ResultSet rs1 = stmt.executeQuery(sql_inhouse); // getting results from query

			if (rs1.next()) { // setting the text as sum rounded to 2 decimals
				double sum = rs1.getDouble(1);
				String sumAsString = String.format("In - House = $ %.2f", sum);
				txt_inhouse.setText(sumAsString);
			}

			String sql_takeout = "select sum(Order_Total) from orders where Order_Type = \"Takeout\";"; // quering to
																										// find order
																										// total for
																										// takeout
																										// service
			ResultSet rs2 = stmt.executeQuery(sql_takeout); // getting results from query

			if (rs2.next()) { // setting the text as sum rounded to 2 decimals
				double sum = rs2.getDouble(1);
				String sumAsString = String.format("Takeout = $ %.2f", sum);
				txt_takeout.setText(sumAsString);
			}

			String sql_delivery = "select sum(Order_Total) from orders where Order_Type = \"Delivery\";"; // quering to
																											// find
																											// order
																											// total for
																											// delivery
																											// service
			ResultSet rs3 = stmt.executeQuery(sql_delivery); // getting results from query

			if (rs3.next()) { // setting the text as sum rounded to 2 decimals
				double sum = rs3.getDouble(1);
				String sumAsString = String.format("Delivery = $ %.2f", sum);
				txt_delivery.setText(sumAsString);
			}
			String sql_gtotal = "select sum(Order_Total) from orders;"; // quering to find order total for delivery
																		// service
			ResultSet rs4 = stmt.executeQuery(sql_gtotal); // getting results from query

			if (rs4.next()) { // setting the text as sum rounded to 2 decimals
				double sum = rs4.getDouble(1);
				String sumAsString = String.format("Grand Total = $ %.2f", sum);
				txt_grandtotal.setText(sumAsString);
			}

		} catch (Exception e) {

		}
	}

//--------------------------------------------------------- Generate reports Frame end ------------------------------------------------------------------------

//--------------------------------------------------------- Graph Frame start ------------------------------------------------------------------------

	@FXML
	private RadioButton rb_delivery;

	@FXML
	private RadioButton rb_grandtotal;

	@FXML
	private RadioButton rb_inhouse;

	@FXML
	private RadioButton rb_takeout;

	// create the lineChart to start graphing
	@FXML
	private LineChart lineChart;

	/* All Graph data entries below */
	@FXML
	public void GrandTotal(ActionEvent actionEvent) { // Grand total graph data
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create a series for this data to be implemented.
		
		series.setName("Grand Total"); //Sets the name for the series for it to display in the legend
		
		// Full data series for the GrandTotal type
		series.getData().add(new XYChart.Data<>("Jan", 0));
		series.getData().add(new XYChart.Data<>("Feb", 20));
		series.getData().add(new XYChart.Data<>("Mar", 40));
		series.getData().add(new XYChart.Data<>("Apr", 60));
		series.getData().add(new XYChart.Data<>("May", 80));
		series.getData().add(new XYChart.Data<>("Jun", 100));
		series.getData().add(new XYChart.Data<>("Jul", 120));
		series.getData().add(new XYChart.Data<>("Aug", 140));
		series.getData().add(new XYChart.Data<>("Sep", 160));
		series.getData().add(new XYChart.Data<>("Oct", 180));
		series.getData().add(new XYChart.Data<>("Nov", 200));
		series.getData().add(new XYChart.Data<>("Dec", 220));
		
		//adding series to lineChart
		lineChart.getData().add(series);

	}

	@FXML
	public void InHouse(ActionEvent actionEvent) { // In-house graph data
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create a series for this data to be implemented.
		
		series.setName("In-house"); //Sets the name for the series for it to display in the legend
		
		// Full data series for the InHouse type
		series.getData().add(new XYChart.Data<>("Jan", 0));
		series.getData().add(new XYChart.Data<>("Feb", 60));
		series.getData().add(new XYChart.Data<>("Mar", 40));
		series.getData().add(new XYChart.Data<>("Apr", 120));
		series.getData().add(new XYChart.Data<>("May", 814));
		series.getData().add(new XYChart.Data<>("Jun", 450));
		series.getData().add(new XYChart.Data<>("Jul", 700));
		series.getData().add(new XYChart.Data<>("Aug", 230));
		series.getData().add(new XYChart.Data<>("Sep", 5));
		series.getData().add(new XYChart.Data<>("Oct", 809));
		series.getData().add(new XYChart.Data<>("Nov", 546));
		series.getData().add(new XYChart.Data<>("Dec", 375));
		
		//adding series to lineChart
		lineChart.getData().add(series);

	}

	@FXML
	public void Takeout(ActionEvent actionEvent) { // Take-out graph data
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create a series for this data to be implemented.
		
		series.setName("Takeout"); //Sets the name for the series for it to display in the legend
		
		// Full data series for the Takeout type
		series.getData().add(new XYChart.Data<>("Jan", 0));
		series.getData().add(new XYChart.Data<>("Feb", 20));
		series.getData().add(new XYChart.Data<>("Mar", 40));
		series.getData().add(new XYChart.Data<>("Apr", 60));
		series.getData().add(new XYChart.Data<>("May", 80));
		series.getData().add(new XYChart.Data<>("Jun", 100));
		series.getData().add(new XYChart.Data<>("Jul", 120));
		series.getData().add(new XYChart.Data<>("Aug", 140));
		series.getData().add(new XYChart.Data<>("Sep", 160));
		series.getData().add(new XYChart.Data<>("Oct", 180));
		series.getData().add(new XYChart.Data<>("Nov", 200));
		series.getData().add(new XYChart.Data<>("Dec", 220));
		
		//adding series to lineChart
		lineChart.getData().add(series);

	}

	@FXML
	public void Delivery(ActionEvent actionEvent) { // Delivery graph data
		
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); //Create a series for this data to be implemented.
		
		series.setName("Delivery"); //Sets the name for the series for it to display in the legend
		
		// Full data series for the Delivery type
		series.getData().add(new XYChart.Data<>("Jan", 0));
		series.getData().add(new XYChart.Data<>("Feb", 20));
		series.getData().add(new XYChart.Data<>("Mar", 40));
		series.getData().add(new XYChart.Data<>("Apr", 60));
		series.getData().add(new XYChart.Data<>("May", 80));
		series.getData().add(new XYChart.Data<>("Jun", 100));
		series.getData().add(new XYChart.Data<>("Jul", 120));
		series.getData().add(new XYChart.Data<>("Aug", 140));
		series.getData().add(new XYChart.Data<>("Sep", 160));
		series.getData().add(new XYChart.Data<>("Oct", 180));
		series.getData().add(new XYChart.Data<>("Nov", 200));
		series.getData().add(new XYChart.Data<>("Dec", 220));
		
		//adding series to lineChart
		lineChart.getData().add(series);

	}

	@FXML
	public void clearData() { // for clearing data on the graph and clearing radio buttons
		lineChart.getData().clear();
		rb_delivery.setSelected(false);
		rb_takeout.setSelected(false);
		rb_grandtotal.setSelected(false);
		rb_inhouse.setSelected(false);
	}

//--------------------------------------------------------- Graph Frame end ------------------------------------------------------------------------

//--------------------------------------------------------- Place Order Frame begins ------------------------------------------------------------
	
	
	// Pizza-related buttons
	
	@FXML
	private ChoiceBox pizza_type = new ChoiceBox<String>();
	private final ObservableList<String> pizzaTypeList = FXCollections.observableArrayList("Cheese", "Meat", "Veggie");

	@FXML
	private ChoiceBox pizza_size = new ChoiceBox<String>();
	private final ObservableList<String> pizzaSizeList = FXCollections.observableArrayList("Small", "Medium", "Large");

	@FXML
	private Button add_pizza;
	
	// Order-related buttons
	
	@FXML
	private ChoiceBox order_type = new ChoiceBox<String>();
	private final ObservableList<String> orderTypeList = FXCollections.observableArrayList("InHouse", "Takeout", "Delivery");

	@FXML
	private ChoiceBox payment_type = new ChoiceBox<String>();
	private final ObservableList<String> paymentTypeList = FXCollections.observableArrayList("Cash", "Card");

	@FXML
	private TextField txt_order_num;
	
	@FXML
	private TextField txt_cust_name;
	
	@FXML
	private TextField txt_order_total;
	
	@FXML
	private TextArea txtorder;

	@FXML
	private Button completeOrder;
	
	
	Order order = new Order();                                                 // Order object instantiated

	
	/**
	 * This method adds the pizzas based on the options selected, but also handles
	 * the information related to the order as well (i.e. Order Number, Order Type,
	 * etc.).
	 * 
	 * NOTE: Though is is named "addPizza", this method will throw an error message
	 * if any drop-boxes are incomplete.
	 */
	@FXML
	public void addPizza() {
		
		// Try to receive inputs to create and display all order information.
		
		try {

			// Pizza Handling
			
			if (pizza_type.getValue() != null                                 // I.V. - Validate order and pizza info
					&& pizza_size.getValue() != null 
					&& order_type.getValue() != null
					&& payment_type.getValue() != null
					&& !(txt_cust_name.getText().isEmpty()) )
			{

				if (pizza_type.getValue() == "Cheese") {                      // Cheese Pizza Options

					if (pizza_size.getValue() == "Small") {
						Pizza pizza = new Pizza("Cheese", "Small");
						order.addItem(pizza);
						// Price: 7.99
						txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nSmall Cheese Pizza Added!"
								+ "\nPrice $7.99 + tax");
					}
					
					if (pizza_size.getValue() == "Medium") {
						Pizza pizza = new Pizza("Cheese", "Medium");
						order.addItem(pizza);
						// Price: 9.99
						txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nMedium Cheese Pizza Added!"
								+ "\nPrice $9.99 + tax");
					}
					
					if (pizza_size.getValue() == "Large") {
						Pizza pizza = new Pizza("Cheese", "Large");
						order.addItem(pizza);
						// Price: 11.99
						txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nLarge Cheese Pizza Added!"
								+ "\nPrice $11.99 + tax");
					}

				}

				if (pizza_type.getValue() == "Meat") {                         // Meat Pizza Options
					
					if (pizza_size.getValue() == "Small") {
						Pizza pizza = new Pizza("Meat", "Small");
						order.addItem(pizza);
						// Price: 9.99
						txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nSmall Meat Pizza Added!"
								+ "\nPrice $9.99 + tax");
					}
					
					if (pizza_size.getValue() == "Medium") {
						Pizza pizza = new Pizza("Meat", "Medium");
						order.addItem(pizza);
						// Price: 14.99
						txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nMedium Meat Pizza Added!"
								+ "\nPrice $14.99 + tax");
					}
					
					if (pizza_size.getValue() == "Large") {
						Pizza pizza = new Pizza("Meat", "Large");
						order.addItem(pizza);
						// Price: 19.99
						txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nLarge Meat Pizza Added!"
								+ "\nPrice $19.99 + tax");
					}

				}
				
				if (pizza_type.getValue() == "Veggie") {                        // Veggie Pizza Options
					
					if (pizza_size.getValue() == "Small") {
						Pizza pizza = new Pizza("Veggie", "Small");
						order.addItem(pizza);
						// Price: 9.99
						txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nSmall Veggie Pizza Added!"
								+ "\nPrice $9.99 + tax");
					}
					
					if (pizza_size.getValue() == "Medium") {
						Pizza pizza = new Pizza("Veggie", "Medium");
						order.addItem(pizza);
						// Price: 14.99
						txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nMedium Veggie Pizza Added!"
								+ "\nPrice $14.99 + tax");
					}
					
					if (pizza_size.getValue() == "Large") {
						Pizza pizza = new Pizza("Veggie", "Large");
						order.addItem(pizza);
						// Price: 19.99
						txtorder.appendText("\n\nOrder No: " + order.getOrderNumber() + "\nLarge Veggie Pizza Added!"
								+ "\nPrice $19.99 + tax");
					}

				}

				// Order Handling

				if (order_type.getValue() == "InHouse") {                        // + 5% InHouse Fee
					order.setOrderType("InHouse");
					order.calculateTotal(order);
					txtorder.appendText("\nService: InHouse");
				} 
				
				else if (order_type.getValue() == "Takeout") {
					order.setOrderType("Takeout");
					order.calculateTotal(order);
					txtorder.appendText("\nService: Takeout");
				} 
				
				else if (order_type.getValue() == "Delivery") {                  // + 3.00 Delivery Fee
					order.setOrderType("Service: Delivery");
					order.calculateTotal(order);
					txtorder.appendText("\nDelivery");
				}
				
			}

			order.calculateSubTotal(order);
			order.setOrderTotal(order.calculateTotal(order));
			txt_order_total.setText(String.valueOf(order.getOrderTotal()));   // Calculate and display order total												
			txtorder.appendText("\nOrder Total: " + order.getOrderTotal() + "\n");
			
			order.setOrderNumber(order.getOrderNumber());                     // Generate and display order number
			txt_order_num.setText(String.valueOf(order.getOrderNumber()));

		} 
		catch (Exception e) 
		{
			ShowMessage("Cannot process request! Please check input fields.");
		}
		
	}
	
	/**
	 * Method to initializee all of the choices boxes on the Place Order frame.
	 */
	public void pizzaList() {
		pizza_type.setItems(pizzaTypeList);
		pizza_size.setItems(pizzaSizeList);
		order_type.setItems(orderTypeList);
		payment_type.setItems(paymentTypeList);
		
	}
	

	/**
	 * This method will add the order to the database based on the information from within each of the
	 * text boxes. This method also involves more input validation.
	 */
	public void completeOrder() {

		// Creating and formatting the current date for the DB
		Date today = new Date();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		String orderDate = DATE_FORMAT.format(today);

		// Start of adding order to DB
		Connection con = DatabaseConnection.getConnection();
		String sql = "insert into orders (Order_Number, Customer_Name, Order_Total, Order_Type, Order_Date) "
				+ "values (?,?,?,?,?);";
		
		// I.V. - Fields must all be filled
		if ((order_type.getValue() != null)
				&& (payment_type.getValue() != null) 
				&& (!txt_order_num.getText().isEmpty())
				&& (!txt_cust_name.getText().isEmpty()) 
				&& (!txt_order_total.getText().isEmpty())) 
		{

			try {
				PreparedStatement ps;
				ps = con.prepareStatement(sql);
				ps.setString(1, txt_order_num.getText());                     // Column 1 info
				ps.setString(2, txt_cust_name.getText());                     // Column 2 info
				ps.setString(3, txt_order_total.getText());                   // Column 3 info
				ps.setString(4, (String) order_type.getValue());              // Column 4 info
				ps.setString(5, orderDate);                                   // Column 5 info

				// Added layer of input validation
				if (txt_order_num.getText().isEmpty() 
						|| txt_cust_name.getText().isEmpty()
						|| txt_order_total.getText().isEmpty()) {

					if (txt_order_num.getText().isEmpty()) {
						ShowMessage("Order number invalid! Please check inputs.");
					} else if (txt_cust_name.getText().isEmpty()) {
						ShowMessage("Please insert a customer name!");
					} else if (txt_order_total.getText().isEmpty()) {
						ShowMessage("Order total invlaid! Please check inputs.");
					} else if (String.valueOf(order_type).isEmpty()) {
						ShowMessage("Order type invalid! Please check inputs.");
					}

				}

				else /* Input valid, order transferred to DB */
				{
					ps.execute();
					ShowMessage("Order added successfully!");
				}

			} 
			catch (Exception e)                                               // Input invalid
			{
				System.out.println(e);
				ShowMessage("Cannot process request. Please try again.");
			}

		}
		else
		{
			ShowMessage("Cannot process request. Please try again.");
		}
		
	}
}	
//--------------------------------------------------------- Place Order Frame end --------------------------------------------------------------------
