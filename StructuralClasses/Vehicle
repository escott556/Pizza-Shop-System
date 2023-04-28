/************************************************************************************************************
 * Author:                    Bhakti Patel
 * Date Created:              3/1/2023
 * Last Edited:               3/23/2023
 * 
 * Purpose:                   This is the object class that serves the purpose of instantiating a DeliveryVehicle
 *                            object that represents the delivery vehicle associated with the Pizza Shop. Included
 *                            are a variable to indicate a vehicle's ID number, constructors, and methods to access
 *                            and modify information.
 */
package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Vehicle class to keep track of delivery vehicle information and who is
 * driving
 * 
 * @author bhaktipatel
 *
 */
public class Vehicle {

	private int vehicleID;
	private String vehicleType;
	private int empID;

	/**
	 * default constructor
	 * 
	 * @param vehicleID
	 * @param vehicleType
	 * @param empID
	 */
	public Vehicle(int vehicleID, String vehicleType, int empID) {
		super();
		this.vehicleID = vehicleID;
		this.vehicleType = vehicleType;
		this.empID = empID;
	}

	//Setters and getters method
	
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
	/**
	 * 
	 * @return the list of the vehicles from the database
	 */
	public static ObservableList<Vehicle> getVehicleInfo() {

		ObservableList<Vehicle> list = FXCollections.observableArrayList();  //creating the list
		Connection con = DatabaseConnection.getConnection();   //connecting to the database
		String sql = "select * from vehicle;";   //Query for retrieving information of the vehicle
		Statement s;
		ResultSet rs;
		try {
			//create statement and execute the statement
			s = con.createStatement();
			rs = s.executeQuery(sql);      
			Vehicle vehicle;

			//creating vehicle objects and adding it to the list
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

	/**
	 * 
	 * @paramFileName
	 * @return if vehicle name is valid or not
	 */
	public static boolean checkVehicleName(String EmpName) {

		Pattern p = Pattern.compile("[a-zA-Z ]+"); // can have a-z, A-Z

		Matcher m = p.matcher(EmpName); // Matches the pattern with the employee name

		return m.matches(); // returns true/false if the pattern matches the employee name or not
	}

}
