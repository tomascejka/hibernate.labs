# JPA v 2.2 - date time api with timezone thanks to Hibernate
Jak pouzivat nove jdk8 datetime api via JPA v2.2 + rozsireni v Hibernate o TIMEZONE část/údaj.

## Rozšíření Hibernate 5
Hibernate v5.5+ kromě JPA v2.2 podoruje i další rozšíření date api. A to Duration, Instant a ZonedDateTime objekty. Zde je mapování:

    Java Type            |  JDBC Type
+------------------------+---------------------
java.time.LocalDate      |  DATE
java.time.LocalTime      |  TIME
java.time.LocalDateTime  |  TIMESTAMP
java.time.OffsetTime     |  TIME_WITH_TIMEZONE
java.time.OffsetDateTime |  TIMESTAMP_WITH_TIMEZONE
java.time.Duration       |  BIGINT
java.time.Instant        |  TIMESTAMP
java.time.ZonedDateTime  |  TIMESTAMP

Pozor: Mapování Duration a Instant jsou zřejmé. Ale mapování ZonedDateTime trpí probléměm -  [zdroj](https://hibernate.atlassian.net/browse/HHH-11621), protože Hibernate používá pro mapování JDBC/java.sql.Timestamp objekt, který nejednoznačně používá data o timezoně. Řešením je explicitní nastavení timezony, via ```hibernate.jdbc.time_zone``` v ```persistence.xml```.


## Předpoklady
Nastavení hibernate/h2 jdbc driverů na classpath via maven, viz. ```pom.xml``` soubor:

```
    <!-- JPA v2.2 -->
    <dependency>
      <groupId>javax.persistence</groupId>
      <artifactId>javax.persistence-api</artifactId>
      <version>2.2</version>
    </dependency>
    
    <!-- JPA implementations: -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.6.15.Final</version>
    </dependency>

    <!-- database (for junit test)-->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.2.220</version>
    </dependency>
```

## Build
Spusť příkaz ```build.bat```, a můžete si ověřit jak funguje s/bez nastavení ```hibernate.jdbc.time_zone``` v ```persistene.xml``` viz. console.

## Run
Run příkaz není potřeba, vše obslouží příkaz ```build.bat```.

## Sources
1. https://thorben-janssen.com/map-date-time-api-jpa-2-2/
2. https://github.com/thjanssen/HibernateTips/blob/master/DateAndTime/