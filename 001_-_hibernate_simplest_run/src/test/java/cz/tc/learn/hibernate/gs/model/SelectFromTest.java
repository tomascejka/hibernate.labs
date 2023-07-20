package cz.tc.learn.hibernate.gs.model;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SelectFromTest {

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
    public void selectFrom() {
        log.info("... selectFrom ...");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Author a = em.createQuery("SELECT a FROM Author a WHERE id = 1", Author.class).getSingleResult();

        log.info("Author: "+a.getFirstName()+" "+a.getLastName());
        em.getTransaction().commit();
        em.close();
    }
}