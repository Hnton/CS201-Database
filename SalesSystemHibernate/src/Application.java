import org.hibernate.Session;

public class Application
{
	public static void main(String[] args)
	{
		// Multiple schemas: https://stackoverflow.com/questions/39393773/how-to-configure-multiple-schemas-with-hibernate
		// Look at bottom answer with multiple cfg.xml files for simplest solution.  Update HibernateUtilities to do this
		// if you want more than one schema in an application.
		Session session = HibernateUtilities.getSessionFactory().openSession();
		
		//session.getTransaction().commit();
		
		Item item = session.get(Item.class, 1);
		
		
		session.close();
		HibernateUtilities.getSessionFactory().close();
//		System.out.println("Session opened and closed, and retrieved: " + item);
	}
}