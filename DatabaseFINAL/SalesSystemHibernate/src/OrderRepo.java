/**
 * A repository object for managing CRUD operations for Address objects
 */

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 * The AddressRepo extends RepoBase and is typed to an Address object.  
 * It provides an entire API to create, read, update, and delete addresses from the datastore 
 * @author calmond
 *
 */

//https://www.mkyong.com/hibernate/hibernate-query-examples-hql/
@SuppressWarnings("deprecation")
public class OrderRepo extends RepoBase<Order> implements Repository<Order>
{
	/**
	 * Default constructor.  It sets the Schema to Person. and the Table to Address for the repository
	 */
	public OrderRepo()
	{
		this.setSchema("Order");
		this.setTable("Order");
	}
	
	/**
	 * get will retrieve an address from the datastore with the given ID,
	 *  or return null if not found
	 * @return the Address record with the given ID, or null if not found
	 */
	public Order get(int ID)
	{
		Order getOrder = new Order();
		
		try
		{
			getOrder = (Order) session.get(Order.class, ID);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return getOrder;
	}
	
	/**
	 * getFirst will return the first Order record out of the datastore, 
	 *  or null if there are no records 
	 * @return the first Order record in the database
	 */
	public Order getFirst()
	{
		Order Order = new Order();
		
		try
		{
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("from Order order by ID");
			@SuppressWarnings("rawtypes")
			List list = query.list();
			
			if (list.size() > 0)
			{
				Order = (Order) list.remove(0);				
			}
			else
			{
				Order = null;
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return Order;
	}
	
	/**
	 * getCollection will retrieve a group of Orderes that match a given HQL statement
	 *   Any valid where clause can be passed. 
	 *   <br/>
	 *   Examples (see search documentation also):
	 *   String matching: from Order where Order like '%1600%'
	 *   Zip code: where from Order where Zip > 33333
	 * @return ArrayList with records matching the hql query
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Order> getCollection(String hql)
	{	
		ArrayList<Order> list = new ArrayList<Order>();
		try
		{
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			list = new ArrayList<Order>(query.list());
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return list;
	}

	/**
	 * Add an Order record to the datastore.
	 * This uses the session saveOrUpdate method to create a new record or update an existing record 
	 * @return The primary key assigned by the database or -1 if the insert was unsuccessful 
	 */
	@Override
	public int add(Order addr)
	{
		int OrderID = -1;
		
		try
		{
			session.saveOrUpdate(addr);
			transaction.commit();
			
			if (addr.getID() > 0)
			{
				OrderID = addr.getID();
			}
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return OrderID;
	}
	
	/**
	 * Update an existing Order record in the datastore.
	 *   This uses the session saveOrUpdate method to create a new record or update an existing record 
	 *   update SQL string and execute it
	 * @return true if successful, false if not
	 */
	@Override
	public boolean update(Order addr)
	{
		try
		{
			session.saveOrUpdate(addr);
			transaction.commit();
			return true;
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete an Order record from the datastore.
	 * Foreign Key constraints with the Person table may cause this to fail
	 * if the person record isn't deleted first
	 * @return true if successful, false if not
	 */
	@Override
	public boolean delete(Order addr)
	{
		// None of the others needed this, but delete did
//		transaction.begin();
		try
		{
			session.delete(addr);
			transaction.commit();
			return true;
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return false;		
	}
	
	/**
	 * search will do an HQL lookup matching an Order record with the where clause provided.
	 * Calls getCollection to retrieve results, and returns an ArrayList of the Order instances
	 *   that match.
	 * <br/>
	 *   Examples:
	 *   String matching: where Order like '%1600%'
	 *   Zip code: where Zip > 33333
	 * @return ArrayList with records matching the sql where clause parameter
	 */
	public ArrayList<Order> search(String term)
	{
		ArrayList<Order> Orderes = new ArrayList<Order>();
		// no select in HQL.  from <Object> where <class field> is syntax
		String hql = "from Order " + term;		
		// Calling getCollection with the above SQL
		Orderes = new ArrayList<Order>( getCollection(hql) );
		
		return Orderes;
	}
}