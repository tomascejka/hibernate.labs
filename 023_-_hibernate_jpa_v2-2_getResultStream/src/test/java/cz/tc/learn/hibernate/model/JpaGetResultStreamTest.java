package cz.tc.learn.hibernate.model;

import java.time.LocalDate;
import java.util.stream.Stream;

import javax.persistence.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// -- nepouzivej stream api nad jpa result-em, database to umi lepeji, rychleji a kvalitneji!!!
public class JpaGetResultStreamTest {

    private static final Logger LOG = Logger.getLogger(JpaGetResultStreamTest.class.getName());

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

    // -- nepouzivetj stream api pro FILTROVANI - pouzivej JPQL query
	@Test
    public void testStreamAsJpa_filter() {
		em.getTransaction().begin();
		em.createQuery("SELECT a FROM Author a WHERE a.firstName like '%and%' and a.id >= 20", Author.class).getResultStream();
		em.getTransaction().commit();		
    }

	// -- nepouzivej stream api pro LIMITY - pouzivej JPQL query
	@Test
	public void testStreamAsJpa_limit() {
		em.getTransaction().begin();
		em.createQuery("SELECT a FROM Author a ORDER BY a.id ASC", Author.class)
			.setMaxResults(5)
			.setFirstResult(10)
			.getResultStream();	
		em.getTransaction().commit();	
	}

	// -- nepouzivej stream api pro SORTOVANI - pouzivej JPQL query
	@Test
	public void testStreamAsJpa_sort() {
		em.getTransaction().begin();
		em.createQuery("SELECT a FROM Author a ORDER BY a.id ASC", Author.class).getResultStream();;	
		em.getTransaction().commit();	
	}
}
