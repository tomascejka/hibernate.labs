package cz.tc.learn.hibernate.test.lib;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import cz.tc.learn.hibernate.test.lib.transaction.JPATransactionFunction;
import cz.tc.learn.hibernate.test.lib.transaction.JPATransactionVoidFunction;

public abstract class AbstractTest {

    private static final Logger LOG = Logger.getLogger(AbstractTest.class.getName());

	private static EntityManagerFactory emf;
	private EntityManager entityManager;
  
	@BeforeClass public static void setup() { emf = Persistence.createEntityManagerFactory("sample-unit"); }
  
	@AfterClass public static void tear() { emf.close(); }
  
	@Before public void setupThis() { entityManager = emf.createEntityManager(); }
  
	@After public void tearThis() { entityManager.close(); }	    

    protected <T> T doInJPA(JPATransactionFunction<T> function) {
        T result = null;
        //EntityManager entityManager = null;
        EntityTransaction txn = null;
        try {
            entityManager = emf.createEntityManager();
            function.beforeTransactionCompletion();
            txn = entityManager.getTransaction();
            txn.begin();
            result = function.apply(entityManager);
            if ( !txn.getRollbackOnly() ) {
                txn.commit();
            }
            else {
                try {
                    txn.rollback();
                }
                catch (Exception e) {
                    LOG.error( "Rollback failure", e );
                }
            }
        } catch (Throwable t) {
            if ( txn != null && txn.isActive() ) {
                try {
                    txn.rollback();
                }
                catch (Exception e) {
                    LOG.error( "Rollback failure", e );
                }
            }
            throw t;
        } finally {
            function.afterTransactionCompletion();
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return result;
    }

    protected void doInJPA(JPATransactionVoidFunction function) {
        //EntityManager entityManager = null;
        EntityTransaction txn = null;
        try {
            entityManager = emf.createEntityManager();
            function.beforeTransactionCompletion();
            txn = entityManager.getTransaction();
            txn.begin();
            function.accept(entityManager);
            if ( !txn.getRollbackOnly() ) {
                txn.commit();
            }
            else {
                try {
                    txn.rollback();
                }
                catch (Exception e) {
                    LOG.error( "Rollback failure", e );
                }
            }
        } catch (Throwable t) {
            if ( txn != null && txn.isActive() ) {
                try {
                    txn.rollback();
                }
                catch (Exception e) {
                    LOG.error( "Rollback failure", e );
                }
            }
            throw t;
        } finally {
            function.afterTransactionCompletion();
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

}

