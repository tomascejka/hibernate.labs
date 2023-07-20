# Hibernate bootstraping
Presentace, jak jednoduše lze nakonfigurovat nástroj Hibernate/ORM pomocí souboru ```hiberante.cfg.xml```.

## Předpoklady
Nastavení hibernate/h2 jdbc driverů na classpath via maven, viz. ```pom.xml``` soubor:

```
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.2.220</version>
    </dependency>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.6.15.Final</version>
    </dependency>
```



## Build
Spusť příkaz ```build.bat```, a prověří zda hibernate je správně nakonfigurován (junit skončí ok). Vše se konfiguruje v souboru ```src/test/resources/META-INF/hibernate.cfg.xml```. Např aktuální stav - nastavení h2/in-memory databáze, viz:

```
<?xml version="1.0" encoding="UTF-8"?>
<hibernate-configuration>
    <session-factory>
        <property name="connection.driver_class">org.h2.Driver</property>
        <property name="connection.url">jdbc:h2:mem:test;DB_CLOSE_DELAY=-1</property>
        <property name="connection.username"></property>
        <property name="connection.password"></property>
        <property name="connection.pool_size">1</property>

        <property name="dialect">org.hibernate.dialect.H2Dialect</property>
        
        <property name="hbm2ddl.auto">create</property>     
    </session-factory>
</hibernate-configuration>
```

## Run
Run příkaz není potřeba, vše obslouží příkaz ```build.bat```.

## Sources
1. https://thorben-janssen.com/hibernate-tips-use-hibernates-native-bootstrapping-api/