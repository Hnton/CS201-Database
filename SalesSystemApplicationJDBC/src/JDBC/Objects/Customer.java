package JDBC.Objects;

import JDBC.EntityBase;

/**
 * Address is a basic class representing a street address
 * @author calmond
 */

public class Customer extends EntityBase
{
	
	private int PersonID = 0;
	private int SalesRepID = 0;

	/**
	 * Default constructor
	 */
	public Customer()
	{
		
	}
	
	/**
	 * Overloaded constructor with parameters for everything except ID, which will come from the DB
	 *    This can still be set using the setter as part of the call by chaining the setter like this:
	 *    Address address = new Address("123 main street", "Anytown", "Anystate", 12345).setID(1);
	 * Be sure to only assign a value that has come from the DB though
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 */
	public Customer(int PersonID , int SalesRepID)
	{
		// ID is not handled because we don't know if it is in the DB yet
		setPersonID(PersonID);
		setSalesRepID(SalesRepID);
	}
	
	/**
	 * @return the PersonID
	 */
	public int getPersonID()
	{
		return PersonID;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setPersonID(int PersonID)
	{
		this.PersonID = PersonID;
	}
	
	/**
	 * @return the state
	 */
	public int getSalesRepID()
	{
		return SalesRepID;
	}
	
	/**
	 * State must be a 2 character string.  It will be automatically capitalized
	 * @param state the state to set
	 */
	public void setSalesRepID(int SalesRepID)
	{
		this.SalesRepID = SalesRepID;
	}
	
	/* (non-Javadoc)
	 * Eclipse auto-generated toString
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Customer [ID=%s, PersonID=%s, SalesRepID=%s]", getID(), PersonID, SalesRepID);
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Customer))
		{
			return false;
		}
		
		Customer other = (Customer) obj;
		if (PersonID != other.PersonID)
		{
			return false;
		}
		if (SalesRepID != other.SalesRepID)
		{
			return false;
		}
		return true;
	}
}
