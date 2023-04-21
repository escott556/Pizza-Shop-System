///**
// * Author(s):                The Pizza Shop Team
// * Last Editted:             2/18/2023
// * 
// * Purpose:                  This is the main controller class for our system. It will control and handle the events
// *                           instantiated between the various frames of the system. [SUBJECT TO CHANGE]
// *                           
// * ***NOTE***:               2/18/2023
// *                           This is not properly implemented just yet. The Login Frame events are not included and
// *                           MUST be added beforehand for the system to work to its fullest extent.
// *                           
// *                           2/19/2023
// *                           Added the two methods to communicate with the Employee Info Hub and Employee Info Access Info frames.
// *                           It should be noted that the names of the files used are only an assumption. They can be changed, but must match
// *                           in this controller, the Main class, SceneBuilder, and in each of the respective .fxml files.
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
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
		if (username.getText().toString().equals("admin") && password.getText().toString().equals("password")) {
			wrongLogin.setText("Logging in.");

			m.changeScene("ManagerHub.fxml");
		}else if(username.getText().toString().equals("employee") && password.getText().toString().equals("password")) {
			wrongLogin.setText("Logging in!");
			m.changeScene("EmployeeHub.fxml");
		} else if (username.getText().isEmpty() && password.getText().isEmpty()) {
			wrongLogin.setText("Enter your username and password.");
		} else {
			wrongLogin.setText("Incorrect username or password.");
		}
	}

//--------------------------------------------------------- Login Frame end ------------------------------------------------------------------------

//--------------------------------------------------------- ManagerHub Frame start ------------------------------------------------------------------------

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
	private Button placeOrder;

	@FXML
	public void change_to_placeOrder(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("PlaceOrder.fxml");
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

	@FXML
	public void change_to_generateReports(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("GenerateReports.fxml");
	}

	@FXML
	public void change_to_graphSales(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("GraphSales.fxml");
	}
//--------------------------------------------------------- ManagerHub Frame end ------------------------------------------------------------------------

//--------------------------------------------------------- EmployeeMangement Frame start ------------------------------------------------------------------------

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
		try {

			ObservableList<Employee> listE;
			listE = Employee.getEmployeeInfo();

			col_ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("empID"));
			col_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("empName"));
			col_add.setCellValueFactory(new PropertyValueFactory<Employee, String>("empAdd"));
			col_phone.setCellValueFactory(new PropertyValueFactory<Employee, String>("empPhone"));

			table_emp.setItems(listE);

		} catch (Exception e) {
			System.out.println(e);
		}

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
			} else {
				ps.execute();
				ShowMessage("Employee Added!");
			}
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

			if (value2.isEmpty() || value4.isEmpty() || value3.isEmpty()) {
				if (value2.isEmpty() && value4.isEmpty()) {
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
			} else {
				ps.execute();
				ShowMessage("Employee Updated!");
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
			System.out.println(e);
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

	public void updateTable1() {
		try {

			ObservableList<Vehicle> listV;
			listV = Vehicle.getVehicleInfo();

			col_V_ID.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("vehicleID"));
			col_type.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleType"));
			col_emp_ID.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("empID"));

			table_vehicle.setItems(listV);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void add_vehicle() {

		try {

			Connection con = DatabaseConnection.getConnection();

			String sql = "insert into vehicle (emp_ID,vehicle_type) values (?,?);";
			String sql2 = "select * from employee where emp_ID = ?";

			PreparedStatement ps;

			ps = con.prepareStatement(sql);
			PreparedStatement ps2 = con.prepareStatement(sql2);

			ps2.setString(1, txt_emp_ID.getText());
			ResultSet rs = ps2.executeQuery();

			ps.setString(1, txt_emp_ID.getText());
			ps.setString(2, txt_VType.getText());

			if (txt_emp_ID.getText().isEmpty() || txt_VType.getText().isEmpty()) {

				if (txt_emp_ID.getText().isEmpty() && txt_VType.getText().isEmpty()) {
					ShowMessage("Enter employee ID and vehicle type!");
				} else if (txt_VType.getText().isEmpty()) {
					ShowMessage("Enter vehicle type!");
				} else {
					ShowMessage("Enter employee ID!");
				}
			}

			if (!txt_emp_ID.getText().isEmpty() && !txt_VType.getText().isEmpty()) {
				if (!(rs.next()) && !(txt_emp_ID.getText().isEmpty())) {
					ShowMessage("Employee ID does not exists!");
				}
				ps.execute();
				ShowMessage("Vehicle Added!");
			}

			updateTable1();

		} catch (Exception e) {
			System.out.println(e);
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

//--------------------------------------------------------- VehicleManagement Frame end ------------------------------------------------------------------------
//--------------------------------------------------------- Place Order Frame begins ------------------------------------------------------------
	
	
	// Pizza-related buttons
	
	@FXML
	private ChoiceBox pizza_type = new ChoiceBox<String>();                    // Choice Box for pizza type
	private final ObservableList<String> pizzaTypeList = FXCollections.observableArrayList("Cheese", "Meat", "Veggie");

	@FXML
	private ChoiceBox pizza_size = new ChoiceBox<String>();	                   // Choice Box for pizza size
	private final ObservableList<String> pizzaSizeList = FXCollections.observableArrayList("Small", "Medium", "Large");

	@FXML
	private Button add_pizza;	                                               // Button to add pizza with info. selected
	
	// Order-related buttons
	
	@FXML
	private ChoiceBox order_type = new ChoiceBox<String>();	               // Choice box for service type
	private final ObservableList<String> serviceTypeList = FXCollections.observableArrayList("InHouse", "Takeout", "Delivery");

	@FXML
	private ChoiceBox payment_type = new ChoiceBox<String>();	               // Choice Box for payment type
	private final ObservableList<String> paymentTypeList = FXCollections.observableArrayList("Cash", "Card");

	@FXML
	private TextField txt_order_num; 	                                       // Text box for order number
	
	@FXML
	private TextField txt_cust_name;	                                       // Text box for customer name
	
	@FXML
	private TextField txt_order_total;	                                       // Text box for order total
	
	
	/**
	 * Method to initializee all of the choices boxes on the Place Order frame.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		// List all of the items for each choice box
		pizza_type.setItems(pizzaTypeList);
		
		pizza_size.setItems(pizzaSizeList);

		order_type.setItems(serviceTypeList);
		
		payment_type.setItems(paymentTypeList);
	}

	@FXML                                                                      // Button to complete order
	private Button completeOrder;
	
	Order order = new Order();                                                 // Order object instantiated
	
	
	/**
	 * This method adds the pizzas based on the options selected, but also handles the information related to the
	 * order as well (i.e. Order Number, Order Type, etc.).
	 * 
	 * NOTE: Though is is named "addPizza", this method will throw an error message if any drop-boxes are incomplete.
	 */
	public void addPizza() 
	{
		// Pizza handling

		if (pizza_type.getValue() != null && pizza_size.getValue() != null && order_type.getValue() != null
				&& payment_type.getValue() != null) // i.e. pizza type and size must be selected
		{

			if (pizza_type.getValue() == "Cheese") {

				if (pizza_size.getValue() == "Small") {
					Pizza pizza = new Pizza("Cheese", "Small"); // Price: 7.99
					order.addItem(pizza);
					txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nSmall Cheese Pizza Added!"
							+ "\nPrice $7.99 + tax");
					
				}
				if (pizza_size.getValue() == "Medium") {
					Pizza pizza = new Pizza("Cheese", "Medium"); // Price: 9.99
					order.addItem(pizza);
					txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nMedium Cheese Pizza Added!"
							+ "\nPrice $9.99 + tax");
					
				}
				if (pizza_size.getValue() == "Large") {
					Pizza pizza = new Pizza("Cheese", "Large"); // Price: 11.99
					order.addItem(pizza);
					txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nLarge Cheese Pizza Added!"
							+ "\nPrice $11.99 + tax");
					
				}

			}

			if (pizza_type.getValue() == "Meat") {
				if (pizza_size.getValue() == "Small") {
					Pizza pizza = new Pizza("Meat", "Small"); // Price: 9.99
					order.addItem(pizza);
					txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nSmall Meat Pizza Added!"
							+ "\nPrice $9.99 + tax");
					
				}
				if (pizza_size.getValue() == "Medium") {
					Pizza pizza = new Pizza("Meat", "Medium"); // Price: 14.99
					order.addItem(pizza);
					txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nMedium Meat Pizza Added!"
							+ "\nPrice $14.99 + tax");
					
				}
				if (pizza_size.getValue() == "Large") {
					Pizza pizza = new Pizza("Meat", "Large"); // Price: 19.99
					order.addItem(pizza);
					txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nLarge Meat Pizza Added!"
							+ "\nPrice $19.99 + tax");
					
				}

			}
			if (pizza_type.getValue() == "Veggie") {
				if (pizza_size.getValue() == "Small") {
					Pizza pizza = new Pizza("Veggie", "Small"); // Price: 9.99
					order.addItem(pizza);
					txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nSmall Veggie Pizza Added!"
							+ "\nPrice $9.99 + tax");
					
				}
				if (pizza_size.getValue() == "Medium") {
					Pizza pizza = new Pizza("Veggie", "Medium"); // Price: 14.99
					order.addItem(pizza);
					txtorder.appendText("\nOrder No: " + order.getOrderNumber() + "\nMedium Veggie Pizza Added!"
							+ "\nPrice $14.99 + tax");
					
				}
				if (pizza_size.getValue() == "Large") {
					Pizza pizza = new Pizza("Veggie", "Large"); // Price: 19.99
					order.addItem(pizza);
					txtorder.appendText("\n\nOrder No: " + order.getOrderNumber() + "\nLarge Veggie Pizza Added!"
							+ "\nPrice $19.99 + tax");
					
				}

			}

			// Order handling (Order Type and added fees)

			if (order_type.getValue() == "InHouse") {
				order.setOrderType("InHouse");
				order.calculateTotal(order);
				txtorder.appendText("\nService: InHouse");

			} else if (order_type.getValue() == "Takeout") {
				order.setOrderType("Takeout");
				// System.out.println("Set order type as in-house! %5 Fee Added!");
				order.calculateTotal(order);
				txtorder.appendText("\nService: Takeout");
			
			} else if (order_type.getValue() == "Delivery") {
				order.setOrderType("Service: Delivery");
				// System.out.println("Set order type as delivery! $3.00 Fee Added!");
				order.calculateTotal(order);
				txtorder.appendText("\nDelivery");
				
			}
		}

		else /* Drop box fields are incomplete. */
		{
			ShowMessage("All fields not complete!");
		}

		order.calculateSubTotal(order); // Total after taxes and fees

		order.setOrderTotal(order.calculateTotal(order)); // Sets the total as what was calculated.

		txt_order_total.setText(String.valueOf(order.getOrderTotal())); // Displays the total after order type is chosen
		txtorder.appendText("\nOrder Total: " + order.getOrderTotal() + "\n");
		order.setOrderNumber(order.getOrderNumber());
		txt_order_num.setText(String.valueOf(order.getOrderNumber())); // Should display the total to the frame

	}
	
	/**
	 * This method will add the order to the database based on the information from within each of the
	 * text boxes. This method also involves more input validation.
	 */
	public void completeOrder()
	{
		if( (order_type.getValue() != null) && (payment_type.getValue() != null) && (!txt_order_num.getText().isEmpty()) 
				&& (!txt_cust_name.getText().isEmpty()) && (!txt_order_total.getText().isEmpty()))    // Inp. Val.
		{
			Date orderDate = new Date();                                                 // Create a current date to use.
			
			// Start of adding order to DB
			Connection con = DatabaseConnection.getConnection();
			String sql = "insert into orders (Order_Number, Customer_Name, Order_Total, Order_Type, Order_Date) "
					+ "values (?,?,?,?,?);";
			try {
				PreparedStatement ps;
				ps = con.prepareStatement(sql);
				ps.setString(1, txt_order_num.getText());                                // Column 1 info
				ps.setString(2, txt_cust_name.getText());                                // Column 2 info
				ps.setString(3, txt_order_total.getText());                              // Column 3 info
				ps.setString(4, String.valueOf(order_type));                             // Column 4 info
				ps.setString(5, String.valueOf(orderDate));                              // Column 5 info

				
				// Added layer of input validation.
				if (txt_order_num.getText().isEmpty() || txt_cust_name.getText().isEmpty() || txt_order_total.getText().isEmpty()) 
				{
		
					if (txt_order_num.getText().isEmpty()) 
					{
						ShowMessage("Order number invalid! Please check inputs.");
					} 
					else if (txt_cust_name.getText().isEmpty()) 
					{
						ShowMessage("Please insert a customer name!");
					} 
					else if (txt_order_total.getText().isEmpty()) 
					{
						ShowMessage("Order total invlaid! Please check inputs.");
					} 
					else if (String.valueOf(order_type).isEmpty()) 
					{
						ShowMessage("Order type invalid! Please check inputs.");
					}
					
				} 
				
				else                                                                     // Input is valid, order transferred to DB
				{
					ps.execute();
					ShowMessage("Order added successfully!");
				}

			} 
			catch (Exception e)                                                          // Input invalid
			{
				ShowMessage("Cannot process request. Please check inuts and try again.");
			}
			
		}
		else                                                                             // Input invalid
		{
			ShowMessage("Cannot process request. Please check inputs and try again.");
		}
	}
		

		
		
//--------------------------------------------------------- Place Order Frame end --------------------------------------------------------------------
//--------------------------------------------------------- Graph Frame start ------------------------------------------------------------------------

	@FXML
	private LineChart lineChart;

	@FXML
	public void drawChart(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");

		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); // Create the series for data to
																						// be implemented.

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

		// adding series to lineChart
		lineChart.getData().add(series);

	}

	@FXML
	public void drawChart2(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");

		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); // Create the series for data to
																						// be implemented.

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

		// adding series to lineChart
		lineChart.getData().add(series);

	}

	@FXML
	public void drawChart3(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");

		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); // Create the series for data to
																						// be implemented.

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

		// adding series to lineChart
		lineChart.getData().add(series);

	}

	@FXML
	public void drawChart4(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");

		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); // Create the series for data to
																						// be implemented.

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

		// adding series to lineChart
		lineChart.getData().add(series);

	}

	@FXML
	public void drawChart5(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");

		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); // Create the series for data to
																						// be implemented.

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

		// adding series to lineChart
		lineChart.getData().add(series);

	}

	@FXML
	public void drawChart6(ActionEvent actionEvent) {
		Axis<String> xAxis = lineChart.getXAxis();
		xAxis.setLabel("Month");

		Axis<Number> yAxis = lineChart.getYAxis();
		yAxis.setLabel("Revenue");

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>(); // Create the series for data to
																						// be implemented.

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

		// adding series to lineChart
		lineChart.getData().add(series);

	}
//--------------------------------------------------------- Graph Frame end ------------------------------------------------------------------------

}
