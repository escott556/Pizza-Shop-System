/**
 * Author: Bhakti Patel
 * Topic: Semester Project: PizzaShop CS 340
 * Date Created: 02/10/2023
 * Last Edited: 03/18/2023
 */

package application;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class VehicleController implements Initializable {

	private Stage stage;
	private Parent root;
	private Scene scene;

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

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public void updateTable() {
		listV = Vehicle.getVehicleInfo();

		col_V_ID.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("vehicleID"));
		col_type.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleType"));
		col_emp_ID.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("empID"));

		table_vehicle.setItems(listV);

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		updateTable();

	}

	public void add_vehicle() {
		con = DatabaseConnection.getConnection();
		String sql = "insert into vehicle (emp_ID,vehicle_type) values (?,?);";
		try {
			ps = con.prepareStatement(sql);
			// ps.setString(1, txt_V_ID.getText());
			ps.setString(1, txt_emp_ID.getText());
			ps.setString(2, txt_VType.getText());
			ps.execute();

			ShowMessage("Vehicle Added!");
			updateTable();
		} catch (Exception e) {

		}
	}

	public void updateVehicle() {
		try {
			con = DatabaseConnection.getConnection();
			String value1 = txt_V_ID.getText();
			String value2 = txt_emp_ID.getText();
			String value3 = txt_VType.getText();

			String sql = "update vehicle set emp_ID = '" + value2 + "', vehicle_type = '" + value3 
					+ "' where vehicle_ID = '" + value1 + "'";
			ps = con.prepareStatement(sql);
			ps.execute();
			ShowMessage("Vehicle Updated!");
			updateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteVehicle() {
		con = DatabaseConnection.getConnection();
		String sql = "delete from vehicle where vehicle_ID = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, txt_V_ID.getText());
			ps.execute();

			ShowMessage("Vehicle Deleted!");
			updateTable();
		} catch (Exception e) {

		}
	}

	@FXML
	void getSelectedV(MouseEvent event) {

		Vehicle clickedV = table_vehicle.getSelectionModel().getSelectedItem();
		txt_V_ID.setText(String.valueOf(clickedV.getVehicleID()));
		txt_emp_ID.setText(String.valueOf(clickedV.getEmpID()));
		txt_VType.setText(String.valueOf(clickedV.getVehicleType()));
	}


	private void ShowMessage(String message) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, message);
			}
		});
	}

}
