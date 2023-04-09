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

public class Employee {

	private int empID;
	private String empName;
	private String empAdd;
	private String empPhone;

	public void setEmpID() {
		this.empID = empID;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpName() {
		this.empName = empName;
	}

	public void setEmpAdd() {
		this.empAdd = empAdd;
	}

	public void setEmpPhone() {
		this.empPhone = empPhone;
	}

	public String getEmpName() {
		return empName;
	}

	public String getEmpAdd() {
		return empAdd;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public Employee(int empID, String empName, String empAdd, String empPhone) {
		this.empID = empID;
		this.empName = empName;
		this.empAdd = empAdd;
		this.empPhone = empPhone;
	}

	public Employee(String empName) {
		this.empName = empName;
	}

	public static ObservableList<Employee> getEmployeeInfo() {

		ObservableList<Employee> list = FXCollections.observableArrayList();
		Connection con = DatabaseConnection.getConnection();
		String sql = "select * from employee;";
		Statement s;
		ResultSet rs;
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			Employee employee;

			while (rs.next()) {
				employee = (new Employee(rs.getInt("emp_ID"), rs.getString("emp_name"), rs.getString("emp_add"),
						rs.getString("emp_phone")));
				list.add(employee);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}


