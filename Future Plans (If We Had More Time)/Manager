/************************************************************************************************************
 * Author:                     Ethan Scott
 * Date Created:               3/4/2023
 * Last Editted:               3/19/2023
 * 
 * Purpose:                    This is the object class of the Manager object, which serves the purpose of
 *                             representing the manager within the system. It should also be noted that
 *                             the manager is considered an employee, and thus extends it. Included are a
 *                             variable for the manager's password, constructors, and various methods to
 *                             access and modify information.
 **************************************************************************************************************/
package StructuralClasses;

public class Manager extends Employee
{

	// Variables
	
	private String managerPassword;
	
	// Constructors
	
	public Manager() {}
	
	public Manager(String managerPassword)
	{
		super();
		this.managerPassword = managerPassword;
		
	}

	// Methods 
	
	public String getEmployeeInfo(Waitstaff waitstaff)
	{
		// Code stuff, may need to be changed
		return waitstaff.toString();
		
		// edge cases?
	
	}
	
	public String getSalesInfo(Sales sales)
	{
		// Code stuff
		
		return null;
	}
	
	public String getReport(Sales sales)
	{
		// Code stuff
		// May need to omit the getSalesInfo method as they seem the same
		
		return null;
	}
	
	public String getReport(/* revenue, typeOfRevenue ? */)
	{
		// Code stuff
		// Revise
		
		return null;
	}
	
	public String getReport(/* marginalRevenue ? */)
	{
		// Code stuff
		// Revise
		
		return null;
	}
	
	public String getVehicleInfo(DeliveryVehicle vehicle)
	{
		// Code stuff, edge cases?
		return vehicle.toString();
		
	}
	
	// Getters and Setters
	
	// Manager Password
	
	public String getManagerPassword() 
	{
		return managerPassword;
	}

	public void setManagerPassword(String managerPassword) 
	{
		this.managerPassword = managerPassword;
	}

	// To String
	
	@Override
	public String toString() {
		return "Manager [managerPassword=" + managerPassword + "]";
	}
	

	
	
}
