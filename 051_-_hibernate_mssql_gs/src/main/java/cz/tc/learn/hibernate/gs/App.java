package cz.tc.learn.hibernate.gs;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class App 
{
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("example-unit");

    public static void main(String[] args) {
        try {
            persistEmployees();
            findEmployeeByPhoneNumber();
            //with NOT and using parameter
            findEmployeeByPhoneNumber2();
            //using another entity instance with MEMBER OF
            findEmployeeWithTask();
        } finally {
            entityManagerFactory.close();
        }
    }

    public static void persistEmployees() {
        Task task1 = new Task("Coding", "Denise");
        Task task2 = new Task("Refactoring", "Rose");
        Task task3 = new Task("Designing", "Denise");
        Task task4 = new Task("Documentation", null);

        Employee employee1 = Employee.create("Diana", Arrays.asList("111-111-111", "222-222-222"), task1);
        Employee employee2 = Employee.create("Mike", Arrays.asList("666-666-666", "444-444-444"), task2, task3);
        Employee employee3 = Employee.create("Tim", Arrays.asList("555-555-555", "222-222-222"), task2);
        Employee employee4 = Employee.create("Jack", Arrays.asList("333-333-333"), task4);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);
        em.getTransaction().commit();
        em.close();
        System.out.println("-- Employee persisted --");
        System.out.println(employee1);
        System.out.println(employee2);
        System.out.println(employee3);
        System.out.println(employee4);
    }

    private static void findEmployeeByPhoneNumber() {
        System.out.println("-- Employees with phone number 222-222-222 --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Employee e where '222-222-222' MEMBER OF e.phoneNumbers");
        List<Employee> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        em.close();
    }

    private static void findEmployeeByPhoneNumber2() {
        System.out.println("-- Employees with phone number NOT 222-222-222 --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Employee e where :theNumber NOT MEMBER OF e.phoneNumbers");
        query.setParameter("theNumber", "222-222-222");
        List<Employee> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        em.close();
    }

    private static void findEmployeeWithTask() {
        System.out.println("-- Employees with task 'Designing' --");
        EntityManager em = entityManagerFactory.createEntityManager();
        //find task by name first
        Query query = em.createQuery("SELECT t FROM Task t where t.description LIKE 'Design%'");
        List<Task> tasks = query.getResultList();
        if(tasks.size()==0){
            return;
        }
        Task theTask = tasks.get(0);
        // using MEMBER OF
        Query query2 = em.createQuery("SELECT e FROM Employee e where :requiredTask MEMBER OF e.tasks");
        query2.setParameter("requiredTask", theTask);
        List<Employee> resultList = query2.getResultList();
        resultList.forEach(System.out::println);

        //alternatively we can use JOIN
        System.out.println("-- Employees with task 'Designing' using JOIN --");
        Query query3 = em.createQuery("SELECT e FROM Employee e LEFT JOIN e.tasks t where "
                + "t.description LIKE 'Design%'");
        List<Employee> resultList2 = query3.getResultList();
        resultList2.forEach(System.out::println);
        em.close();
    }
}
