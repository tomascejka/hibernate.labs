package cz.tc.learn.hibernate.boostrapingnoxml;

import cz.tc.learn.hibernate.boostrapingnoxml.model.*;
import jakarta.persistence.EntityManager;

public class App {
    
    public static void main(String[] args) {

        EntityManager entityManager = getJpaEntityManager();
        
        entityManager.getTransaction().begin();
        Author newAuthor = new Author();
        newAuthor.setFirstName("Karolina");
        newAuthor.setLastName("Malkova");
        entityManager.persist(newAuthor);
        entityManager.getTransaction().commit();
 
         // additional CRUD operations       
        Author author = entityManager.find(Author.class, 1l);
        System.out.println(author);

        entityManager.getTransaction().begin();
        author.setLastName("Cejkova");
        entityManager.merge(author);
        entityManager.getTransaction().commit();         
         
    }

    private static class EntityManagerHolder {
        private static final EntityManager ENTITY_MANAGER = new JpaEntityManagerFactory(new Class[]{Author.class, Book.class, Publisher.class}).getEntityManager();
    }
    
    public static EntityManager getJpaEntityManager() {
        return EntityManagerHolder.ENTITY_MANAGER;
    }
}
