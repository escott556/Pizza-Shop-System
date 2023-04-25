/************************************************************************************************************************
 * Author:                   Ethan Scott
 * Date Created:             3/4/2023
 * Last Edited:              3/19/2023
 * 
 * Purpose:                  The purpose of this class is to create an instantiation of a customer within the system.
 *                           Within this class are the appropriate getters and setters for related variables
 *                           (customerName, creditCard, address, order); constructors; methods for payBill, Place Order,
 *                           and getOrderNumber.
 *                           
 */
package StructuralClasses;

public class Customer 
{
	
	// Variables
	private String customerName;
	private CreditCard creditCard = new CreditCard();
	private Address address = new Address();
	private Order order = new Order();
	
	// Constructors
	
	public Customer() {}
	
	public Customer(String name, Address address, Order order)
	{
		this.customerName = name;
		this.address = address;
		this.order = order;
	}
	
	// Pay Bill...?                              *** Fix ***
	
	public boolean payBill(Order order)
	{
		// Code stuff
		if(order.orderTotal > 00.00 && creditCard.validate() == true)                   // Order total and card must be valid
		{
			order.orderTotal = 0;
			return true;
		}
		
		else return false;                                                              // False otherwise
	}
	
	// Place Order                              *** Fix ***
	
	public void placeOrder(Order order)
	{
		this.order = new Order(/* stuff */);
	}
	
	// Get order number
	
	public int getOrderNumber(Order order)
	{
		int orderNumber = order.getOrderNumber();
		return orderNumber;
	}

	// Customer name
	
	public String getCustomerName() 
	{
		return customerName;
	}

	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	// Credit card
	
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) 
	{
		this.creditCard = creditCard;
	}

	// Address
	
	public Address getAddress() 
	{
		return address;
	}

	public void setAddress(Address address) 
	{
		this.address = address;
	}

	// Order
	
	public Order getOrder() 
	{
		return order;
	}

	public void setOrder(Order order) 
	{
		this.order = order;
	}

	// To String
	
	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", creditCard=" + creditCard + ", address=" + address
				+ ", order=" + order + "]";
	}
	
}
