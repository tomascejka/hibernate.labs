# Hibernate enable logging
Jednoduché nastavení a zprovoznění nástroje Hibernate/ORM (for java) včetně logování (bez nutnosti instalovat db, použitím H2/memory mode).

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

Dále soubor v ```src/test/resources/log4j.properties``` s tímto obsahem:

```
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{HH:mm:ss,SSS} %-5p [%c] - %m%n

log4j.rootLogger=info, stdout
# basic log level for all messages
log4j.logger.org.hibernate=info

# SQL statements and parameters
log4j.logger.org.hibernate.SQL=debug
#log4j.logger.org.hibernate.type.descriptor.sql=trace
```

Nicméně odkaz[2] doporučuje nastavení, a zde je souhrn pro všechny aktuálně platné verse Hibernate, tzn. 4, 5 a 6, viz. snippet níže:

```
###
# Global configuration for all Hibernate versions
###
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{HH:mm:ss,SSS} %-5p [%c] - %m%n
 
log4j.rootLogger=info, stdout
 
###
# Hibernate < 5.4.5
###
# basic log level for all messages
log4j.logger.org.hibernate=info
# SQL statements and parameters
log4j.logger.org.hibernate.SQL=debug
log4j.logger.org.hibernate.type.descriptor.sql=trace
# Statistics
log4j.logger.org.hibernate.stat=debug
# 2nd Level Cache
log4j.logger.org.hibernate.cache=debug
 
###
# Hibernate >= 5.4.5
###
# basic log level for all messages
log4j.logger.org.hibernate=info
# SQL statements and parameters
log4j.logger.org.hibernate.SQL=debug
log4j.logger.org.hibernate.type.descriptor.sql=trace
# Statistics and slow queries
log4j.logger.org.hibernate.stat=debug
log4j.logger.org.hibernate.SQL_SLOW=info
# 2nd Level Cache
log4j.logger.org.hibernate.cache=debug
 
###
#Hibernate >= 6
###
# basic log level for all messages
log4j.logger.org.hibernate=info
# SQL statements and parameters
log4j.logger.org.hibernate.SQL=debug
log4j.logger.org.hibernate.orm.jdbc.bind=trace
# Statistics and slow queries
log4j.logger.org.hibernate.stat=debug
log4j.logger.org.hibernate.SQL_SLOW=info
# 2nd Level Cache
log4j.logger.org.hibernate.cache=debug
```

## Build
Spusť příkaz ```build.bat```, který v rámci junit testu spustí námi kód. Process spuštění a dotazování se loguje (např. sql select dotaz, viz. níže) na standarní výstup, viz. sleduj konsoli během junit testu.

```
23:14:53,267 DEBUG [org.hibernate.SQL] - 
    select
        author0_.id as id1_0_,
        author0_.firstName as firstnam2_0_,
        author0_.lastName as lastname3_0_,
        author0_.version as version4_0_ 
    from
        Author author0_ 
    where
        author0_.id=1
```

## Run
Run příkaz není potřeba, vše obslouží příkaz ```build.bat```.

## Sources
1. https://thorben-janssen.com/hibernate-getting-started/
2. https://thorben-janssen.com/hibernate-logging-guide/