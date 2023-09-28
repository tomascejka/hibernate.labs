package cz.tc.learn.hibernate.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBidirectionalOneToOne {

	Logger log = Logger.getLogger(this.getClass().getName());

	private EntityManagerFactory emf;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("sample-unit");
	}

	@After
	public void close() {
		emf.close();
	}

	
	@Test
	public void bidirectionalOneToOne() {
		log.info("... bidirectionalOneToOne ...");

		// Add a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// -- bidirectional with onetoone.fetchLazy
		Book b = em.find(Book.class, 1L);
		Manuscript m = new Manuscript();
		m.setBook(b);
		b.setManuscript(m);
		em.persist(m);

		// -- bidirectional with mapsId
		Address a = em.find(Address.class, 3L);
		AddressDetails ad = new AddressDetails("toce");
		ad.setAddress(a);
		em.persist(ad);
		
		em.getTransaction().commit();
		em.close();
		
		// Get Book entity with Authors
		em = emf.createEntityManager();
		em.getTransaction().begin();

		System.out.println("-----------------------------------------------");
		System.out.println("... type: EAGER (natively via @OneToOne) ...");
		System.out.println("----------------------------------------------");
		b = em.find(Book.class, 1L);
		m = b.getManuscript();
		Assert.assertEquals(b, m.getBook());
		System.out.println("----------------------------------------------");
		System.out.println("... type: LAZY (via @OneToOne but with 2x selects) ...");
		System.out.println("----------------------------------------------");
		em.find(Post.class, 2L);
		System.out.println("----------------------------------------------");
		System.out.println("... type: LAZY (via @Maps) ...");
		System.out.println("----------------------------------------------");
		ad = em.find(AddressDetails.class, a.getId());
		Assert.assertEquals(a.getId(), ad.getAddress().getId());
		
		em.getTransaction().commit();
		em.close();
	}
}
