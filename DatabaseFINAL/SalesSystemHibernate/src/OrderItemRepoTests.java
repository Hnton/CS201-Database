import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("unused")
class OrderItemRepoTests
{
	
	@Test
	void OrderItemDefaultConstructorTest()
	{
		OrderItem testOrderItem = new OrderItem();
		assertTrue(testOrderItem.getItemID() == 0);
		assertTrue(testOrderItem.getOrderID() == 0);
		assertTrue(testOrderItem.getQuantity() == 0);
		assertTrue(testOrderItem.getID() == 0);
	}
	
	@Test
	void OrderItemOverloadedConstructorTest()
	{
		OrderItem testOrderItem = new OrderItem(2, 3, 3);
		assertTrue(testOrderItem.getItemID() == 3);
		assertTrue(testOrderItem.getOrderID() == 2);
		assertTrue(testOrderItem.getQuantity() == 3);
		assertTrue(testOrderItem.getID() == 0);
	}
	
	@Test
	void OrderItemRepoGetTest()
	{
		try
		{
			OrderItemRepo addRepo = new OrderItemRepo();
			assertTrue(addRepo.count() > 0);
			OrderItem testOrderItem = addRepo.get(1);
			assertNotNull(testOrderItem);
			testOrderItem = addRepo.getFirst();
			assertNotNull(testOrderItem);
		}
		catch (Exception ex)
		{
			fail("Could not get an OrderItem");
		}
	}
	
	@Test
	void OrderItemRepoSearchTest()
	{
		try
		{
			OrderItemRepo addRepo = new OrderItemRepo();
			ArrayList<OrderItem> results = addRepo.search("where OrderID like '1'");
			assertTrue(results.size() > 0); // One OrderItem has 1600 in a field
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void OrderItemAddNewRecordTest()
	{
		try
		{
			int initialCount = 0;
			int status = 0; // Value should be PK id or -1, but never 0
			OrderItem testOrderItem = new OrderItem(2, 3, 3);
			OrderItemRepo addRepo = new OrderItemRepo();
			initialCount = addRepo.count();
			status = addRepo.add(testOrderItem);
			assertTrue(status > 0);
			assertTrue(addRepo.count() > initialCount);
		}
		catch (Exception ex)
		{
			fail("Could not add new OrderItem");
		}
	}
	
	@Test
	void OrderItemUpdateExistingRecordTest()
	{
		try
		{
			OrderItemRepo addRepo = new OrderItemRepo();
			OrderItem OrderItem = addRepo.getFirst();
			OrderItem.setItemID(OrderItem.getItemID() + 1);
			assertTrue(addRepo.update(OrderItem));
			// New call to getFirst will retrieve record from the DB again.  
			// If it saved, they will be equal
			assertTrue(OrderItem.equals(addRepo.getFirst()));
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void OrderItemDeleteExistingRecordTest()
	{
		try
		{
			int currentCount = 0; // Value should be PK id or -1, but never 0
			OrderItemRepo addRepo = new OrderItemRepo();
			// Add a new OrderItem by itself so it won't have FK constraint with Person.Person table
			// Wouldn't be an issue if more than just the OrderItem table was mapped, but in this demo
			//  only OrderItem is mapped so it cannot do a cascade delete
			OrderItem testOrderItem = new OrderItem(2, 3, 3);
			addRepo.add(testOrderItem);
			currentCount = addRepo.count();
			assertTrue(addRepo.delete(testOrderItem));
			Assert.assertEquals(currentCount - 1, addRepo.count());
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void OrderItemAddCollectionTest()
	{
		try
		{
			ArrayList<OrderItem> addCollection = new ArrayList<OrderItem>();
			OrderItemRepo addRepo = new OrderItemRepo();
			int initialCount = addRepo.count();
			int newCount = 0;
			addCollection.add(new OrderItem(2, 3, 3));
			addCollection.add(new OrderItem(2, 1, 1));
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
	void OrderItemUpdateCollectionTest()
	{
		try
		{
			ArrayList<OrderItem> updateCollection = new ArrayList<OrderItem>();
			OrderItemRepo addRepo = new OrderItemRepo();
			OrderItem compAdd1 = new OrderItem();
			OrderItem compAdd2 = new OrderItem();
			OrderItem add1 = addRepo.get(1);
			OrderItem add2 = addRepo.get(2);
			
			
			add1.setQuantity(3);
			add2.setQuantity(10);
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
	void OrderItemDeleteCollectionTest()
	{
		try
		{
			ArrayList<OrderItem> delCollection = new ArrayList<OrderItem>();
			OrderItemRepo addRepo = new OrderItemRepo();
			delCollection.add(new OrderItem(3, 3, 3));
			delCollection.add(new OrderItem(2, 2, 2));
			// Need new OrderItemes without a Person.Person due to FK constraint and only OrderItem being mapped
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