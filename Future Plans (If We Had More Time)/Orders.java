/************************************************************************************************************************
 * Author:                   Ethan Scott
 * Date Created:             3/4/2023
 * Last Editted:             3/25/2023
 * 
 * Purpose:                  The purpose of this class is to create a collection of the orders created from
 *                           the orders class. This class serves more as a record that will be interacting
 *                           with the database rather than an typical object.
 *                  
 ***********************************************************************************************************************/
package StructuralClasses;

public class Orders extends Customer
{
	// Variables
	
	Order order;
	
	// Constructors
	
	public Orders() {}
	
	public Orders(Order order) 
	{
		super();
		this.order = order;
	}
	
	// Methods
	
	public boolean addOrder(Order order)
	{
		// Code stuff
		
		return false;
	}
	
	public boolean toSales()
	{
		// What does this do?
		
		return false;
	}
	
//	public boolean toCustomer()
//	{
//		// OMIT... this is a misunderstanding of the collection class Orders
//		
//		return false;
//	}

	// Getters and Setters
	
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
		return "Orders [order=" + order + "]";
	}

}
