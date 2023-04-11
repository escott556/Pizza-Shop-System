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
	public void change_to_orderPayment(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("OrderPayment.fxml");
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
//------------------------------------------------- Place Order Frame begins ------------------------------------------------------------
	
	//...
		
	@FXML
	private RadioButton sm_cheese;
		
	@FXML
	private RadioButton md_cheese;
		
	@FXML
	private RadioButton lg_cheese;
		
	@FXML
	private RadioButton sm_meat;
		
	@FXML
	private RadioButton md_meat;
		
	@FXML
	private RadioButton lg_meat;
		
	@FXML
	private RadioButton sm_veggie;
		
	@FXML
	private RadioButton md_veggie;
		
	@FXML
	private RadioButton lg_veggie;
	
	@FXML
	private Button add_pizzas;
		
	// 1. Way to create order if needed
	// 2. When created, use radio buttons to add objects to the database (after order is complete)
	
	Order order = new Order();                                               // How can this be passed between frames?
	double orderTotal = order.getOrderTotal();
	
	public void addPizzas() {
		//order = new Order();                                   // Initialize this!! Also, may move to change_payment
		//private double orderTotal;
			
		if(sm_cheese.isSelected() || md_cheese.isSelected() || lg_cheese.isSelected() ||
				sm_meat.isSelected() || md_meat.isSelected() || lg_meat.isSelected() ||
				sm_veggie.isSelected() || md_veggie.isSelected() || lg_veggie.isSelected())
		{
			if(sm_cheese.isSelected() || md_cheese.isSelected() || lg_cheese.isSelected())
			{
				// This is the case in which any button for a cheese pizza is selected
				// An order will be created
				if(sm_cheese.isSelected())
				{
					Pizza pizza1 = new Pizza("Cheese", "Small");      // May not work as "Small" isn't handled explicitly
					//orderTotal += 7.99;
					order.addItem(pizza1);
					System.out.println("Pizza 1 has been added!");

					}
				else if(md_cheese.isSelected())
				{
					Pizza pizza1 = new Pizza("Cheese", "Medium");
					//orderTotal += 9.99;
					order.addItem(pizza1);
					System.out.println("Pizza 1 has been added!");

				}
				else if(lg_cheese.isSelected())
				{
					Pizza pizza1 = new Pizza("Cheese", "Large");
					//orderTotal += 11.99;
					order.addItem(pizza1);
					System.out.println("Pizza 1 has been added!");
				}
				
			}
			
			if(sm_meat.isSelected() || md_meat.isSelected() || lg_meat.isSelected())
			{
				if(sm_meat.isSelected())
				{
					Pizza pizza2 = new Pizza("Meat", "Small");
					//orderTotal += 9.99;
					order.addItem(pizza2);
					System.out.println("Pizza 2 has been added!");

				}
				else if(md_meat.isSelected())
				{
					Pizza pizza2 = new Pizza("Meat", "Medium");
					//orderTotal += 14.99;
					order.addItem(pizza2);
					System.out.println("Pizza 2 has been added!");

				}
				else if(lg_meat.isSelected())
				{
					Pizza pizza2 = new Pizza("Meat", "Large");
					//orderTotal += 19.99;
					order.addItem(pizza2);
					System.out.println("Pizza 2 has been added!");

				}
			}
			
			if(sm_veggie.isSelected() || md_veggie.isSelected() || lg_veggie.isSelected())
			{
				if(sm_veggie.isSelected())
				{
					Pizza pizza3 = new Pizza("Veggie", "Small");
					//orderTotal += 9.99;
					order.addItem(pizza3);
					System.out.println("Pizza 3 has been added!");

				}
				else if(md_veggie.isSelected())
				{
					Pizza pizza3 = new Pizza("Veggie", "Medium");
					//orderTotal += 14.99;
					order.addItem(pizza3);
					System.out.println("Pizza 3 has been added!");

				}
				else if(lg_veggie.isSelected())
				{
					Pizza pizza3 = new Pizza("Veggie", "Large");
					//orderTotal += 19.99;
					order.addItem(pizza3);
					System.out.println("Pizza 3 has been added!");

				}
			}
			// Pizzas should be added now
			System.out.println("Pizzas have been added!");
			System.out.println("Order total so far is " + orderTotal + ".");


		}
		else /*no buttons are selected */
		{
			ShowMessage("Order not created as no pizzas were chosen!");
		}
	}
		

		
		
//------------------------------------------------- Place Order Frame end ---------------------------------------------------------------
//------------------------------------------------- Payment Frame start ---------------------------------------------------------------

	@FXML
	private RadioButton ot_takeout;
		
	@FXML
	private RadioButton ot_inHouse;
		
	@FXML
	private RadioButton ot_delivery;
		
	@FXML
	private RadioButton pt_cash;
		
	@FXML
	private RadioButton pt_card;
	
	@FXML
	private TextField txt_order_num;
	
	@FXML
	private TextField txt_cust_name;
	
	@FXML
	private TextField txt_order_total;
	
	@FXML
	private Button add_order;
	
	// NOTE: This comes after the Place Order frame and will complete the order and send to the DB
	
	// Same order handled as within the Place Order frame
	public void changeOrderType()
	{
		//double orderTotal;
		System.out.println("Order total in when type is chosen: " + orderTotal);

		

		if(ot_takeout.isSelected())
		{
			order.setOrderType("Takeout");
			System.out.println("Set order type as takeout!");

		}
		else if(ot_inHouse.isSelected())
		{
			order.setOrderType("InHouse");
			System.out.println("Set order type as in-house!");

		}
		else if(ot_delivery.isSelected())
		{
			order.setOrderType("Delivery");
			System.out.println("Set order type as delivery!");

		}
		System.out.println("Order total after type is chosen: " + orderTotal);

		
		orderTotal = order.calculateTotal(order);
		txt_order_total.setText(String.valueOf(order.getOrderTotal()));      // Displays the total after order type is chosen
		txt_order_num.setText(String.valueOf(order.getOrderNumber()));       // Should display the total to the frame

	}
	
	public void changePaymentType()
	{
		if(pt_cash.isSelected() || pt_card.isSelected())
		{
			if(pt_card.isSelected())
			{
//				CreditCard card = new CreditCard();
//				card.validate();                                                // Technically useless in the scale of our project.
				System.out.println("Credit card is valid!");

			}
		}
		System.out.println("Payment chosen!");

	}
	
	public void addOrderToDatabase() {                                          // Long header, may be changed

		// It is assumed that the customer's name will not matter until the order is pushed into the database.
		
		if( (ot_takeout.isSelected() || ot_inHouse.isSelected() || ot_delivery.isSelected()) && 
				(pt_cash.isSelected() || pt_card.isSelected()) && (!(txt_cust_name.getText().isEmpty())) )
		{
			// Add to database

			Connection con = DatabaseConnection.getConnection();
			String sql = "insert into orders (Order_Number, Customer_Name, Order_Total, Order_Type, Order_Date) "
					+ "values (?,?,?,?,?);";
			
			try {
				PreparedStatement ps;
				ps = con.prepareStatement(sql);
				ps.setString(1, String.valueOf(order.getOrderNumber()) );
				ps.setString(2, txt_cust_name.getText() );
				ps.setString(3, String.valueOf(order.getOrderTotal()) );
				ps.setString(4, String.valueOf(order.getOrderType()) );
				ps.setString(5, String.valueOf(order.getOrderDate()) );

				ShowMessage("Order Placed!" + "/n" + order.printBill(order));         // Inserts into DB and prints result
				
				
			}
			catch(Exception e)
			{
				ShowMessage("Order failed!!!");
			}
			

		}
		else
		{
			ShowMessage("Please ensure all fields are complete!");
		}

	}
	
//------------------------------------------------- Payment Frame end ---------------------------------------------------------------
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
