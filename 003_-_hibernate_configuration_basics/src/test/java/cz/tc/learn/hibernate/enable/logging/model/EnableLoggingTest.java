package cz.tc.learn.hibernate.enable.logging.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cz.tc.learn.hibernate.enable.logging.model.Author;

public class EnableLoggingTest {

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