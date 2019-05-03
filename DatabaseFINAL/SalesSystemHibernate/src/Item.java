
/**
 * Address is a basic class representing a street address
 * @author calmond
 */

public class Item extends EntityBase
{
	private String name = "";
	private String description = "";
	private int inventory = 0;
	private double price = 0.00;

	/**
	 * Default constructor
	 */
	public Item()
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
	public Item(String name, String description, int inventory, double price)
	{
		// ID is not handled because we don't know if it is in the DB yet
		setName(name);
		setDescription(description);
		setInventory(inventory);
		setPrice(price);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("Item [ID=%s, name=%s, description=%s, inventory=%s, price=%s]", getID(), name, description, inventory, price);
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
		if (!(obj instanceof Item))
		{
			return false;
		}
		
		Item other = (Item) obj;
		if (!name.equals(other.name))
		{
			return false;
		}
		if (!description.equals(other.description))
		{
			return false;
		}
		if (inventory != other.inventory)
		{
			return false;
		}
		if (price != other.price)
		{
			return false;
		}
		return true;
	}
}
