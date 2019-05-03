package JDBC.Tests;

/**
 * Unit tests for the SalesRepRepo implementation.
 * Not well formed yet because the database isn't properly reset after each test
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import JDBC.Objects.SalesRep;
import JDBC.Repos.*;

import java.util.ArrayList;

class SalesRepRepoTests
{
	
	@Test
	void SalesRepDefaultConstructorTest()
	{
		SalesRep testSalesRep = new SalesRep();
		assertTrue(testSalesRep.getPersonID() == 0);
		assertTrue(testSalesRep.getRegion().isBlank());
		assertTrue(testSalesRep.getID() == 0);
	}
	
	@Test
	void SalesRepOverloadedConstructorTest()
	{
		SalesRep testSalesRep = new SalesRep(9, "HII");
		assertTrue(testSalesRep.getPersonID() == 9);
		assertTrue(testSalesRep.getRegion().equals("HII"));
		assertTrue(testSalesRep.getID() == 0);
	}
	
	@Test
	void SalesRepRepoGetTest()
	{
		try
		{
			SalesRepRepo SalesRepRepo = new SalesRepRepo();
			assertTrue(SalesRepRepo.count() > 0);
			SalesRep testSalesRep = SalesRepRepo.get(1);
			assertNotNull(testSalesRep);
		}
		catch (Exception ex)
		{
			fail("Could not count records");
		}
	}

	@Test
	void SalesRepRepoGetAllTest()
	{
		try
		{
			SalesRepRepo SalesRepRepo = new SalesRepRepo();
			// getAll() returns an array list, which has a size() method
			assertTrue(SalesRepRepo.count() == SalesRepRepo.getAll().size());
		}
		catch (Exception ex)
		{
			fail("Could not get all records");
		}
	}
	
	@Test
	void SalesRepRepoSearchTest()
	{
		try
		{
			//resetDatabase();
			SalesRepRepo addRepo = new SalesRepRepo();
			ArrayList<SalesRep> results = addRepo.search("where Region like '%MEX%'");
			assertTrue(1 == results.size()); // One SalesRep has 1600 in a field
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void SalesRepAddNewRecordTest()
	{
		try
		{
			int status = 0; // Value should be PK id or -1, but never 0
			int initialCount = 0;
			SalesRep testSalesRep = new SalesRep(2, "HII");
			SalesRepRepo SalesRepRepo = new SalesRepRepo();
			initialCount = SalesRepRepo.count();
			status = SalesRepRepo.add(testSalesRep);
			
			assertTrue(status > 0);
			assertTrue(SalesRepRepo.count() > initialCount);
		}
		catch (Exception ex)
		{
			fail("Could not add new record");
		}
	}
	
	@Test
	void SalesRepUpdateExistingRecordTest()
	{
		try
		{
			SalesRepRepo SalesRepRepo = new SalesRepRepo();
			SalesRep SalesRep = SalesRepRepo.getFirst();
			SalesRep.setPersonID(2);
			assertTrue(SalesRepRepo.update(SalesRep));
			// New call to getFirst will retrieve record from the DB again.  
			// If it saved, they will be equal
			assertTrue(SalesRep.getPersonID() == 2);
		}
		catch (Exception ex)
		{
			fail("Could not update existing record");
		}
	}
	
	@Test
	void SalesRepAddCollectionTest()
	{
		try
		{
			ArrayList<SalesRep> addCollection = new ArrayList<SalesRep>();
			SalesRepRepo SalesRepRepo = new SalesRepRepo();
			int initialCount = SalesRepRepo.count();
			int newCount = 0;
			addCollection.add(new SalesRep(4, "WQK"));
			addCollection.add(new SalesRep(3, "PKJ"));
			SalesRepRepo.addCollection(addCollection);
			newCount = SalesRepRepo.count();
			Assert.assertEquals(initialCount + 2, newCount);
		}
		catch (Exception ex)
		{
			fail("Could not add collection");
		}
	}
	
	@Test
	void SalesRepUpdateCollectionTest()
	{
		try
		{
			ArrayList<SalesRep> updateCollection = new ArrayList<SalesRep>();
			SalesRepRepo addRepo = new SalesRepRepo();
			SalesRep compAdd1 = new SalesRep();
			SalesRep add1 = addRepo.get(1);
			
			add1.setRegion("USA");
			updateCollection.add(add1);
			addRepo.updateCollection(updateCollection);
			// These should now be KC and CO in the DB
			compAdd1 = addRepo.get(1);
			compAdd1.getRegion();
			// Make sure our locally updated object is in sync with the DB
			Assert.assertEquals(add1.getRegion(), compAdd1.getRegion());
		}
		catch (Exception ex)
		{
			fail("Could not update collection");
		}
	}	
}
