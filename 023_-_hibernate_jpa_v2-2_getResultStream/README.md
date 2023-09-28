# JPA v 2.2 getResultStream
Nova metoda, která dovolí podat list entit jako stream, ale pozor může být pomalá, resp. nesmíte nad ní dělat další stream operace, které jste zvyklý z jiných api (zde je result list z db).

Ukážeme si, co vynechat, resp. nepoužívat, viz javadoc u jednotlivých junit metod a čím to případně nahradit - jednoduše, používejte databási/JPQL query namísto Stream API pro jdbc resulty!!!

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
Spusť příkaz ```build.bat```, a můžete si ověřit jak fungují streamy z JPA, ale bacha s velkým omezením, resp. doporučením - nechte databázi provést práci, kterou umí (řadit, najít, spojit) a v jpa už jenom podejte/iterujte na výsledkem!!!!!!, viz. console.

## Run
Run příkaz není potřeba, vše obslouží příkaz ```build.bat```.

## Sources
1. https://thorben-janssen.com/jpa-2-2s-new-stream-method-and-how-you-should-not-use-it/
2. https://thorben-janssen.com/get-query-results-stream-hibernate-5/