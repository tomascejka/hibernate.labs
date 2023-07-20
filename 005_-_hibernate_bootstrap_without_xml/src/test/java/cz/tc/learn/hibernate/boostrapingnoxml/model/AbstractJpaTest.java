package cz.tc.learn.hibernate.boostrapingnoxml.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractJpaTest {

    private EntityManagerFactory emf;

    public EntityManagerFactory entityManagerFactory() {
        return emf;
    }
    
    @Before
    public void init() {
        PersistenceUnitInfo persistenceUnitInfo = persistenceUnitInfo(getClass().getSimpleName());

        Map<String, Object> configuration = new HashMap<>();

        emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
            persistenceUnitInfo,
            configuration
        );
    }

    @After
    public void destroy() {
        emf.close();
    }
    
    protected PersistenceUnitInfoImpl persistenceUnitInfo(String name) {
        PersistenceUnitInfoImpl persistenceUnitInfo = new PersistenceUnitInfoImpl(
            name, entityClassNames(), properties()
        );

        String[] resources = resources();
        if (resources != null) {
            persistenceUnitInfo.getMappingFileNames().addAll(Arrays.asList(resources));
        }

        return persistenceUnitInfo;
    }

    protected abstract Class<?>[] entities();

    protected String[] resources() {
        return null;
    }

    protected List<String> entityClassNames() {
        return Arrays.asList(entities()).stream().map(Class::getName).collect(Collectors.toList());
    }

    protected Properties properties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        DataSource dataSource = newDataSource();
        if (dataSource != null) {
            properties.put("hibernate.connection.datasource", dataSource);
        }
        properties.put("hibernate.generate_statistics", Boolean.TRUE.toString());

        return properties;
    }
    
    protected DataSource newDataSource() {
    return proxyDataSource()
            ? dataSourceProxyType().dataSource(
                dataSourceProvider().dataSource())
            : dataSourceProvider().dataSource();
    }
    
    protected DataSourceProxyType dataSourceProxyType() {
        return DataSourceProxyType.DATA_SOURCE_PROXY;
    }
    
    protected boolean proxyDataSource() {
        return true;
    }
    
    protected DataSourceProvider dataSourceProvider() {
        return database().dataSourceProvider();
    }
    
    protected Database database() {
        return Database.HSQLDB;
    }   
}
