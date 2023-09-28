package cz.tc.learn.hibernate.bootstraping.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.tc.learn.hibernate.boostraping.model.Author;

public class HibernateBootstrappingJpaTest {

    private static final Logger log = Logger.getLogger(HibernateBootstrappingJpaTest.class.getName());

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
    public void testBootstrapping() {
		log.info("... bootstrapping ...");

		em.getTransaction().begin();

		em.find(Author.class, 1L);

		em.getTransaction().commit();
    }
}