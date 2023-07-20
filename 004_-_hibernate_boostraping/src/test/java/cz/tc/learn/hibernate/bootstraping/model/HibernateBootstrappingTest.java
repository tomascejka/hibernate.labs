package cz.tc.learn.hibernate.bootstraping.model;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import cz.tc.learn.hibernate.boostraping.model.Author;

public class HibernateBootstrappingTest {

    private static final Logger log = Logger.getLogger(HibernateBootstrappingTest.class.getName());

    @Test
    public void bootstrapping() {
        log.info("... bootstrapping ...");

        ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
        
        SessionFactory sessionFactory = new MetadataSources(standardRegistry)
            .addAnnotatedClass(Author.class).buildMetadata()
            .buildSessionFactory();
            Session session = sessionFactory.openSession();
        session.beginTransaction();

        Author a = new Author();
        a.setFirstName("Thorben");
        a.setLastName("Janssen");
        session.persist(a);

        session.getTransaction().commit();
        session.close();
    }
}