package cz.tc.learn.hibernate.model;

import java.time.LocalDate;
import javax.persistence.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RepeatableTest {

    private static final Logger LOG = Logger.getLogger(RepeatableTest.class.getName());

    private static EntityManagerFactory emf;
	private EntityManager em;
  
	@BeforeClass
	public static void setup() {
		emf = Persistence.createEntityManagerFactory("sample-unit");
	}
  
	@AfterClass public static void tear() { emf.close(); }
  
	@Before public void setupThis() { 
		em = emf.createEntityManager();
	}
  
	@After public void tearThis() { em.close(); }	

    @Test
    public void testDateApi() {
		LOG.info("... repeatable annotation on Book entity ...");
        LocalDate nowDate = LocalDate.now();
		String title = "Hibernate tips";

		// -- with TIMEZONE
		em.getTransaction().begin();
		Book e = new Book();
		e.setTitle(title);
		e.setPublishingDate(nowDate);
		em.persist(e);
		em.getTransaction().commit();	

		em.getTransaction().begin();
		
		Book e2 = em.createNamedQuery("Book.findByPublishingDate", Book.class)
			.setParameter("publishingDate", nowDate)
			.getSingleResult();
		LOG.info("book(by date)={}"+e2);

		Book e3 = em.createNamedQuery("Book.findByTitle", Book.class)
			.setParameter("title", title)
			.getSingleResult();
		LOG.info("book(by title)={}"+e3);

		em.getTransaction().commit();		
    }
}
