# Hibernate simplest run
Jednoduché nastavení a zprovoznění nástroje Hibernate/ORM (for java) bez nutnosti instalovat db, použitím H2/memory mode.

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
Spusť příkaz ```build.bat```, který v rámci junit testu spustí námi očekáváný kód (spustí jednoduchý dotaz, který dohledá entitu typu ```Author.java```). Před spuštění testu, se naplní databáze dle nastavení v ```src/main/resources/META-INF/persistence.xml``` (viz. parameter ```javax.persistence.sql-load-script-source```).

## Run
Run příkaz není potřeba, vše obslouží příkaz ```build.bat```.

## Sources
+ https://thorben-janssen.com/hibernate-getting-started/