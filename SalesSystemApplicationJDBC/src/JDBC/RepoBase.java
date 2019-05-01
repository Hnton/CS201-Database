package JDBC;

/**
 * RepoBase is a base class implementing common Repository API methods that don't require something
 * unique about their class type. It is a generic and must by typed with a class that inherits from 
 * EntityBase.
 * RepoBase implements the following methods:
 * <ul>
 * <li>count: return an integer representing how many records there are in the datastore</li>
 * <li>addCollection: add a group of objects to the datastore</li>
 * <li>updateCollection: update a group of objects already in the datastore</li>
 * <li>deleteCollection: delete a group of objects already in the datastore</li>
 * <li>getSchema</li>
 * <li>setSchema</li>
 * <li>getTable</li>
 * <li>setTable</li>
 * </ul>
 */

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public abstract class RepoBase<T extends EntityBase>
{
	protected Connection conn = JDBCUtilities.getConnection();
	protected String schema = "";
	protected String table = "";
	
	/**
	 * @return the number of records in the datastore
	 */
	public int count()
	{
		String sql = "select count(*) from " + getSchema() + getTable() + ";";
		Statement statement = null;
		ResultSet rs = null;			
		int count = 0;
		
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			rs.next();
			if (rs != null)
			{
				count = rs.getInt(1);
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return count;
	}
	
	public abstract T get(int ID);

	public abstract T getFirst();

	public abstract Collection<?> getAll();
	
	public abstract Collection<?> getCollection(String sql);
	
	public abstract int add(T eb);
	
	public abstract boolean update(T eb);
	
	public abstract boolean delete(T eb);
	
	/**
	 * Add a collection of objects to the datastore as new records
	 *   This is accomplished by looping over each record and calling
	 *   the specific class's add method as defined in the Repository interface
	 * @param addList
	 * @return 0 if all records are added, or the number of unsuccessful adds
	 */
	public int addCollection(Collection<T> addList)
	{
		int elementsProcessed = 0;
		
		for (Object o : addList)
		{
			T element = (T)o;
			if (add(element) > 0)
			{
				elementsProcessed++;
			}
		}

		return (Math.abs(addList.size() - elementsProcessed));
	}
	
	/**
	 * Update a collection of existing objects in the datastore
	 *   This is accomplished by looping over each record and calling
	 *   the specific class's update method as defined in the Repository interface
	 * @param updateList
	 * @return
	 */
	public int updateCollection(Collection<T> updateList)
	{
		int elementsProcessed = 0;
		
		for (Object o : updateList)
		{
			T element = (T)o;
			if (update(element))
			{
				elementsProcessed++;
			}
		}
		return updateList.size() - elementsProcessed;
	}

	/**
	 * Delete a collection of existing objects in the datastore
	 *   This is accomplished by looping over each record and calling
	 *   the specific class's delete method as defined in the Repository interface
	 * @param updateList
	 * @return
	 */
	public int deleteCollection(Collection<T> deleteList)
	{
		int elementsProcessed = 0;

		for (Object o : deleteList)
		{
			T element = (T)o;
			if (delete(element))
			{
				elementsProcessed++;
			}
		}
		return (deleteList.size() - elementsProcessed);
	}
	
	public abstract Collection<?> search(String term);
	
	/**
	 * @return the name of the schema
	 */
	public String getSchema()
	{
		return schema;
	}

	/**
	 * @param schema the schema name to set
	 */
	public void setSchema(String schema)
	{
		if (schema.length() == 0)
		{
			this.schema = "";
		}
		else
		{
			if (schema.charAt(schema.length() - 1) != '.')
			{
				this.schema = schema + ".";
			}
			else
			{
				this.schema = schema;
			}
		}
	}

	/**
	 * @return the name of the table
	 */
	public String getTable()
	{
		return table;
	}

	/**
	 * @param table the table name to set
	 */
	public void setTable(String table)
	{
		this.table = table;
	}
}