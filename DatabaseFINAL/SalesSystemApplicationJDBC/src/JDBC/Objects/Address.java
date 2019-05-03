package JDBC.Objects;

import JDBC.EntityBase;

/**
 * Address is a basic class representing a street address
 * @author calmond
 */

public class Address extends EntityBase
{
	private String address = "";
	private String city = "";
	private String state = "";
	private int zip = 0;

	/**
	 * Default constructor
	 */
	public Address()
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
	public Address(String address, String city, String state, int zip)
	{
		// ID is not handled because we don't know if it is in the DB yet
		setAddress(address);
		setCity(city);
		setState(state);
		setZip(zip);
	}
	
	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	/**
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}
	
	/**
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}
	
	/**
	 * State must be a 2 character string.  It will be automatically capitalized
	 * @param state the state to set
	 */
	public void setState(String state)
	{
		if (state.length() == 2)
		{
			this.state = state.toUpperCase();
		}
		else
		{
			System.err.println("State malformed");
		}
	}
	
	/**
	 * @return the zip
	 */
	public int getZip()
	{
		return zip;
	}
	
	/**
	 * A zip code as an integer between 0 and 99999
	 * @param zip the zip to set
	 */
	public void setZip(int zip)
	{
		if (zip > 0 && zip <= 99999)
		{
			this.zip = zip;
		}
		else
		{
			System.err.println("Zip malformed");
		}
	}
	
	/* (non-Javadoc)
	 * Eclipse auto-generated toString
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Address [ID=%s, address=%s, city=%s, state=%s, zip=%s]\n", getID(), address, city, state, zip);
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
		
		Address other = (Address) obj;
		if (!address.equals(other.address))
		{
			return false;
		}
		if (!city.equals(other.city))
		{
			return false;
		}
		if (!state.equals(other.state))
		{
			return false;
		}
		if (zip != other.zip)
		{
			return false;
		}
		return true;
	}
}
