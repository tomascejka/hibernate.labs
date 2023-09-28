package cz.tc.learn.hibernate.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.tc.learn.hibernate.model.Book;

public class JpaDateApiTest {

    private static final Logger LOG = Logger.getLogger(JpaDateApiTest.class.getName());

    private static EntityManagerFactory emf;
	private EntityManager em;
  
	@BeforeClass
	public static void setup() {
		emf = Persistence.createEntityManagerFactory("sample-unit");
	}
  
	@AfterClass public static void tear() { emf.close(); }
  
	@Before public void setupThis() { em = emf.createEntityManager(); }
  
	@After public void tearThis() { em.close(); }	

    @Test
    public void testDateApi() {
		LOG.info("... jpa v2.2 date api ...");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sample-unit");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
        Book b = new Book();
        b.setDate(LocalDate.now());
        b.setTime(LocalTime.now());
        b.setDateTime(LocalDateTime.now());
        b.setOffsetTime(OffsetTime.now());
        b.setOffsetDateTime(OffsetDateTime.now());
        em.persist(b);
		em.getTransaction().commit();

		// -- READ transaction
		em.getTransaction().begin();
		Book b2 = em.find(Book.class, b.getId());
		LOG.info("Read(by id): " + b2);
		
		TypedQuery<Book> q = em.createQuery("SELECT m FROM Book m WHERE m.date = :date", Book.class);
		q.setParameter("date", LocalDate.now());
		LOG.info("Read(by date): " + q.getSingleResult());
		em.getTransaction().commit();

		em.close();
		emf.close();
    }
}
