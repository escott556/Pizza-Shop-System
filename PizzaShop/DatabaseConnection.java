/**
 * Author: Bhakti Patel
 * Topic: Semester Project: PizzaShop CS 340
 * Date Created: 03/10/2023
 * Last Edited: 03/18/2023
 */

package application;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {

	public static Connection getConnection() {
		try {

			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PizzaShop", "root", "Bhakti@30");
			System.out.println("Success");
			return con;
		} catch (Exception e) {
			return null;
		}
	}

}
