/************************************************************************************************************************
 * Author:                   Ethan Scott
 * Date Created:             3/4/2023
 * Last Editted:             3/25/2023
 * 
 * Purpose:                  The purpose of this class is to create an instantiation of a Pizza ...
 *                           
 ************************************************************************************************************************/
package application;

import java.util.Arrays;

public class Pizza
{

	// Variables
	
	private String type;                            // Cheese, Veggie Lover's, Meat Lover's
	private String size;
	private double cost;
	
	// Constructors
	
	public Pizza() {}
	
	public Pizza(String type, String size)         // Will this be passed using the Place Order buttons in UI?
	{
		super();
		this.type = type;
		this.size = size;
	}
	
	// Methods
	
	/**
	 * This calculates the price of each individual pizza object based on the pizza's type and size.
	 * @param pizza
	 * @return
	 */
	public double calculateCost(Pizza pizza)
	{
		this.cost = 00.00;
		
		if(pizza.getType().equals("Cheese"))                                         // A cheese pizza
		{
			cost = 7.99;
			
			if(pizza.getSize().equals("Medium"))                                     // Medium, + 2.00
			{
				cost += 2.00;
			}
			else if(pizza.getSize().equals("Large"))                                 // Large, + 4.00
			{
				cost += 4.00;
			}
		}
		
		else if(pizza.getType().equals("Veggie") || pizza.getType().equals("Meat") ) // A veggie or meat lover's pizza.
		{
			cost = 9.99;
			
			if(pizza.getSize().equals("Medium"))                                     // Medium, + 5.00
			{
				cost += 5.00;
			}
			else if(pizza.getSize().equals("Large"))                                 // Large, + 10.00
			{
				cost += 10.00;
			}
		}
		
		return cost;
	}
	
	// Type
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	// Size
	
	public String getSize() 
	{
		return size;
	}
	
	public void setSize(String size) 
	{
		this.size = size;
	}
	
	// Cost
	
	public double getCost() 
	{
		return cost;
	}
	
	public void setCost(double cost) 
	{
		this.cost = cost;
	}

	// To String
	
	@Override
	public String toString() {
		return "Pizza [type=" + type + ", size=" + size + ", cost=" + cost
				+ "]";
	}
	
}
