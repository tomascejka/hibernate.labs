# hibernate testing strategy
Jak naznacuje kod v odkazu [1](https://github.com/vladmihalcea/high-performance-java-persistence/blob/master/core/src/test/java/com/vladmihalcea/hpjp/util/AbstractTest.java#L404C1-L480C6), jak testuj Vlad. Je testovani celkem komplexni (nastaveni database na lokale a prip. nastaveni). A cilem je si projit tuto implementaci a najit si svou cestu.

Uz nejaky vyzkum probehl viz. 
- HibernateBootstrappingTest.java, viz. hibernate.labs/hibernate_boostraping (pouze hibernate API)
- HibernateBootstrappingJpaTest.java, viz. hibernate.labs/hibernate_boostraping_jpa (pouze jpa API)

## Sources
1. https://github.com/vladmihalcea/high-performance-java-persistence/blob/master/core/src/test/java/com/vladmihalcea/hpjp/util/AbstractTest.java#L404C1-L480C6