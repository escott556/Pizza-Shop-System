/****************************************************************************************************************************
 * Author:                 Ethan Scott
 * Date Created:           4/23/2023
 * Last Editted:           4/24/2023
 * 
 * Purpose:                This is the test class that uses JUnit 4 to test various inputs that may be used when trying
 *                         to place an order within the Pizza Shop System. Though these inputs are never directly
 *                         provided by the user, the UI still passes these inputs after the user selects them with
 *                         choice boxes.
 ****************************************************************************************************************************/

package application;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class Test_Calculations {

	@Test
	public static void testCalculateCost() throws Exception{

		System.out.println("\nTESTING PIZZA PRICES\n");

		// Small Cheese
		try 
		{
			Pizza pizza = new Pizza("Cheese", "Small");
			assertEquals(7.99, pizza.calculateCost(pizza), 0.001);
			System.out.println("SUCCESS | Small Cheese");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE  | Small Cheese");
		}
		
		// Medium Cheese
		try 
		{
			Pizza pizza = new Pizza("Cheese", "Medium");
			assertEquals(9.99, pizza.calculateCost(pizza), 0.001);
			System.out.println("SUCCESS | Medium Cheese");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE  | Medium Cheese");
		}
		
		// Large Cheese
		try 
		{
			Pizza pizza = new Pizza("Cheese", "Large");
			assertEquals(11.99, pizza.calculateCost(pizza), 0.001);
			System.out.println("SUCCESS | Large Cheese");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE  | Large Cheese");
		}
		
		// Small Veggie
		try 
		{
			Pizza pizza = new Pizza("Veggie", "Small");
			assertEquals(9.99, pizza.calculateCost(pizza), 0.001);
			System.out.println("SUCCESS | Small Veggie");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE  | Small Veggie");
		}
		
		// Medium Veggie
		try 
		{
			Pizza pizza = new Pizza("Veggie", "Medium");
			assertEquals(14.99, pizza.calculateCost(pizza), 0.001);
			System.out.println("SUCCESS | Medium Veggie");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE  | Medium Veggie");
		}
		
		// Large Veggie
		try 
		{
			Pizza pizza = new Pizza("Veggie", "Large");
			assertEquals(19.99, pizza.calculateCost(pizza), 0.001);
			System.out.println("SUCCESS | Large Veggie");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE  | Large Veggie");
		}
		
		// Small Meat
		try 
		{
			Pizza pizza = new Pizza("Meat", "Small");
			assertEquals(9.99, pizza.calculateCost(pizza), 0.001);
			System.out.println("SUCCESS | Small Meat");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE  | Small Meat");
		}
		
		// Medium Meat
		try 
		{
			Pizza pizza = new Pizza("Meat", "Medium");
			assertEquals(14.99, pizza.calculateCost(pizza), 0.001);
			System.out.println("SUCCESS | Meat Meat");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE  | Medium Meat");
		}
		
		// Large Meat
		try 
		{
			Pizza pizza = new Pizza("Meat", "Large");
			assertEquals(19.99, pizza.calculateCost(pizza), 0.001);
			System.out.println("SUCCESS | Large Meat");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE  | Large Meat");
		}
		
		System.out.println("COMPLETE| All Exceptions Caught");

		
	}// End of Pizza, testCalculateCost
	
	@Test
	public static void testCalculateSubTotal() throws Exception{
		
		Order order = new Order();
		order.addItem(new Pizza("Veggie", "Small"));
		order.addItem(new Pizza("Cheese", "Medium"));

		System.out.println("\nTESTING SUB TOTAL\n");

		// Off by +00.01
		try 
		{
			assertEquals(19.99, order.calculateSubTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | One cent over");
			
		}
		
		// Off by -00.01
		try 
		{
			assertEquals(19.99, order.calculateSubTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | One cent under");
			
		}
		
		// Off by +1.00
		try 
		{
			assertEquals(20.98, order.calculateSubTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | One dollar over");
			
		}
		
		// Off by -1.00
		try 
		{
			assertEquals(18.98, order.calculateSubTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | One dollar under");
			
		}
		
		// Wrong order total
		try 
		{
			assertEquals(9.99, order.calculateSubTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | Wrong order total");
			
		}
		
		// String instead of double
		try 
		{
			assertEquals("19.98", order.calculateSubTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | String instead of double");
			
		}
		
		// Empty Order
		try 
		{
			assertEquals(00.00, order.calculateSubTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | Empty order");
			
		}
		
		// Correct Sub Total
		try 
		{
			assertEquals(19.98, order.calculateSubTotal(order), 0.001);
			System.out.println("SUCCESS | Sub Total Correct");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE | Something went wrong");
			
		}
		
		System.out.println("COMPLETE| All Exceptions Caught");

		
	}// End of Order, testCalculateSbTotal(Order order)
	
	@Test
	public static void testCalculateTotal() throws Exception{
		
		Order order = new Order();
		order.addItem(new Pizza("Veggie", "Small"));
		order.addItem(new Pizza("Cheese", "Medium"));

		System.out.println("\nTESTING TOTAL\n");

		// InHouse =/= Takeout
		try 
		{
			order.setOrderType("InHouse");
			assertEquals(21.18, order.calculateTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | InHouse =/= Takeout");
			
		}
		
		// InHouse =/= Delivery
		try 
		{
			order.setOrderType("InHouse");
			assertEquals(24.36, order.calculateTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | InHouse =/= Delivery");
			
		}
		
		// Takeout =/= InHouse
		try 
		{
			order.setOrderType("Takeout");
			assertEquals(22.24, order.calculateTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | Takeout =/= InHouse");
			
		}
		
		// Takeout =/= Delivery
		try 
		{
			order.setOrderType("Takeout");
			assertEquals(24.36, order.calculateTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | Takeout =/= Delivery");
			
		}
		
		// Delivery =/= Takeout
		try 
		{
			order.setOrderType("Delivery");
			assertEquals(21.18, order.calculateTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | Delivery =/= Takeout");
			
		}
		
		// Delivery =/= InHouse
		try 
		{
			order.setOrderType("Delivery");
			assertEquals(22.24, order.calculateTotal(order), 0.001);
		}
		catch(AssertionError e)
		{
			System.out.println("CAUGHT  | Delivery =/= InHouse");
			
		}
		
		// InHouse Correct Total
		try 
		{
			order.setOrderType("InHouse");
			assertEquals(22.24, order.calculateTotal(order), 0.001);
			System.out.println("SUCCESS | InHouse Total Correct");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE | InHouse Total Incorrect");
			
		}
		
		// Takeout Correct Total
		try 
		{
			order.setOrderType("Takeout");
			assertEquals(21.18, order.calculateTotal(order), 0.001);
			System.out.println("SUCCESS | Takeout Total Correct");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE | Takeout Total Incorrect");
			
		}
		
		// Delivery Correct Total
		try 
		{
			order.setOrderType("Delivery");
			assertEquals(24.36, order.calculateTotal(order), 0.001);
			System.out.println("SUCCESS | Delivery Total Correct");
		}
		catch(AssertionError e)
		{
			System.out.println("FAILURE | Delivery Total Incorrect");
			
		}
		
		System.out.println("COMPLETE| All Exceptions Caught");

	} // End of Order, testCalculateTotal(Order order)
}
