/**
 * EntityBase is the base class for all objects in the application.
 * It provides an ID field to act as the primary key in the database and provides the
 * corresponding getters/setters
 * 
 * It also provides a compareTo for sorting on the PK, but use with caution because if you 
 * compare two different object types their PK may have the same value since they are from 
 * different tables.
 * 
 * @author calmond
 *
 */

public class EntityBase implements Comparable<Object>
{
	private int ID = 0;

	/**
	 * Default constructor.  
	 */
	public EntityBase()
	{
		
	}

	/**
	 * 
	 * @return the ID property (primary key from database)
	 */
	public int getID()
	{
		return ID;
	}
	
	/**
	 * Setter for the ID field.  It is protected so an application outside of the package cannot use it
	 * This should only set the value assigned from the database!  DO NOT just assign an arbitrary value
	 * @param ID the ID to set
	 * @return the instance of EntityBase with the ID set.  This allows chaining the setID(#) method call
	 */
	protected EntityBase setID(int ID)
	{
		if (this.ID == 0 && ID > 0)
		{
			this.ID = ID;
		}
		return this;
	}

	/**
	 * Compares the ID field of different objects
	 */
	@Override
	public int compareTo(Object o)
	{
		EntityBase that = (EntityBase)o;
		int compareValue;
		if (this.ID > that.ID)
		{
			compareValue = 1;
		}
		else if (this.ID < that.ID)
		{
			compareValue = -1;
		}
		else
		{
			compareValue = 0;
		}
		return compareValue;
	}
}