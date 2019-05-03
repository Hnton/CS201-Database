import java.util.Date;

/**
 * Address is a basic class representing a street address
 * @author calmond
 */

public class Order extends EntityBase
{
	private int customerID = 0;
	private Date dateOrdered = new Date(000-00-00);
	private short shipped = 0;

	/**
	 * Default constructor
	 */
	public Order()
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
	public Order(int customerID, Date dateOrdered, short shipped)
	{
		// ID is not handled because we don't know if it is in the DB yet
		setCustomerID(customerID);
		setDateOrdered(dateOrdered);
		setShipped(shipped);
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public short getShipped() {
		return shipped;
	}

	public void setShipped(short shipped) {
		this.shipped = shipped;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Order [ID=%s, customerID=%s, dateOrdered=%s, shipped=%s]", getID(), customerID, dateOrdered, shipped);
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
		if (!(obj instanceof Order))
		{
			return false;
		}
		
		Order other = (Order) obj;
		if (customerID != other.customerID)
		{
			return false;
		}
		if (!dateOrdered.equals(other.dateOrdered))
		{
			return false;
		}
		if (shipped != other.shipped)
		{
			return false;
		}
		return true;
	}
}
