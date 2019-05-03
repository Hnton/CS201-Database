package JDBC.Objects;

import JDBC.EntityBase;

/**
 * Address is a basic class representing a street address
 * @author calmond
 */

public class SalesRep extends EntityBase
{
	private int PersonID = 0;
	private String Region = "";

	/**
	 * Default constructor
	 */
	public SalesRep()
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
	public SalesRep(int PersonID, String Region)
	{
		// ID is not handled because we don't know if it is in the DB yet
		setPersonID(PersonID);
		setRegion(Region);
	}
	
	/**
	 * @return the address
	 */
	public int getPersonID()
	{
		return PersonID;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setPersonID(int PersonID)
	{
		this.PersonID = PersonID;
	}
	
	/**
	 * @return the state
	 */
	public String getRegion()
	{
		return Region;
	}
	
	/**
	 * State must be a 2 character string.  It will be automatically capitalized
	 * @param state the state to set
	 */
	public void setRegion(String Region)
	{

			this.Region = Region.toUpperCase();

	}
	
	/* (non-Javadoc)
	 * Eclipse auto-generated toString
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("SalesRep [ID=%s, PersonID=%s, Region=%s]\n", getID(), PersonID, Region);
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
		if (!(obj instanceof Address))
		{
			return false;
		}
		
		SalesRep other = (SalesRep) obj;
		if (PersonID != other.PersonID)
		{
			return false;
		}
		if (!Region.equals(other.Region))
		{
			return false;
		}
		return true;
	}
}
