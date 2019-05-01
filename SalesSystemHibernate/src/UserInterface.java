import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {

	    Scanner in = new Scanner(System.in);  // Create a Scanner object
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
				System.out.println("Please select operation below (1-3):"
						+ "\n1 - Get Collection"
						+ "\n2 - Add"
						+ "\n3 - Update"
						+ "\n4 - Delete"
						+ "\n5 - Search");
				int itemCat = in.nextInt();
				switch(itemCat)
				{
				case 1:
					System.out.println("Get Collections");
					break;
				case 2:
					System.out.println("Add");
					break;
				case 3:
					System.out.println("Update");
					break;
				case 4:
					System.out.println("Delete");
					break;
				case 5:
					System.out.println("Search");
					break;
				}
				
			}
			//ORDER
			else if(cat == 2)
			{
				System.out.println("\nORDER");
				System.out.println("Please select operation below (1-3):"
						+ "\n1 - Get Collection"
						+ "\n2 - Add"
						+ "\n3 - Update"
						+ "\n4 - Delete"
						+ "\n5 - Search");
				int orderCat = in.nextInt();
				switch(orderCat)
				{
				case 1:
					System.out.println("Get Collections");
					break;
				case 2:
					System.out.println("Add");
					break;
				case 3:
					System.out.println("Update");
					break;
				case 4:
					System.out.println("Delete");
					break;
				case 5:
					System.out.println("Search");
					break;
				}
			}
			//ORDERITEM
			else if(cat == 3)
			{
				System.out.println("/nORDERITEM");
				System.out.println("Please select operation below (1-3):"
						+ "\n1 - Get Collection"
						+ "\n2 - Add"
						+ "\n3 - Update"
						+ "\n4 - Delete"
						+ "\n5 - Search");
				int OrderItemCat = in.nextInt();
				switch(OrderItemCat)
				{
				case 1:
					System.out.println("Get Collections");
					break;
				case 2:
					System.out.println("Add");
					break;
				case 3:
					System.out.println("Update");
					break;
				case 4:
					System.out.println("Delete");
					break;
				case 5:
					System.out.println("Search");
					break;
				}
			}
		}while(cat != 4);
		System.out.println("Have a nice day!");

	}
}
