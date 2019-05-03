package JDBC.Objects;

import JDBC.EntityBase;

/**
 * Address is a basic class representing a street address
 * @author calmond
 */

public class Person extends EntityBase
{
	
	private int AddressID = 0;
	private String FirstName = "";
	private String LastName = "";
	private String Phone = "";

	/**
	 * Default constructor
	 */
	public Person()
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
	public Person(int AddressID , String FirstName, String LastName, String Phone)
	{
		// ID is not handled because we don't know if it is in the DB yet
		setAddressID(AddressID);
		setFirstName(FirstName);
		setLastName(LastName);
		setPhone(Phone);
	}
	
	/**
	 * @return the PersonID
	 */
	public int getAddressID()
	{
		return AddressID;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setAddressID(int AddressID)
	{
		this.AddressID = AddressID;
	}
	
	/**
	 * @return the state
	 */
	public String getFirstName()
	{
		return FirstName;
	}
	
	/**
	 * State must be a 2 character string.  It will be automatically capitalized
	 * @param state the state to set
	 */
	public void setFirstName(String FirstName)
	{
		this.FirstName = FirstName;
	}
	
	/**
	 * @return the state
	 */
	public String getLastName()
	{
		return LastName;
	}
	
	/**
	 * State must be a 2 character string.  It will be automatically capitalized
	 * @param state the state to set
	 */
	public void setLastName(String LastName)
	{
		this.LastName = LastName;
	}
	
	/**
	 * @return the state
	 */
	public String getPhone()
	{
		return Phone;
	}
	
	/**
	 * State must be a 2 character string.  It will be automatically capitalized
	 * @param state the state to set
	 */
	public void setPhone(String Phone)
	{
			this.Phone = Phone;
	}
	
	/* (non-Javadoc)
	 * Eclipse auto-generated toString
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Person [ID=%s, AddressID=%s, FirstName=%s, LastName=%s, Phone=%s]\n", getID(), AddressID, FirstName, LastName, Phone);
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
		
		Person other = (Person) obj;
		if (AddressID != other.AddressID)
		{
			return false;
		}
		if (!FirstName.equals(other.FirstName))
		{
			return false;
		}
		if (!LastName.equals(other.LastName))
		{
			return false;
		}
		if (!Phone.equals(other.Phone))
		{
			return false;
		}
		return true;
	}
}
