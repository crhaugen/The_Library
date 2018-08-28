package crHaugen;


/**
 * @author crhaugen
 *
 */

import java.math.BigDecimal;

public class Patron {
	


	private	String cardNumber = " ";
	private	BigDecimal fees = new BigDecimal(0.0);
	private	String firstName = " ";
	private	String lastName = " ";
	
	/*
	 *  @param firstName
	 *  @param lastName
	 *  @param cardNumber
	 *  @param fees
	 */
		
	public Patron(String firstName, String lastName, String cardNumber, BigDecimal fees)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.cardNumber = cardNumber;
		this.fees = fees;
	
	}
	
	/*
	 *@return the card number
	 */
	public String getCardNumber() 
	{
		return cardNumber;
	}
	
	/*
	 * @param cardNumber sets card number
	 */
	public void setCardNumber(String cardNumber) 
	{
		this.cardNumber = cardNumber;
	}
	
	/*
	 *@return the fees
	 */
	public BigDecimal getFees() 
	{
		return fees;
	}
	
	/*
	 * @param fees sets the fees
	 */

	public void setFees(BigDecimal fees) 
	{
		this.fees = fees;
	}
	/*
	 *@return the first name
	 */

	public String getFirstName() 
	{
		return firstName;
	}
	/*
	 * @param firstName set the first name
	 */

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
	/*
	 *@return the last name
	 */
	public String getLastName() 
	{
		return lastName;
	}
	
	/*
	 * @param lastName sets the last name
	 */
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
    //seeing if user owns fees
	public boolean hasFees()
	{
		boolean hasFees = false;
		
		if(fees.compareTo(BigDecimal.ZERO) > 0)
		{
			hasFees = true;
		}
		
		return hasFees;
	}
	
	@Override
	public String toString() {
		return "Patron [cardNumber=" + cardNumber + ", fees=" + fees + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((fees == null) ? 0 : fees.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patron other = (Patron) obj;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (fees == null) {
			if (other.fees != null)
				return false;
		} else if (!fees.equals(other.fees))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	

}
