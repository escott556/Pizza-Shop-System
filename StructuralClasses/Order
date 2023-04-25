/************************************************************************************************************************
 * Author:                   Ethan Scott
 * Date Created:             3/4/2023
 * Last Edited:              4/16/2023
 * 
 * Purpose:                  The purpose of this class is to create an instantiation of an order, as well as
 *                           keep track of all of the related information on the order, such as the total cost,
 *                           the type of the order, items involved, and the order number. It includes business
 *                           rules to account for fees and tax.
 *                           
 *************************************************************************************************************************/

package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;

public class Order // extends Orders?
{

	// Variables
	
	private int orderNumber;
	private String customerName;
	protected double orderTotal = 00.00;
	private String orderType;
	private Date orderDate;
	private ArrayList<Pizza> items = new ArrayList<>();                     //DB doesn't care about this, just the total
	
	// Business-Related Variables
	final double TAX = 0.06;
	final double IN_HOUSE_FEE = 0.05;
	final double DELIVERY_FEE = 3.00;
	
	// Constructors

	public Order() {
		orderNumber = (int)(Math.random() * 1000);
		orderDate = new Date();
	}

	public Order(int orderNumber, String customerName, double orderTotal, String orderType, Date orderDate) 
	{
		super();
		this.orderNumber = (int)(Math.random() * 1000);
		this.customerName = customerName;
		//this.pizza = pizza;
		this.orderTotal = orderTotal;
		this.orderType = orderType;
		this.orderDate = new Date();
		
	}
	
	// Methods
	
	/**
	 * This method will add a pizza to the item list of the order.
	 * @param pizza - The pizza that is to be added to the item list.
	 */
	public void addItem(Pizza pizza)
	{
		// Code stuff, edge cases?
		items.add(pizza);
		
	}
	
	/**
	 * This method 'cancels' the order. It clears the items, allowing Java's garbage collection to do the rest.
	 * @return - True as the list of items is cleared.
	 */
	public boolean cancelOrder()
	{
		items.clear();
		return true;
	}

	/**
	 * This method calculates the total of the passed order. In doing so, it finds the total of all of the
	 * pizzas within the order, then sums up all of the costs of each pizza within the order.
	 * 
	 * @param order - The order to be passed through.
	 * @return      - The total cost of the order.
	 */
	public double calculateSubTotal(Order order)
	{
		orderTotal = 0;                                        // Ensures that the price does not add up incorrectly.
		
		for(int i = 0; i < items.size(); i++)                  // Calculates base cost before fee and tax.
		{
			Pizza currentPizza = items.get(i);
			currentPizza.calculateCost(currentPizza);
			orderTotal += currentPizza.getCost();
			System.out.println("PASSED |  Pizza " + (i + 1) + " added with price of " + currentPizza.getCost());
		}

		return round(orderTotal,2);
	}
	
	/**
	 * This method calculates the total of the passed order. In doing so, it finds the total of all of the
	 * pizzas within the order, then applies an in-house or delivery fee as needed, and finally adds the tax
	 * amount.
	 * 
	 * @param order - The order to be passed through.
	 * @return      - The total cost of the order.
	 */
	public double calculateTotal(Order order)                  // Adds any necessary taxes and fees.
	{
		orderTotal = order.calculateSubTotal(order);
		System.out.println("Order total now: " + orderTotal);
	
		if(order.getOrderType().equals("InHouse"))             // In-house fee if needed
		{
			orderTotal += (orderTotal * IN_HOUSE_FEE);
		}
		else if(order.getOrderType().equals("Delivery"))       // Delivery fee if needed
		{
			orderTotal += DELIVERY_FEE;
		}
		
		orderTotal += (orderTotal * TAX);                      // Tax is added
		
		return round(orderTotal,2);
	}
	
	/**
	 * This method prints the bill, acting as a formatted toString method.
	 * @param order - The order being passed
	 * @return      - All related information is returned and printed
	 */
	public String printBill(Order order)
	{
		return "Order: " + orderNumber + "Order Date: " + orderDate + "\n\nItems: " + itemPrinter(items)
				+ "\n\nOrder Total: " + calculateTotal(order);
	}
	
	// Order Number
	
	public int getOrderNumber() 
	{
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) 
	{
		this.orderNumber = orderNumber;
	}

	// Customer Name
	
	public String getCustomerName() 
	{
		return customerName;
	}

	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	// Order Total
	
	public double getOrderTotal() 
	{
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) 
	{
		this.orderTotal = orderTotal;
	}

	// Order Type
	
	public String getOrderType() 
	{
		return orderType;
	}

	public void setOrderType(String orderType) 
	{
		this.orderType = orderType;
	}
	
	// Payment Type
//	public String getPaymentType() 
//	{
//		return orderType;
//	}
//
//	public void setPaymentType(String paymentType) 
//	{
//		this.paymentType = paymentType;
//	}

	// Order Date
	
	public Date getOrderDate() 
	{
		return orderDate;
	}

	public void setOrderDate(Date orderDate) 
	{
		this.orderDate = orderDate;
	}

	// To String and Print Items
	
	@Override
	/**
	 * NOTE: This method is not used.
	 */
	public String toString() 
	{
		return "Order [orderNumber=" + orderNumber + ", orderTotal=" + orderTotal + ", orderType=" + orderType
				+ ", orderDate=" + orderDate + ", items=" + items + "]";
	}
	
	
	/**
	 * This method prints all of the individual items of the items ArrayList to provide for easier readability.
	 * It acts much like a helper method for the printBill method.
	 * 
	 * @param itemList - The list of items (pizzas) that are within the order
	 * @return         - A printed format of all items in the order
	 */
	public String itemPrinter(ArrayList<Pizza> itemList)
	{
		String printList = " ";
		
		for(int i = 0; i < itemList.size(); i++)
		{
			printList += "Item #" + i + ": " + itemList.get(i) + "\t" + (itemList.get(i).getCost());
		}
		
		return printList;
	}
	
	// May not work
	public static ObservableList<Order> getEmployeeInfo() {

		ObservableList<Order> list = FXCollections.observableArrayList();
		Connection con = DatabaseConnection.getConnection();
		String sql = "select * from orders;";
		Statement s;
		ResultSet rs;
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			Order order;

			while (rs.next()) {
				order = (new Order(rs.getInt("Order_Number"), rs.getString("Customer_Name"), 
						rs.getDouble("Order_Total"),rs.getString("Order_Type"), rs.getDate("Order_Date")));
				list.add(order);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);      //assigning factor to user's decimal places and raising it to 10th power
	    value = value * factor;                         //multiplying the value passed with the factor
	    long tmp = Math.round(value);                   
	    return (double) tmp / factor;                  //returning to desired number of places
	}

	
}
