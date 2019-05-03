
/**
 * Address is a basic class representing a street address
 * @author calmond
 */

public class OrderItem extends EntityBase
{
	private int orderID = 0;
	private int itemID = 0;
	private int quantity = 0;

	/**
	 * Default constructor
	 */
	public OrderItem()
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
	public OrderItem(int orderID, int itemID, int quantity)
	{
		// ID is not handled because we don't know if it is in the DB yet
		setOrderID(orderID);
		setItemID(itemID);
		setQuantity(quantity);
	}
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("OrderItem [ID=%s, orderID=%s, itemID=%s, quantity=%s]", getID(), orderID, orderID, quantity);
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
		if (!(obj instanceof OrderItem))
		{
			return false;
		}
		
		OrderItem other = (OrderItem) obj;
		if (orderID != other.orderID)
		{
			return false;
		}
		if (itemID != other.itemID)
		{
			return false;
		}
		if (quantity != other.quantity)
		{
			return false;
		}
		return true;
	}


}
