/**
	 * Author: Bhakti Patel
	 * Topic: Semester Project: PizzaShop CS 340
	 * Date Created: 03/01/2023
	 * Last Edited: 03/18/2023
	 */
package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vehicle {

	private int vehicleID;
	private String vehicleType;
	private int empID;

	public Vehicle(int vehicleID, String vehicleType, int empID) {
		super();
		this.vehicleID = vehicleID;
		this.vehicleType = vehicleType;
		this.empID = empID;
	}

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleTYPE(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public static ObservableList<Vehicle> getVehicleInfo() {

		ObservableList<Vehicle> list = FXCollections.observableArrayList();
		Connection con = DatabaseConnection.getConnection();
		String sql = "select * from vehicle;";
		Statement s;
		ResultSet rs;
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			Vehicle vehicle;

			while (rs.next()) {
				vehicle = (new Vehicle(rs.getInt("vehicle_ID"), rs.getString("vehicle_type"), rs.getInt("emp_ID")));
				list.add(vehicle);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
