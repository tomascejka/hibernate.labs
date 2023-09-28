# Hibernate bootstraping
Presentace, jak jednoduše lze nakonfigurovat nástroj Hibernate/ORM pomocí souboru ```persistence.xml``` a pak instanci PersistenceManagera
si vyrobit rucne, viz junit test.

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
Spusť příkaz ```build.bat```, a prověří zda hibernate je správně nakonfigurován (junit skončí ok). Vše se konfiguruje v souboru ```src/main/resources/META-INF/persistence.xml```. Např aktuální stav - nastavení h2/in-memory databáze, viz:

```
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
             http://www.oracle.com/webfolder/technetwork/jsc/xml/ns/persistence/persistence_2_1.xsd"
             version="2.1">
    <persistence-unit name="sample-unit">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <exclude-unlisted-classes>false</exclude-unlisted-classes>
        <properties>
            
            <property name="jakarta.persistence.jdbc.driver" value="org.h2.Driver"/>
            <property name="jakarta.persistence.jdbc.url" value="jdbc:h2:mem:test"/>
            <property name="jakarta.persistence.jdbc.user" value="sa"/>
            <property name="jakarta.persistence.jdbc.password" value=""/>

            <property name="hibernate.hbm2ddl.auto" value="create-drop"/>
            <property name="hibernate.show_sql" value="false"/>
            <property name="hibernate.format_sql" value="true"/>
            
            <property name="javax.persistence.schema-generation.database.action"
            value="drop-and-create" />
            <property name="javax.persistence.sql-load-script-source"
            value="data.sql" />            

        </properties>
    </persistence-unit>
</persistence>
```

## Run
Run příkaz není potřeba, vše obslouží příkaz ```build.bat```.

## Sources
1. https://thorben-janssen.com/hibernate-tips-use-hibernates-native-bootstrapping-api/