
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtilities 
{
	private static SessionFactory sessionFactory;

	static
	{
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
			.build(); // return a registry using the xml config file
		try 
		{
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) 
		{
			System.out.println("Caught exception by the tail...");
			System.out.println(e.getMessage());
			// The registry would be destroyed by the SessionFactory, but we had trouble 
			// building the SessionFactory, so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}		
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}