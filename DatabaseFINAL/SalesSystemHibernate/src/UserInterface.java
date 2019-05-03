import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {

	private static Scanner in;

	public static void main(String[] args) {

	    in = new Scanner(System.in);
		System.out.println("Welcome to Sales System Hibernate!");
		
		int cat;
		do 
		{	
			System.out.println("\nPlease select Category below (1-3):"
					+ "\n1 - Item"
					+ "\n2 - Order"
					+ "\n3 - OrderItem"
					+ "\n4 - Exit");
			cat = in.nextInt();
			//ITEM
			if(cat == 1)
			{
				System.out.println("\nITEM");
				
				ItemRepo item = new ItemRepo();

				ArrayList<Item> listName = item.getCollection("SELECT name FROM Item");
				ArrayList<Item> listID = item.getCollection("SELECT ID FROM Item");
				ArrayList<Item> listDescription = item.getCollection("SELECT description FROM Item");
				ArrayList<Item> listInventory = item.getCollection("SELECT inventory FROM Item");
				ArrayList<Item> listPrice = item.getCollection("SELECT price FROM Item");

				
				for(int i = 0; i < listName.size(); i++)
				{
					
					System.out.println("\nID: " + listID.get(i) +"\nNAME: "+ listName.get(i) + "\nDESCRIPTION: " + listDescription.get(i) + "\nINVENTORY: " + listInventory.get(i) + "\nPRICE: " + listPrice.get(i));
				}


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
					System.out.println("Name: ");
					String inName = in.next();
					System.out.println("Description: ");
					String inDescription = in.next();
					System.out.println("Inventory: ");
					int inInventory = in.nextInt();
					System.out.println("Price: ");
					double inPrice = in.nextDouble();
					
					Item newItem = new Item(inName, inDescription, inInventory, inPrice);
					ItemRepo addItem = new ItemRepo();
					addItem.add(newItem);
					System.out.println("Adding " + newItem + " to Item Table!");
					break;
				case 2:
					System.out.println("Update");
					
					System.out.println("Enter ID of Item you want to update: ");
					int inID = in.nextInt();
					ItemRepo updateItem = new ItemRepo();
					Item upItem = updateItem.get(inID);
					System.out.println("What would you like to update?"
							+ "\n1 - Name"
							+ "\n2 - Description"
							+ "\n3 - Inventory"
							+ "\n4 - Price");
					int whatUpdate = in.nextInt();
					
					if(whatUpdate == 1)
					{
						//NAME
						System.out.println("Update NAME to: ");
						 String updateName = in.next();
						upItem.setName(updateName);
						updateItem.update(upItem);
						
						System.out.println("ID " + inID + " NAME has been updated to " + updateName);
					}
					if(whatUpdate == 2)
					{
						//DESCRIPTION
						System.out.println("Update DESCRIPTION to: ");
						 String updateDescription = in.next();
						upItem.setDescription(updateDescription);
						updateItem.update(upItem);
						
						System.out.println("ID " + inID + " DESCRIPTION has been updated to " + updateDescription);
					}
					if(whatUpdate == 3)
					{
						//INVENTORY
						System.out.println("Update INVENTORY to: ");
						int updateInventory = in.nextInt();
						upItem.setInventory(updateInventory);
						updateItem.update(upItem);
						
						System.out.println("ID " + inID + " DESCRIPTION has been updated to " + updateInventory);
		
					}
					if(whatUpdate == 4)
					{
						//PRICE
						System.out.println("Update PRICE to: ");
						double updatePrice = in.nextDouble();
						upItem.setPrice(updatePrice);
						updateItem.update(upItem);
						
						System.out.println("ID " + inID + " PRICE has been updated to " + updatePrice);
					}
					break;
				case 3:
					System.out.println("Delete");
					ItemRepo deleteItem = new ItemRepo();
					Item delItem = new Item();
					System.out.println("Enter ID of Item you want to delete: ");
					inID = in.nextInt();
					delItem = deleteItem.get(inID);
					
					deleteItem.delete(delItem);
					
					System.out.println("ID " + inID + " has been deleted");

					
					break;
				case 4:
					System.out.println("Search");
					System.out.println("What Name would you like to search for?");
					String search = in.next();
					ItemRepo searchItem = new ItemRepo();
					ArrayList<Item> Result = searchItem.search("where Name like " + "'" + search+"'");				
					System.out.println(Result);
					break;
				}
				
			}
			//ORDER
			else if(cat == 2)
			{
				System.out.println("\nORDER");
				
				OrderRepo item = new OrderRepo();

				ArrayList<Order> listID = item.getCollection("SELECT ID FROM Order");
				ArrayList<Order> listCustomerID = item.getCollection("SELECT customerID FROM Order");
				ArrayList<Order> listdateOrdered = item.getCollection("SELECT dateOrdered FROM Order");
				ArrayList<Order> listShipped = item.getCollection("SELECT shipped FROM Order");

				
				for(int i = 0; i < listID.size(); i++)
				{
					
					System.out.println("\nID: " +listID.get(i) + "\nCustomerID: " + listCustomerID.get(i) +"\nDate Ordered: "+ listdateOrdered.get(i) + "\nShipped: " + listShipped.get(i));
				}


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
					System.out.println("CustomerID: ");
					int inCustomerID = in.nextInt();
					Date inDate = new Date();
					System.out.println("Date Ordered:" + inDate);
					System.out.println("Shipped: ");
					short inShipped = in.nextShort();
					
					Order newOrder = new Order(inCustomerID, inDate, inShipped);
					OrderRepo addOrder = new OrderRepo();
					addOrder.add(newOrder);
					System.out.println("Adding " + newOrder + " to Order Table!");
					break;
				case 2:
					System.out.println("Update");
					
					System.out.println("Enter ID of Order you want to update: ");
					int inID = in.nextInt();
					OrderRepo updateOrder = new OrderRepo();
					Order upOrder = updateOrder.get(inID);
					System.out.println("What would you like to update?"
							+ "\n1 - CustomerID"
							+ "\n2 - Shipped");
					int whatUpdate = in.nextInt();
					
					if(whatUpdate == 1)
					{
						//CUSTOMERID
						System.out.println("Update CustomerID to: ");
						int updateCustomerID = in.nextInt();
						upOrder.setCustomerID(updateCustomerID);
						updateOrder.update(upOrder);
						
						System.out.println("ID " + inID + " CustomerID has been updated to " + updateCustomerID);
					}
					if(whatUpdate == 2)
					{
						//DESCRIPTION
						System.out.println("Update Shipped to: 0 - NO 1 - YES ");
						short updateShipped = in.nextShort();
						upOrder.setShipped(updateShipped);
						updateOrder.update(upOrder);
						
						System.out.println("ID " + inID + " Shipped has been updated to " + updateShipped);
					}
					break;
				case 3:
					System.out.println("Delete");
					OrderRepo deleteOrder = new OrderRepo();
					Order delOrder = new Order();
					System.out.println("Enter ID of Item you want to delete: ");
					inID = in.nextInt();
					delOrder = deleteOrder.get(inID);
					
					deleteOrder.delete(delOrder);
					
					System.out.println("ID " + inID + " has been deleted");

					
					break;
				case 4:
					System.out.println("Search");
					System.out.println("What CustomerID would you like to search for?");
					String search = in.next();
					OrderRepo searchItem = new OrderRepo();
					ArrayList<Order> Result = searchItem.search("where CustomerID like " + "'" + search+"'");				
					System.out.println(Result);
					break;
				}
			}
			//ORDERITEM
			else if(cat == 3)
			{
	System.out.println("\nORDERITEM");
				
				OrderItemRepo item = new OrderItemRepo();
				
				ArrayList<OrderItem> listID = item.getCollection("SELECT ID FROM OrderItem");
				ArrayList<OrderItem> listOrderID = item.getCollection("SELECT orderID FROM OrderItem");
				ArrayList<OrderItem> listItemID = item.getCollection("SELECT itemID FROM OrderItem");
				ArrayList<OrderItem> listQuantity = item.getCollection("SELECT quantity FROM OrderItem");

				
				for(int i = 0; i < listID.size(); i++)
				{
					
					System.out.println("\nID: " +listID.get(i) + "\nOrderID: " + listOrderID.get(i) +"\nItemID: "+ listItemID.get(i) + "\nQuantity: " + listQuantity.get(i));
				}


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
					System.out.println("OrderID: ");
					int inOrderID = in.nextInt();
					System.out.println("ItemID: ");
					int inItemID = in.nextInt();
					System.out.println("Quantity: ");
					int inQuantity = in.nextInt();
					
					OrderItem newOrderItem = new OrderItem(inOrderID, inItemID, inQuantity);
					OrderItemRepo addOrderItem = new OrderItemRepo();
					addOrderItem.add(newOrderItem);
					System.out.println("Adding " + newOrderItem + " to OrderItem Table!");
					break;
				case 2:
					System.out.println("Update");
					
					System.out.println("Enter ID of OrderItem you want to update: ");
					int inID = in.nextInt();
					OrderItemRepo updateOrderItem = new OrderItemRepo();
					OrderItem upOrderItem = updateOrderItem.get(inID);
					System.out.println("What would you like to update?"
							+ "\n1 - OrderID"
							+ "\n2 - ItemID"
							+ "\n3 - Quantity");
					int whatUpdate = in.nextInt();
					
					if(whatUpdate == 1)
					{
						//ORDERID
						System.out.println("Update OrderID to: ");
						int updateOrderID = in.nextInt();
						upOrderItem.setOrderID(updateOrderID);
						updateOrderItem.update(upOrderItem);
						
						System.out.println("ID " + inID + " OrderID has been updated to " + updateOrderID);
					}
					if(whatUpdate == 2)
					{
						//DESCRIPTION
						System.out.println("Update ItemID to: ");
						int updateItemID = in.nextInt();
						upOrderItem.setItemID(updateItemID);
						updateOrderItem.update(upOrderItem);
						
						System.out.println("ID " + inID + " ItemID has been updated to " + updateItemID);
					}
					if(whatUpdate == 3)
					{
						//DESCRIPTION
						System.out.println("Update Quantity to: ");
						int updateQuantity = in.nextInt();
						upOrderItem.setQuantity(updateQuantity);
						updateOrderItem.update(upOrderItem);
						
						System.out.println("ID " + inID + " Quantity has been updated to " + updateQuantity);
					}
					break;
				case 3:
					System.out.println("Delete");
					OrderItemRepo deleteOrderItem = new OrderItemRepo();
					OrderItem delOrderItem = new OrderItem();
					System.out.println("Enter ID of OrderItem you want to delete: ");
					inID = in.nextInt();
					delOrderItem = deleteOrderItem.get(inID);
					
					deleteOrderItem.delete(delOrderItem);
					
					System.out.println("ID " + inID + " has been deleted");

					
					break;
				case 4:
					System.out.println("Search");
					System.out.println("What OrderID would you like to search for?");
					String search = in.next();
					OrderItemRepo searchOrderItem = new OrderItemRepo();
					ArrayList<OrderItem> Result = searchOrderItem.search("where OrderID like " + "'" + search+"'");				
					System.out.println(Result);
					break;
				}
			}
		}while(cat != 4);
		System.out.println("Have a nice day!");

	}
}
