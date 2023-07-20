# Hibernate configuration basics
Presentace, jak jednoduše lze nakonfigurovat nástroj Hibernate/ORM (zde použiji H2/in-memory databázi) pomocí souboru ```persistence.xml```.

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
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1"
             xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">

    <persistence-unit name="sample-unit" transaction-type="RESOURCE_LOCAL">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <exclude-unlisted-classes>false</exclude-unlisted-classes>
        <properties>
            <!-- hibernate properties -->
            <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
            <property name="hibernate.format_sql" value="true" />
            <property name="hibernate.generate_statistics" value="true" />

            <!-- jpa properties -->
            <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"/>
            <!--
            @see https://thorben-janssen.com/jpa-persistence-xml/#Create_and_initialize_the_database
            -->
            <property name="javax.persistence.schema-generation.database.action"
                      value="create"/>
            <property name="javax.persistence.sql-load-script-source"
                value="data.sql" />

        </properties>
    </persistence-unit>
</persistence>
```

## Run
Run příkaz není potřeba, vše obslouží příkaz ```build.bat```.

## Sources
1. https://thorben-janssen.com/hibernate-getting-started/#Configuration