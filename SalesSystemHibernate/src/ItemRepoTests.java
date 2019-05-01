import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("unused")
class ItemRepoTests
{
	
	@Test
	void ItemDefaultConstructorTest()
	{
		Item testItem = new Item();
		assertTrue(testItem.getName().isBlank());
		assertTrue(testItem.getDescription().isBlank());
		assertTrue(testItem.getInventory() == 0);
		assertTrue(testItem.getPrice() == 0.00);
		assertTrue(testItem.getID() == 0);
	}
	
	@Test
	void ItemOverloadedConstructorTest()
	{
		Item testItem = new Item("Pool Cue", "1 of 100", 100, 100.00);
		assertTrue(testItem.getName().equals("Pool Cue"));
		assertTrue(testItem.getDescription().equals("1 of 100"));
		assertTrue(testItem.getInventory() == 100);
		assertTrue(testItem.getPrice() == 100.00);
		assertTrue(testItem.getID() == 0);
	}
	
	@Test
	void ItemRepoGetTest()
	{
		try
		{
			ItemRepo addRepo = new ItemRepo();
			assertTrue(addRepo.count() > 0);
			Item testItem = addRepo.get(1);
			assertNotNull(testItem);
			testItem = addRepo.getFirst();
			assertNotNull(testItem);
		}
		catch (Exception ex)
		{
			fail("Could not get an Item");
		}
	}
	
	@Test
	void ItemRepoSearchTest()
	{
		try
		{
			ItemRepo addRepo = new ItemRepo();
			ArrayList<Item> results = addRepo.search("where Name like 'Widget'");
			assertTrue(results.size() > 0); // One Item has 1600 in a field
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void ItemAddNewRecordTest()
	{
		try
		{
			int initialCount = 0;
			int status = 0; // Value should be PK id or -1, but never 0
			Item testItem = new Item("Pool Cue", "2 of 100", 100, 100.00);
			ItemRepo addRepo = new ItemRepo();
			initialCount = addRepo.count();
			status = addRepo.add(testItem);
			assertTrue(status > 0);
			assertTrue(addRepo.count() > initialCount);
		}
		catch (Exception ex)
		{
			fail("Could not add new Item");
		}
	}
	
	@Test
	void ItemUpdateExistingRecordTest()
	{
		try
		{
			ItemRepo addRepo = new ItemRepo();
			Item Item = addRepo.getFirst();
			Item.setPrice(Item.getPrice() + 1);
			assertTrue(addRepo.update(Item));
			// New call to getFirst will retrieve record from the DB again.  
			// If it saved, they will be equal
			assertTrue(Item.equals(addRepo.getFirst()));
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void ItemDeleteExistingRecordTest()
	{
		try
		{
			int currentCount = 0; // Value should be PK id or -1, but never 0
			ItemRepo addRepo = new ItemRepo();
			// Add a new Item by itself so it won't have FK constraint with Person.Person table
			// Wouldn't be an issue if more than just the Item table was mapped, but in this demo
			//  only Item is mapped so it cannot do a cascade delete
			Item testItem = new Item("Carom Cue", "5 of 100", 100, 1100.00);
			addRepo.add(testItem);
			currentCount = addRepo.count();
			assertTrue(addRepo.delete(testItem));
			Assert.assertEquals(currentCount - 1, addRepo.count());
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void ItemAddCollectionTest()
	{
		try
		{
			ArrayList<Item> addCollection = new ArrayList<Item>();
			ItemRepo addRepo = new ItemRepo();
			int initialCount = addRepo.count();
			int newCount = 0;
			addCollection.add(new Item("Carom Cue", "6 of 100", 100, 1100.00));
			addCollection.add(new Item("Carom Cue", "7 of 100", 100, 1100.00));
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
	void ItemUpdateCollectionTest()
	{
		try
		{
			ArrayList<Item> updateCollection = new ArrayList<Item>();
			ItemRepo addRepo = new ItemRepo();
			Item compAdd1 = new Item();
			Item compAdd2 = new Item();
			Item add1 = addRepo.get(1);
			Item add2 = addRepo.get(2);
			
			
			add1.setInventory(1000);
			add2.setInventory(900);
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
	void ItemDeleteCollectionTest()
	{
		try
		{
			ArrayList<Item> delCollection = new ArrayList<Item>();
			ItemRepo addRepo = new ItemRepo();
			delCollection.add(new Item("Carom Cue", "6 of 100", 100, 1100.00));
			delCollection.add(new Item("Carom Cue", "7 of 100", 100, 1100.00));
			// Need new Itemes without a Person.Person due to FK constraint and only Item being mapped
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