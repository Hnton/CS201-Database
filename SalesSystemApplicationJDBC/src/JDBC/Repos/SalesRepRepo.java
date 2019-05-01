package JDBC.Repos;

/**
 * A repository object for managing CRUD operations for Address objects
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import JDBC.RepoBase;
import JDBC.Repository;
import JDBC.Objects.*;

/**
 * The AddressRepo extends RepoBase and is typed to an Address object.  
 * It provides an entire API to create, read, update, and delete addresses from the datastore 
 * @author calmond
 *
 */
public class SalesRepRepo extends RepoBase<SalesRep> implements Repository<SalesRep>
{
	/**
	 * Default constructor.  It sets the Schema to Person. and the Table to Address for the repository
	 */
	public SalesRepRepo()
	{
		this.setSchema("Person");
		this.setTable("SalesRep");
	}
	
	/**
	 * get will retrieve an address from the datastore with the given ID,
	 *  or return null if not found
	 * @return the Address record with the given ID, or null if not found
	 */
	public SalesRep get(int ID)
	{
		Statement statement = null;
		ResultSet rs = null;
		SalesRep SalesRep = new SalesRep();
		
		try
		{
			String sql = "select * from " + schema + table + " where ID = " + ID + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			if (rs != null)
			{
				rs.next();				
				SalesRep.setID(ID);
				SalesRep.setPersonID(rs.getInt("PersonID"));
				SalesRep.setRegion(rs.getString("Region"));
				return SalesRep;
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return null;
	}
	
	/**
	 * getFirst will return the first Address record out of the datastore, 
	 *  or null if there are no records 
	 * @return the first address record in the database
	 */
	public SalesRep getFirst()
	{
		Statement statement = null;
		ResultSet rs = null;
		SalesRep SalesRep = new SalesRep();
		
		try
		{
			// top 1 is T-SQL specific
			String sql = "select top 1 * from " + getSchema() + getTable() + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			rs.next();
			SalesRep.setID(rs.getInt("ID"));
			SalesRep.setPersonID(rs.getInt("PersonID"));
			SalesRep.setRegion(rs.getString("Region"));
			return SalesRep;
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return null;
	}
	
	/**
	 * Retrieves all records from the datastore in one ArrayList
	 * getAll is not a safe call on very large record sets and should be removed from the 
	 *   interface/implementation if dealing with very large sets of data
	 * @return All Address records out of the datastore in an ArrayList, or an empty ArrayList if there are no records
	 */
	public ArrayList<SalesRep> getAll()
	{
		ArrayList<SalesRep> list = new ArrayList<SalesRep>();
		Statement statement = null;
		ResultSet rs = null;
		
		try
		{
			String sql = "select * from " + getSchema() + getTable() + ";";
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
			{
				// Adding a new Address object to the list from the resultset data
				list.add((SalesRep) new SalesRep(rs.getInt("PersonID"), 
												 rs.getString("Region"))
												   .setID(rs.getInt("ID")));				
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return list;
	}	

	/**
	 * Add an address record to the datastore.
	 * This uses the getters of the addr parameter to build an 
	 *   insert SQL string and execute it
	 * @return The primary key assigned by the database or -1 if the insert was unsuccessful 
	 */
	@Override
	public int add(SalesRep SalesRep)
	{
		try
		{
			int primaryKey = 0;
			String sql = "INSERT INTO " + getSchema() + getTable();
			sql += " (PersonID , Region) VALUES(";
			sql += SalesRep.getPersonID() + ", '" + SalesRep.getRegion() + "');";
			
			Statement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = null;
			statement.execute(sql, Statement.RETURN_GENERATED_KEYS);
			rs = statement.getGeneratedKeys();
			rs.next();
			primaryKey = rs.getInt(1);
			return primaryKey; // return the new PK
		} 
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	 * Update an existing address record in the datastore.
	 *   This uses the getters of the addr parameter to build an 
	 *   update SQL string and execute it
	 * @return true if successful, false if not
	 */
	@Override
	public boolean update(SalesRep SalesRep)
	{
		try
		{
			Statement statement = conn.createStatement();
			String sql = "UPDATE " + getSchema() + getTable() + " SET ";
			sql += "PersonID = '" + SalesRep.getPersonID();
			sql += "', Region = '" + SalesRep.getRegion();
			sql += "' where ID = " + SalesRep.getID()  + ";";
			statement.execute(sql);
			return true;
		} 
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * Delete an address record from the datastore.
	 * Foreign Key constraints with the Person table may cause this to fail
	 * if the person record isn't deleted first
	 * @return true if successful, false if not
	 */
	@Override
	public boolean delete(SalesRep SalesRep)
	{
		try
		{
			// Using a concurrent update ResultSet
			Statement statement = conn.createStatement();
			String sql = "DELETE FROM Person.SalesRep where ID=" + SalesRep.getID() + ";";
			statement.execute(sql);
			return true;
		} 
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return false;		
	}
	
	/**
	 * getCollection will retrieve a group of Addresses that match a given SQL select statement
	 *   Any valid where clause can be passed. 
	 *   <br/>
	 *   Examples:
	 *   String matching: Select * from Person.Address where address like '%1600%'
	 *   Zip code: where Select * from Person.Address where Zip > 33333
	 * @return ArrayList with records matching the sql select query
	 */
	public ArrayList<SalesRep> getCollection(String sql)
	{	
		SalesRep SalesRep = new SalesRep();
		ArrayList<SalesRep> list = new ArrayList<SalesRep>();
		Statement statement = null;
		ResultSet rs = null;			
		
		try
		{
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next())
			{		
				SalesRep.setID(rs.getInt("ID"));
				SalesRep.setPersonID(rs.getInt("PersonID"));
				SalesRep.setRegion(rs.getString("Region"));
				
				list.add(SalesRep);				
			}
		}
		catch (SQLException sqlex)
		{
			System.out.println(sqlex.getMessage());
		}
		return list;
	}
	
	/**
	 * search will do an SQL lookup matching an address record with the where clause provided.
	 * Calls getCollection to retrieve results, and returns an ArrayList of the Address instances
	 *   that match.
	 * <br/>
	 *   Examples:
	 *   String matching: where address like '%1600%'
	 *   Zip code: where Zip > 33333
	 * @return ArrayList with records matching the sql where clause parameter
	 */
	public ArrayList<SalesRep> search(String whereClause)
	{
		ArrayList<SalesRep> SalesReps;
		
		String sql = "SELECT * FROM " + getSchema() + getTable() + " " + whereClause + ";";
		// Calling getCollection with the above SQL
		SalesReps = new ArrayList<SalesRep>( getCollection(sql) );
		
		return SalesReps;
	}
}