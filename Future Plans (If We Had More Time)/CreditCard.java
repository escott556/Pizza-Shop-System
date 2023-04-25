/**********************************************************************************************************************
 * Author:                   Ethan Scott
 * Date Created:             3/15/2023
 * Last Editted:             3/25/2023
 * 
 * Purpose:                  The purpose of this object class is to instantiate a credit card object that represents
 *                           the customer's credit card they will be using to pay for their order. Within this class are
 *                           several variables related to the credit card, constructors, as well as a method to validate
 *                           the credit card (presumably with a bank).
 *                           
 **********************************************************************************************************************/
package StructuralClasses;

public class CreditCard extends Customer
{

	// Variables
	
	private String nameOnCard;
	private String cardNumber;
	private String bank;
	private String cardType;
	private String expirationDate;
	
	// Constructors
	
	public CreditCard() {}
	
	public CreditCard(String nameOnCard, String cardNumber, String bank, String cardType, String expirationDate) 
	{
		super();
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.bank = bank;
		this.cardType = cardType;
		this.expirationDate = expirationDate;
	}
	
	// Methods
	
	// Validate
	
	/**
	 * This method 'validates' the credit card of the customer. It is a stub method as it can be assumed that
	 * this functionality would be passed onto another system/API/entity.
	 * 
	 * @return - Always true, assuming the aforementioned.
	 */
	public boolean validate()
	{
		return true;
	}

	// Getters and setters
	
	// Name on Card
	
	public String getNameOnCard() 
	{
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) 
	{
		this.nameOnCard = nameOnCard;
	}

	// Card Number
	
	public String getCardNumber() 
	{
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) 
	{
		this.cardNumber = cardNumber;
	}
	
	// Bank

	public String getBank() 
	{
		return bank;
	}

	public void setBank(String bank) 
	{
		this.bank = bank;
	}
	
	// Card Type

	public String getCardType() 
	{
		return cardType;
	}

	public void setCardType(String cardType) 
	{
		this.cardType = cardType;
	}
	
	// Expiration Date

	public String getExpirationDate() 
	{
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) 
	{
		this.expirationDate = expirationDate;
	}

	// To String
	
	@Override
	public String toString() {
		return "CreditCard [nameOnCard=" + nameOnCard + ", cardNumber=" + cardNumber + ", bank=" + bank + ", cardType="
				+ cardType + ", expirationDate=" + expirationDate + "]";
	}	
	

}
