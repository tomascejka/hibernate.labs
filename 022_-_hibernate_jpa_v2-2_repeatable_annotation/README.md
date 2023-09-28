# JPA v 2.2 Repeatable anotace
Nyní v JPA v2.2 lze jednoduše nahradit složenou anotaci s více opakujícími se anotacemi jednotlivými/seznam-based zápise (= přehlednější), viz. např.

```
@Entity
@NamedQueries({
    @NamedQuery(name = “Book.findByTitle”, query = “SELECT b FROM Book b WHERE b.title = :title”),
    @NamedQuery(name = “Book.findByPublishingDate”, query = “SELECT b FROM Book b WHERE b.publishingDate = :publishingDate”)
})
public class Book {
    ...
}
```

... lze nyní nahradit tatko:

```
@Entity
@NamedQuery(name = “Book.findByTitle”, query = “SELECT b FROM Book b WHERE b.title = :title”)
@NamedQuery(name = “Book.findByPublishingDate”, query = “SELECT b FROM Book b WHERE b.publishingDate = :publishingDate”)
public class Book {
    ...
}
```

Zde je seznam všech anotací JPA, kterých se to týká:

  Annotation                  Description
+------------------------+------------------------------------------------------------------------------------------------------
AssociationOverride      |    Override the mapping for an entity relationship.
AttributeOverride        |    Override the mapping of a Basic property.
Convert                  |    Activates or deactivates an AttributeConverter for a Basic property.
JoinColumn               |    Defines a join column for an association or element collection.
MapKeyJoinColumn         |    Defines the mapping to an entity that’s used as the map key.
NamedEntityGraph         |    Specifies a graph of entities that are fetched with a query.
NamedNativeQuery         |    Defines a named native SQL query.
NamedQuery               |    Defines a named JPQL query.
NamedStoredProcedureQuery|    Defines a named stored procedure query.
PersistenceContext       |    References a container-managed EntityManager.
PersistenceUnit          |    References a EntityManagerFactory and its associated persistence unit.
PrimaryKeyJoinColumn     |    References a primary key column that’s used as a foreign key to join to another table.
SecondaryTable           |    Defines a secondary database table that’s mapped by the entity.
SqlResultSetMapping      |    Defines the mapping of the result of native SQL query.
SequenceGenerator        |    Defines the sequence based primary key generator that’s referenced by a GeneratedValue annotation.
TableGenerator           |    Defines the table based primary key generator that’s referenced by a GeneratedValue annotation.

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
Spusť příkaz ```build.bat```, a můžete si ověřit jak fungují ```@NamedQuery``` anotace, kt. implementuji ```@Repeatable``` anotaci, viz. console.

## Run
Run příkaz není potřeba, vše obslouží příkaz ```build.bat```.

## Sources
1. https://thorben-janssen.com/jpa-2-2-repeatable-annotations/