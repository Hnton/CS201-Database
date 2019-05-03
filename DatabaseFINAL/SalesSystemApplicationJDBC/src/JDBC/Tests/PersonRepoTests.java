package JDBC.Tests;

/**
 * Unit tests for the PersonRepo implementation.
 * Not well formed yet because the database isn't properly reset after each test
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import JDBC.Objects.Customer;
import JDBC.Objects.Person;
import JDBC.Repos.*;

import java.util.ArrayList;

class PersonRepoTests
{
	
	@Test
	void PersonDefaultConstructorTest()
	{
		Person testPerson = new Person();
		assertTrue(testPerson.getAddressID() == 0);
		assertTrue(testPerson.getFirstName().isBlank());
		assertTrue(testPerson.getLastName().isBlank());
		assertTrue(testPerson.getPhone().isBlank());
	}
	
	@Test
	void PersonOverloadedConstructorTest()
	{
		Person testPerson = new Person(1, "Mikael", "Hinton", "'304-588-2805'");
		assertTrue(testPerson.getAddressID() == 1);
		assertTrue(testPerson.getFirstName().equals("Mikael"));
		assertTrue(testPerson.getLastName().equals("Hinton"));
		assertTrue(testPerson.getPhone().equals("'304-588-2805'"));
		assertTrue(testPerson.getID() == 0);
	}
	
	@Test
	void PersonRepoGetTest()
	{
		try
		{
			PersonRepo addRepo = new PersonRepo();
			assertTrue(addRepo.count() > 0);
			Person testPerson = addRepo.get(1);
			assertNotNull(testPerson);
		}
		catch (Exception ex)
		{
			fail("Could not count records");
		}
	}

	@Test
	void PersonRepoGetAllTest()
	{
		try
		{
			PersonRepo PersonRepo = new PersonRepo();
			// getAll() returns an array list, which has a size() method
			assertTrue(PersonRepo.count() == PersonRepo.getAll().size());
		}
		catch (Exception ex)
		{
			fail("Could not get all records");
		}
	}
	
	@Test
	void PersonRepoSearchTest()
	{
		try
		{
			//resetDatabase();
			PersonRepo addRepo = new PersonRepo();
			ArrayList<Person> results = addRepo.search("where FirstName like 'Aunt'");
			assertTrue(1 == results.size()); // One Person has 1600 in a field
		}
		catch (Exception ex)
		{
			fail("Could not search");
		}
	}
	
	@Test
	void PersonAddNewRecordTest()
	{
		try
		{
			int status = 0; // Value should be PK id or -1, but never 0
			int initialCount = 0;
			Person Person = new Person(1, "Mikael", "Hinton", "'304-588-2805'");
			PersonRepo PersonRepo = new PersonRepo();
			initialCount = PersonRepo.count();
			status = PersonRepo.add(Person);
			assertTrue(status > 0);
			assertTrue(PersonRepo.count() > initialCount);
		}
		catch (Exception ex)
		{
			fail("Could not add new record");
		}
	}

	@Test
	void PersonUpdateExistingRecordTest()
	{
		try
		{
			PersonRepo PersonRepo = new PersonRepo();
			Person Person = PersonRepo.getFirst();
			Person.setAddressID(1);
			assertTrue(PersonRepo.update(Person));
			// New call to getFirst will retrieve record from the DB again.  
			// If it saved, they will be equal
			assertTrue(Person.getAddressID() == PersonRepo.getFirst().getAddressID());
		}
		catch (Exception ex)
		{
			fail("Could not update existing record");
		}
	}
	
	@Test
	void PersonAddCollectionTest()
	{
		try
		{
			ArrayList<Person> addCollection = new ArrayList<Person>();
			PersonRepo PersonRepo = new PersonRepo();
			int initialCount = PersonRepo.count();
			int newCount = 0;
			addCollection.add(new Person(2, "Brian", "Belcher", "'123-456-7890'"));
			addCollection.add(new Person(3, "Jack", "Tucker", "'321-654-9870'"));
			PersonRepo.addCollection(addCollection);
			newCount = PersonRepo.count();
			Assert.assertEquals(initialCount + 2, newCount);
		}
		catch (Exception ex)
		{
			fail("Could not add collection");
		}
	}
	
	@Test
	void PersonUpdateCollectionTest()
	{
		try
		{
			ArrayList<Person> updateCollection = new ArrayList<Person>();
			PersonRepo addRepo = new PersonRepo();
			Person compAdd1 = new Person();
			Person add1 = addRepo.get(1);

			add1.setFirstName("BOB");
			updateCollection.add(add1);
			addRepo.updateCollection(updateCollection);
			// These should now be KC and CO in the DB
			compAdd1 = addRepo.get(1);
			// Make sure our locally updated object is in sync with the DB
			Assert.assertEquals(add1.getFirstName(), compAdd1.getFirstName());
		}
		catch (Exception ex)
		{
			fail("Could not update collection");
		}
	}	
}
