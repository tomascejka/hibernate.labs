# JPA v 2.2 - date time api
Jak pouzivat nove jdk8 api v JPA v2.2.

The JPA 2.2 specificakace mapuje pouze takové třídy Date and Time API, které jsou uvedeny v JDBC 4.2 specification. Což jsou tyto:

| Java Type                |  JDBC Type
+--------------------------+----------------------------
| java.time.LocalDate      |  DATE
| java.time.LocalTime      |  TIME
| java.time.LocalDateTime  |  TIMESTAMP
| java.time.OffsetTime     |  TIME_WITH_TIMEZONE
| java.time.OffsetDateTime |  TIMESTAMP_WITH_TIMEZONE

Pozn.
    + JPA nepodporuje ZonedDateTime, které ukládá dodatečné informace o timezone jako například "daylight saving time".

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
Spusť příkaz ```build.bat```, a zkusi insert nove instance entity a pak ji najit v databasi (vcetne zalogovani hodnot) viz. console.

## Run
Run příkaz není potřeba, vše obslouží příkaz ```build.bat```.

## Sources
1. https://thorben-janssen.com/map-date-time-api-jpa-2-2/
2. https://github.com/thjanssen/HibernateTips/blob/master/DateAndTime/