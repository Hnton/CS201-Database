package JDBC.Tests;

/**
 * Unit tests for the AddressRepo implementation.
 * Not well formed yet because the database isn't properly reset after each test
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import JDBC.Objects.*;
import JDBC.Repos.*;

import java.util.ArrayList;

class CustomerRepoTests
{
	
	@Test
	void CustomerDefaultConstructorTest()
	{
		Customer TestCustomer = new Customer();
		assertTrue(TestCustomer.getPersonID() == 0);
		assertTrue(TestCustomer.getSalesRepID() == 0);
		assertTrue(TestCustomer.getID() == 0);
	}
	
	@Test
	void CustomerOverloadedConstructorTest()
	{
		Customer TestCustomer = new Customer(1,1);
		assertTrue(TestCustomer.getPersonID() == 1);
		assertTrue(TestCustomer.getSalesRepID() == 1);
		assertTrue(TestCustomer.getID() == 0);
	}
	
	@Test
	void CustomerRepoGetTest()
	{
		try
		{
			CustomerRepo CustomerRepo = new CustomerRepo();
			assertTrue(CustomerRepo.count() > 0);
			Customer TestCustomer = CustomerRepo.get(1);
			assertNotNull(TestCustomer);
		}
		catch (Exception ex)
		{
			fail("Could not count records");
		}
	}

	@Test
	void CustomerRepoGetAllTest()
	{
		try
		{
			CustomerRepo CustomerRepo = new CustomerRepo();
			// getAll() returns an array list, which has a size() method
			assertTrue(CustomerRepo.count() == CustomerRepo.getAll().size());
		}
		catch (Exception ex)
		{
			fail("Could not get all records");
		}
	}
	
	@Test
	void CustomerRepoSearchTest()
	{
		try
		{
			//resetDatabase();
			CustomerRepo CustomerRepo = new CustomerRepo();
			ArrayList<Customer> results = CustomerRepo.search("where PersonID like '5'");
			assertTrue(1 == results.size()); // One address has 1600 in a field
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void CustomerAddNewRecordTest()
	{
		try
		{
			int status = 0; // Value should be PK id or -1, but never 0
			int initialCount = 0;
			Customer Customer = new Customer(1, 1);
			CustomerRepo CustomerRepo = new CustomerRepo();
			initialCount = CustomerRepo.count();
			status = CustomerRepo.add(Customer);
			assertTrue(status > 0);
			assertTrue(CustomerRepo.count() > initialCount);
		}
		catch (Exception ex)
		{
			fail("Could not add new record");
		}
	}

	@Test
	void CustomerUpdateExistingRecordTest()
	{
		try
		{
			CustomerRepo CustomerRepo = new CustomerRepo();
			Customer Customer = CustomerRepo.getFirst();
			Customer.setSalesRepID(1);
			assertTrue(CustomerRepo.update(Customer));
			// New call to getFirst will retrieve record from the DB again.  
			// If it saved, they will be equal
			assertTrue(Customer.equals(CustomerRepo.getFirst()));
		}
		catch (Exception ex)
		{
			fail("Could not update existing record");
		}
	}
	
	@Test
	void CustomerAddCollectionTest()
	{
		try
		{
			ArrayList<Customer> addCollection = new ArrayList<Customer>();
			CustomerRepo CustomerRepo = new CustomerRepo();
			int initialCount = CustomerRepo.count();
			int newCount = 0;
			addCollection.add(new Customer(4,4));
			addCollection.add(new Customer(4,4));
			CustomerRepo.addCollection(addCollection);
			newCount = CustomerRepo.count();
			Assert.assertEquals(initialCount + 2, newCount);
		}
		catch (Exception ex)
		{
			fail("Could not add collection");
		}
	}
	
	@Test
	void CustomerUpdateCollectionTest()
	{
		try
		{
			ArrayList<Customer> updateCollection = new ArrayList<Customer>();
			CustomerRepo CustomerRepo = new CustomerRepo();
			Customer compCust1 = new Customer();
			Customer compCust2 = new Customer();
			Customer cust1 = CustomerRepo.get(1);
			Customer cust2 = CustomerRepo.get(2);
			
			cust1.setPersonID(1);
			cust2.setSalesRepID(2);
			updateCollection.add(cust1);
			updateCollection.add(cust2);
			CustomerRepo.updateCollection(updateCollection);
			// These should now be KC and CO in the DB
			compCust1 = CustomerRepo.get(1);
			compCust2 = CustomerRepo.get(2);
			// Make sure our locally updated object is in sync with the DB
			Assert.assertEquals(cust1, compCust1);
			Assert.assertEquals(cust2, compCust2);
		}
		catch (Exception ex)
		{
			fail("Could not update collection");
		}
	}	
}
