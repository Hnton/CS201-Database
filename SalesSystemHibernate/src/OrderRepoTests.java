import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("unused")
class OrderRepoTests
{
	
	@Test
	void OrderDefaultConstructorTest()
	{
		Order testOrder = new Order();
		assertTrue(testOrder.getCustomerID() == 0);
		assertTrue(testOrder.getDateOrdered().equals(new Date(0000-00-00)));
		assertTrue(testOrder.getShipped() == 0);
		assertTrue(testOrder.getID() == 0);
	}
	
	@Test
	void OrderOverloadedConstructorTest()
	{
		Order testOrder = new Order(1, new Date(1996-10-20), (short) 0);
		assertTrue(testOrder.getCustomerID() == 1);
		assertTrue(testOrder.getDateOrdered().equals(new Date(1996-10-20)));
		assertTrue(testOrder.getShipped() == 0);
		assertTrue(testOrder.getID() == 0);
	}
	
	@Test
	void OrderRepoGetTest()
	{
		try
		{
			OrderRepo addRepo = new OrderRepo();
			assertTrue(addRepo.count() > 0);
			Order testOrder = addRepo.get(1);
			assertNotNull(testOrder);
			testOrder = addRepo.getFirst();
			assertNotNull(testOrder);
		}
		catch (Exception ex)
		{
			fail("Could not get an Order");
		}
	}
	
	@Test
	void OrderRepoSearchTest()
	{
		try
		{
			OrderRepo addRepo = new OrderRepo();
			ArrayList<Order> results = addRepo.search("where CustomerID like '1'");
			assertTrue(results.size() > 0); // One Order has 1600 in a field
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void OrderAddNewRecordTest()
	{
		try
		{
			int initialCount = 0;
			int status = 0; // Value should be PK id or -1, but never 0
			Order testOrder = new Order(1, new Date(1996-10-20), (short) 0);
			OrderRepo addRepo = new OrderRepo();
			initialCount = addRepo.count();
			status = addRepo.add(testOrder);
			assertTrue(status > 0);
			assertTrue(addRepo.count() > initialCount);
		}
		catch (Exception ex)
		{
			fail("Could not add new Order");
		}
	}
	
	@Test
	void OrderUpdateExistingRecordTest()
	{
		try
		{
			OrderRepo addRepo = new OrderRepo();
			Order Order = addRepo.getFirst();
			Order.setID(Order.getCustomerID() + 1);
			assertTrue(addRepo.update(Order));
			// New call to getFirst will retrieve record from the DB again.  
			// If it saved, they will be equal
			assertTrue(Order.equals(addRepo.getFirst()));
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void OrderDeleteExistingRecordTest()
	{
		try
		{
			int currentCount = 0; // Value should be PK id or -1, but never 0
			OrderRepo addRepo = new OrderRepo();
			// Add a new Order by itself so it won't have FK constraint with Person.Person table
			// Wouldn't be an issue if more than just the Order table was mapped, but in this demo
			//  only Order is mapped so it cannot do a cascade delete
			Order testOrder = new Order(1, new Date(1996-10-20), (short) 0);
			addRepo.add(testOrder);
			currentCount = addRepo.count();
			assertTrue(addRepo.delete(testOrder));
			Assert.assertEquals(currentCount - 1, addRepo.count());
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void OrderAddCollectionTest()
	{
		try
		{
			ArrayList<Order> addCollection = new ArrayList<Order>();
			OrderRepo addRepo = new OrderRepo();
			int initialCount = addRepo.count();
			int newCount = 0;
			addCollection.add(new Order(1, new Date(1996-10-24), (short) 0));
			addCollection.add(new Order(2, new Date(1996-10-25), (short) 1));
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
	void OrderUpdateCollectionTest()
	{
		try
		{
			ArrayList<Order> updateCollection = new ArrayList<Order>();
			OrderRepo addRepo = new OrderRepo();
			Order compAdd1 = new Order();
			Order compAdd2 = new Order();
			Order add1 = addRepo.get(1);
			Order add2 = addRepo.get(2);
			
			add1.setDateOrdered(new Date(2011-11-11));
			add2.setDateOrdered(new Date(2012-12-12));

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
	
	@Test
	void OrderDeleteCollectionTest()
	{
		try
		{
			ArrayList<Order> delCollection = new ArrayList<Order>();
			OrderRepo addRepo = new OrderRepo();
			delCollection.add(new Order(1, new Date(1999-9-29), (short) 0));
			delCollection.add(new Order(1, new Date(2019-10-24), (short) 0));
			// Need new Orderes without a Person.Person due to FK constraint and only Order being mapped
			addRepo.addCollection(delCollection);
			// Now that they are in the db, we can delete them (we hope!)
			assertTrue(addRepo.deleteCollection(delCollection) == 0);
		}
		catch (Exception ex)
		{
			fail("Could not delete a collection");
		}
	}
}