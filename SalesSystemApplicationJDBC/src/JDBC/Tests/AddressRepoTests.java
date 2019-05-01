package JDBC.Tests;

/**
 * Unit tests for the AddressRepo implementation.
 * Not well formed yet because the database isn't properly reset after each test
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import JDBC.Objects.Address;
import JDBC.Repos.*;

import java.util.ArrayList;

class AddressRepoTests
{
	
	@Test
	void addressDefaultConstructorTest()
	{
		Address testAddress = new Address();
		assertTrue(testAddress.getAddress().isBlank());
		assertTrue(testAddress.getCity().isBlank());
		assertTrue(testAddress.getState().isBlank());
		assertTrue(testAddress.getZip() == 0);
		assertTrue(testAddress.getID() == 0);
	}
	
	@Test
	void addressOverloadedConstructorTest()
	{
		Address testAddress = new Address("123 Main Street", "Anytown", "NY", 12345);
		assertTrue(testAddress.getAddress().equals("123 Main Street"));
		assertTrue(testAddress.getCity().equals("Anytown"));
		assertTrue(testAddress.getState().equals("NY"));
		assertTrue(testAddress.getZip() == 12345);
		assertTrue(testAddress.getID() == 0);
	}
	
	@Test
	void addressRepoGetTest()
	{
		try
		{
			AddressRepo addRepo = new AddressRepo();
			assertTrue(addRepo.count() > 0);
			Address testAddress = addRepo.get(1);
			assertNotNull(testAddress);
		}
		catch (Exception ex)
		{
			fail("Could not count records");
		}
	}

	@Test
	void addressRepoGetAllTest()
	{
		try
		{
			AddressRepo addRepo = new AddressRepo();
			// getAll() returns an array list, which has a size() method
			assertTrue(addRepo.count() == addRepo.getAll().size());
		}
		catch (Exception ex)
		{
			fail("Could not get all records");
		}
	}
	
	@Test
	void addressRepoSearchTest()
	{
		try
		{
			//resetDatabase();
			AddressRepo addRepo = new AddressRepo();
			ArrayList<Address> results = addRepo.search("where address like '%1600%'");
			assertTrue(1 == results.size()); // One address has 1600 in a field
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void addressAddNewRecordTest()
	{
		try
		{
			int status = 0; // Value should be PK id or -1, but never 0
			int initialCount = 0;
			Address address = new Address("123 Main Street", "Vienna", "WV", 26105);
			AddressRepo addRepo = new AddressRepo();
			initialCount = addRepo.count();
			status = addRepo.add(address);
			assertTrue(status > 0);
			assertTrue(addRepo.count() > initialCount);
		}
		catch (Exception ex)
		{
			fail("Could not add new record");
		}
	}
	
	@Test
	void addressUpdateExistingRecordTest()
	{
		try
		{
			AddressRepo addRepo = new AddressRepo();
			Address address = addRepo.getFirst();
			address.setZip(33333);
			assertTrue(addRepo.update(address));
			// New call to getFirst will retrieve record from the DB again.  
			// If it saved, they will be equal
			assertTrue(address.equals(addRepo.getFirst()));
		}
		catch (Exception ex)
		{
			fail("Could not update existing record");
		}
	}
	
	@Test
	void addressAddCollectionTest()
	{
		try
		{
			ArrayList<Address> addCollection = new ArrayList<Address>();
			AddressRepo addRepo = new AddressRepo();
			int initialCount = addRepo.count();
			int newCount = 0;
			addCollection.add(new Address("456 7th Ave", "Marietta", "OH", 45750));
			addCollection.add(new Address("9876 12th Street", "Columbus", "OH", 45321));
			addRepo.addCollection(addCollection);
			newCount = addRepo.count();
			Assert.assertEquals(initialCount + 2, newCount);
		}
		catch (Exception ex)
		{
			fail("Could not add collection");
		}
	}
	
	@Test
	void addressUpdateCollectionTest()
	{
		try
		{
			ArrayList<Address> updateCollection = new ArrayList<Address>();
			AddressRepo addRepo = new AddressRepo();
			Address compAdd1 = new Address();
			Address compAdd2 = new Address();
			Address add1 = addRepo.get(1);
			Address add2 = addRepo.get(2);
			
			add1.setCity("Kansas City");
			add2.setState("CO");
			updateCollection.add(add1);
			updateCollection.add(add2);
			addRepo.updateCollection(updateCollection);
			// These should now be KC and CO in the DB
			compAdd1 = addRepo.get(1);
			compAdd2 = addRepo.get(2);
			// Make sure our locally updated object is in sync with the DB
			Assert.assertEquals(add1, compAdd1);
			Assert.assertEquals(add2, compAdd2);
		}
		catch (Exception ex)
		{
			fail("Could not update collection");
		}
	}	
}
