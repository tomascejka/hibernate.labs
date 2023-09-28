package cz.tc.learn.hibernate.boostrapingnoxml;

import org.h2.jdbcx.JdbcDataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;

public class JpaEntityManagerFactory {
    
    private final String DB_URL = "jdbc:h2:mem:test,INIT=create schema IF NOT EXISTS sample";
    private final String DB_USER_NAME = "sa";
    private final String DB_PASSWORD = "";
    private final Class[] entityClasses;
    
    public JpaEntityManagerFactory(Class[] entityClasses) {
        this.entityClasses = entityClasses;
    }
            
    public EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
    
    protected EntityManagerFactory getEntityManagerFactory() {
        PersistenceUnitInfo persistenceUnitInfo = getPersistenceUnitInfo(getClass().getSimpleName());
        Map<String, Object> configuration = new HashMap<>();
        return new EntityManagerFactoryBuilderImpl(new PersistenceUnitInfoDescriptor(persistenceUnitInfo), configuration)
          .build();
    }
    
    protected HibernatePersistenceUnitInfo getPersistenceUnitInfo(String name) {
        return new HibernatePersistenceUnitInfo(name, getEntityClassNames(), getProperties());
    }
    
    protected List<String> getEntityClassNames() {
        return Arrays.asList(getEntities())
          .stream()
          .map(Class::getName)
          .collect(Collectors.toList());
    }
    
    protected Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.id.new_generator_mappings", false);
        properties.put("hibernate.connection.datasource", getDataSource());
        return properties;
    }
    
    protected Class[] getEntities() {
        return entityClasses;
    }
    
    protected DataSource getDataSource() {
	    JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(DB_URL);
	    dataSource.setUser(DB_USER_NAME);
        dataSource.setPassword(DB_PASSWORD);
	    return dataSource;
    }
}