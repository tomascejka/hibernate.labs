package cz.tc.learn.hibernate.model;

import java.time.*;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import cz.tc.learn.hibernate.test.lib.AbstractTest;
import cz.tc.learn.hibernate.model.Book;

/**
 * Zkousel jsem si zprehledeni junit-u via doInJpa metody, ale dosel jsem k zaveru, ze:
 * 	- je to nevhodne v teto fazi uceni - potrebujeme videt jpa/hibernate jak funguje
 *  - je to urcite vhodne v pozdejsich (novejsich) laboratorich, 
 * 		1. kde znalost hibernate/jpa bude hlubssi 
 *      2. tzn. bude prehlednejsi pouzivat doInJpa (bude skryvat, co jiz znas)
 * 
 * Napad doInJpa pochazi odsud:
 * 	- https://vladmihalcea.com/mysql-metadata-locking-and-database-transaction-ending/
 *  - https://github.com/vladmihalcea/high-performance-java-persistence/blob/master/core/src/test/java/com/vladmihalcea/hpjp/util/AbstractTest.java
 */
@Ignore 
public class JpaDateApiV2Test extends AbstractTest {

    private static final Logger LOG = Logger.getLogger(JpaDateApiV2Test.class.getName());

    @Test
    public void testDateApi() {

		Book b = new Book();
		doInJPA(entityManager -> {
			b.setDate(LocalDate.now());
			b.setDateTime(LocalDateTime.now());	
			
			ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
			LOG.info("Now with UTC: "+now);			
			entityManager.persist( b );
		} );

		doInJPA(entityManager -> {
			// -- read by book.id
			LOG.info("Read(by id): " + entityManager.find(Book.class, b.getId()));
			// -- read by book.date
			TypedQuery<Book> q = entityManager.createQuery("SELECT m FROM Book m WHERE m.date = :date", Book.class);
			q.setParameter("date", LocalDate.now());
			LOG.info("Read(by date): " + q.getSingleResult());			
		} );
    }
}