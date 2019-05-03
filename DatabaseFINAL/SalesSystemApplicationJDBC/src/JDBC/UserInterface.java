package JDBC;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import JDBC.Objects.*;
import JDBC.Repos.*;

public class UserInterface {

	private static Scanner in;

	public static void main(String[] args) {

	    in = new Scanner(System.in);
		System.out.println("Welcome to Sales System JDBC!");
		
		int cat;
		do 
		{	
			System.out.println("\nPlease select Category below (1-3):"
					+ "\n1 - Address"
					+ "\n2 - Customerr"
					+ "\n3 - Person"
					+ "\n4 - SalesRep"
					+ "\n5 - Exit");
			cat = in.nextInt();
			//ADDRESS
			if(cat == 1)
			{
				System.out.println("\nADDRESS");
				
				AddressRepo item = new AddressRepo();
				
				System.out.println(item.getAll());


				System.out.println("Please select operation below (1-3):"
						+ "\n1 - Add"
						+ "\n2 - Update"
						+ "\n3 - Delete"
						+ "\n4 - Search");
				int itemCat = in.nextInt();
				switch(itemCat)
				{
//				Item 
				case 1:
					System.out.println("Add");
					System.out.println("Address: ");
					String inAddress = in.next();
					System.out.println("City: ");
					String inCity = in.next();
					System.out.println("State: ");
					String inState = in.next();
					System.out.println("ZIP: ");
					int inZip = in.nextInt();
					
					Address newAddress = new Address(inAddress, inCity, inState, inZip);
					AddressRepo addAddress = new AddressRepo();
					addAddress.add(newAddress);
					System.out.println("Adding " + newAddress + " to Address Table!");
					break;
				case 2:
					System.out.println("Update");
					
					System.out.println("Enter ID of Address you want to update: ");
					int inID = in.nextInt();
					AddressRepo updateAddress = new AddressRepo();
					Address upAddress = updateAddress.get(inID);
					System.out.println("What would you like to update?"
							+ "\n1 - Address"
							+ "\n2 - City"
							+ "\n3 - State"
							+ "\n4 - Zip");
					int whatUpdate = in.nextInt();
					
					if(whatUpdate == 1)
					{
						//NAME
						System.out.println("Update ADDRESS to: ");
						 String updateAddresss = in.next();
						upAddress.setAddress(updateAddresss);
						updateAddress.update(upAddress);
						
						System.out.println("ID " + inID + " ADDRESS has been updated to " + updateAddresss);
					}
					if(whatUpdate == 2)
					{
						//DESCRIPTION
						System.out.println("Update CITY to: ");
						 String updateCity = in.next();
						upAddress.setCity(updateCity);
						updateAddress.update(upAddress);
						
						System.out.println("ID " + inID + " CITY has been updated to " + updateCity);
					}
					if(whatUpdate == 3)
					{
						//INVENTORY
						System.out.println("Update STATE to: ");
						String updateState = in.next();
						upAddress.setState(updateState);
						updateAddress.update(upAddress);
						
						System.out.println("ID " + inID + " STATE has been updated to " + updateState);
		
					}
					if(whatUpdate == 4)
					{
						//PRICE
						System.out.println("Update ZIP to: ");
						int updateZip = in.nextInt();
						upAddress.setZip(updateZip);
						updateAddress.update(upAddress);
						
						System.out.println("ID " + inID + " ZIP has been updated to " + updateZip);
					}
					break;
				case 3:
					System.out.println("Delete");
					AddressRepo deleteAddress = new AddressRepo();
					Address delAddress = new Address();
					System.out.println("Enter ID of Address you want to delete: ");
					inID = in.nextInt();
					delAddress = deleteAddress.get(inID);
					
					deleteAddress.delete(delAddress);
					
					System.out.println("ID " + inID + " has been deleted");

					
					break;
				case 4:
					System.out.println("Search");
					System.out.println("What State would you like to search for?");
					String search = in.next();
					AddressRepo searchItem = new AddressRepo();
					ArrayList<Address> Result = searchItem.search("where state like " + "'" + search+"'");				
					System.out.println(Result);
					break;
				}
				
			}
			//CUSTOMER
			else if(cat == 2)
			{
				System.out.println("\nCustomer");
				
				CustomerRepo item = new CustomerRepo();

				
				System.out.println(item.getAll());



				System.out.println("\nPlease select operation below (1-3):"
						+ "\n1 - Add"
						+ "\n2 - Update"
						+ "\n3 - Delete"
						+ "\n4 - Search");
				int itemCat = in.nextInt();
				switch(itemCat)
				{
				case 1:
					System.out.println("Add");
					System.out.println("PersonID: ");
					int inPersonID = in.nextInt();
					System.out.println("SalesRepID: ");
					int inSalesRepID = in.nextInt();
					
					Customer newCustomer = new Customer(inPersonID, inSalesRepID);
					CustomerRepo addCustomer = new CustomerRepo();
					addCustomer.add(newCustomer);
					System.out.println("Adding " + newCustomer + " to Customer Table!");
					break;
				case 2:
					System.out.println("Update");
					
					System.out.println("Enter ID of Customer you want to update: ");
					int inID = in.nextInt();
					CustomerRepo updateCustomer = new CustomerRepo();
					Customer upCustomer = updateCustomer.get(inID);
					System.out.println("What would you like to update?"
							+ "\n1 - PersonID"
							+ "\n2 - SalesRepID");
					int whatUpdate = in.nextInt();
					
					if(whatUpdate == 1)
					{
						//CUSTOMERID
						System.out.println("Update PersonID to: ");
						int updatePersonID = in.nextInt();
						upCustomer.setPersonID(updatePersonID);
						updateCustomer.update(upCustomer);
						
						System.out.println("ID " + inID + " PersonID has been updated to " + updatePersonID);
					}
					if(whatUpdate == 2)
					{
						//DESCRIPTION
						System.out.println("Update SalesRepID to: ");
						int updateSalesRepID = in.nextInt();
						upCustomer.setSalesRepID(updateSalesRepID);
						updateCustomer.update(upCustomer);
						
						System.out.println("ID " + inID + " SalesRepID has been updated to " + updateSalesRepID);
					}
					break;
				case 3:
					System.out.println("Delete");
					CustomerRepo deleteCustomer = new CustomerRepo();
					Customer delCustomer = new Customer();
					System.out.println("Enter ID of Customer you want to delete: ");
					inID = in.nextInt();
					delCustomer = deleteCustomer.get(inID);
					
					deleteCustomer.delete(delCustomer);
					
					System.out.println("ID " + inID + " has been deleted");

					
					break;
				case 4:
					System.out.println("Search");
					System.out.println("What PersonID would you like to search for?");
					String search = in.next();
					CustomerRepo searchItem = new CustomerRepo();
					ArrayList<Customer> Result = searchItem.search("where PersonID like " + "'" + search+"'");				
					System.out.println(Result);
					break;
				}
			}
			//PERSON
			else if(cat == 3)
			{
				System.out.println("\nPERSON");
				
				PersonRepo item = new PersonRepo();
				


				System.out.println(item.getAll());


				System.out.println("\nPlease select operation below (1-3):"
						+ "\n1 - Add"
						+ "\n2 - Update"
						+ "\n3 - Delete"
						+ "\n4 - Search");
				int itemCat = in.nextInt();
				switch(itemCat)
				{
//				Item 
				case 1:
					System.out.println("Add");
					System.out.println("AddressID: ");
					int inAddressID = in.nextInt();
					System.out.println("First Name: ");
					String inFirstName = in.next();
					System.out.println("Last Name: ");
					String inLastName = in.next();
					System.out.println("Phone Name: ");
					String inPhone = in.next();
					
					inPhone = "'" + inPhone +"'";
					
					Person newPerson = new Person(inAddressID, inFirstName, inLastName, inPhone);
					PersonRepo addPerson = new PersonRepo();
					addPerson.add(newPerson);
					System.out.println("Adding " + newPerson + " to Person Table!");
					break;
				case 2:
					System.out.println("Update");
					
					System.out.println("Enter ID of Person you want to update: ");
					int inID = in.nextInt();
					PersonRepo updatePerson = new PersonRepo();
					Person upPerson = updatePerson.get(inID);
					System.out.println("What would you like to update?"
							+ "\n1 - AddressID"
							+ "\n2 - First Name"
							+ "\n3 - Last Name"
							+ "\n4 - Phone");
					int whatUpdate = in.nextInt();
					
					if(whatUpdate == 1)
					{
						//ORDERID
						System.out.println("Update AddressID to: ");
						int updateAddressID = in.nextInt();
						upPerson.setAddressID(updateAddressID);
						updatePerson.update(upPerson);
						
						System.out.println("ID " + inID + " AddressID has been updated to " + updateAddressID);
					}
					if(whatUpdate == 2)
					{
						//DESCRIPTION
						System.out.println("Update First Name to: ");
						String updateFirstName = in.next();
						upPerson.setFirstName(updateFirstName);
						updatePerson.update(upPerson);
						
						System.out.println("ID " + inID + " ItemID has been updated to " + updateFirstName);
					}
					if(whatUpdate == 3)
					{
						//DESCRIPTION
						System.out.println("Update Last Name to: ");
						String updateLastName = in.next();
						upPerson.setLastName(updateLastName);
						updatePerson.update(upPerson);
						
						System.out.println("ID " + inID + " Quantity has been updated to " + updateLastName);
					}
					if(whatUpdate == 4)
					{
						//DESCRIPTION
						System.out.println("Update Phone to: ");
						String updatePhone = in.next();
						upPerson.setPhone(updatePhone);
						updatePerson.update(upPerson);
						
						System.out.println("ID " + inID + " Quantity has been updated to " + updatePhone);
					}
					break;
				case 3:
					System.out.println("Delete");
					PersonRepo deletePerson = new PersonRepo();
					Person delPerson = new Person();
					System.out.println("Enter ID of Person you want to delete: ");
					inID = in.nextInt();
					delPerson = deletePerson.get(inID);
					
					deletePerson.delete(delPerson);
					
					System.out.println("ID " + inID + " has been deleted");

					
					break;
				case 4:
					System.out.println("Search");
					System.out.println("What First Name would you like to search for?");
					String search = in.next();
					PersonRepo searchPerson = new PersonRepo();
					ArrayList<Person> Result = searchPerson.search("where FirstName like " + "'" + search+"'");				
					System.out.println(Result);
					break;
				}
			}
			else if(cat == 4)
			{
				//SALESREP
System.out.println("\nSALESREP");
				
				SalesRepRepo item = new SalesRepRepo();

				System.out.println(item.getAll());


				System.out.println("\nPlease select operation below (1-3):"
						+ "\n1 - Add"
						+ "\n2 - Update"
						+ "\n3 - Delete"
						+ "\n4 - Search");
				int itemCat = in.nextInt();
				switch(itemCat)
				{
//				Item 
				case 1:
					System.out.println("Add");
					System.out.println("PersonID: ");
					int inPersonID = in.nextInt();
					System.out.println("Region: ");
					String inRegion = in.next();
					
					SalesRep newSalesRep = new SalesRep(inPersonID, inRegion);
					SalesRepRepo addSalesRep = new SalesRepRepo();
					addSalesRep.add(newSalesRep);
					System.out.println("Adding " + newSalesRep + " to SalesRep Table!");
					break;
				case 2:
					System.out.println("Update");
					
					System.out.println("Enter ID of SalesRep you want to update: ");
					int inID = in.nextInt();
					SalesRepRepo updateSalesRep = new SalesRepRepo();
					SalesRep upSalesRep = updateSalesRep.get(inID);
					System.out.println("What would you like to update?"
							+ "\n1 - PersonID"
							+ "\n2 - Region");
					int whatUpdate = in.nextInt();
					
					if(whatUpdate == 1)
					{
						//ORDERID
						System.out.println("Update PersonID to: ");
						int updatePersonID = in.nextInt();
						upSalesRep.setPersonID(updatePersonID);
						updateSalesRep.update(upSalesRep);
						
						System.out.println("ID " + inID + " PersonID has been updated to " + updatePersonID);
					}
					if(whatUpdate == 2)
					{
						//DESCRIPTION
						System.out.println("Update Region to: ");
						String updateRegion = in.next();
						upSalesRep.setRegion(updateRegion);
						updateSalesRep.update(upSalesRep);
						
						System.out.println("ID " + inID + " ItemID has been updated to " + updateRegion);
					}
					break;
				case 3:
					System.out.println("Delete");
					SalesRepRepo deleteSalesRep = new SalesRepRepo();
					SalesRep delSalesRep = new SalesRep();
					System.out.println("Enter ID of SalesRep you want to delete: ");
					inID = in.nextInt();
					delSalesRep = deleteSalesRep.get(inID);
					
					deleteSalesRep.delete(delSalesRep);
					
					System.out.println("ID " + inID + " has been deleted");

					
					break;
				case 4:
					System.out.println("Search");
					System.out.println("What Region would you like to search for?");
					String search = in.next();
					SalesRepRepo searchSalesRep = new SalesRepRepo();
					ArrayList<SalesRep> Result = searchSalesRep.search("where region like " + "'" + search+"'");				
					System.out.println(Result);
					break;
				}
			}
		}while(cat != 5);
		System.out.println("Have a nice day!");

	}
}

