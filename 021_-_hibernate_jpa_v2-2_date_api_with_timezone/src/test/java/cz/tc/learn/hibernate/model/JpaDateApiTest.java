package cz.tc.learn.hibernate.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JpaDateApiTest {

    private static final Logger LOG = Logger.getLogger(JpaDateApiTest.class.getName());

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
		LOG.info("... jpa v2.2 date api (WITH/OUT timezone) ...");

		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        LocalDate nowDate = LocalDate.now();
		LocalDateTime nowDt = LocalDateTime.now();
		
		// -- with TIMEZONE
		em.getTransaction().begin();
		LOG.info("---------------------------------------------------");
		LOG.info("Now in UTC: "+nowDt);
		LOG.info("---------------------------------------------------");
		Book e = new Book();
		e.setDate(nowDate);
		e.setDateTime(nowDt);
		e.setZonedDateTime(now);
		em.persist(e);
		em.getTransaction().commit();	

		em.getTransaction().begin();
		em.find(Book.class, e.getId());
		LOG.info("book={}"+e);
		em.getTransaction().commit();		
    }
}
