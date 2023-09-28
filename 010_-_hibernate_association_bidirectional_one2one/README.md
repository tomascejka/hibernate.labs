# Hibernate association OneToOne
Presentace, ze associace @OneToOne trpí problémem (pokud je bidirectional/oboustranná) s fetchType.LAZY nastavením. V takovém případě hibernate vždy používá EAGER.

## Předpoklady
Mějme tři dvojice ve vztahu one to one:

1. Book    <--> Manuscript    (via @OneToOne/bidirectional, fetchType není specifikován, tzn. EAGER)
2. Post    <--> PostDetails   (via @OneToOne/bidirectional, fetchType je exp. definován, tzn. LAZY)
3. Address <--  AddressDetail (via @Maps/unidirectional via shared key, tzn. sdílení PK)

## Build
Spusť příkaz ```build.bat```, který spustí všechny tři případy a je vidět, že až @MapsId funguje správně (za předpokladu, že chceme oneToOne/lazy chování).

```
-----------------------------------------------
... type: EAGER (natively via @OneToOne) ...
----------------------------------------------
20:29:36,607 DEBUG [org.hibernate.SQL] -
    select
        book0_.id as id1_2_0_,
        book0_.title as title2_2_0_,
        book0_.version as version3_2_0_,
        manuscript1_.id as id1_3_1_,
        manuscript1_.fk_book as fk_book3_3_1_,
        manuscript1_.file as file2_3_1_
    from
        Book book0_
    left outer join
        Manuscript manuscript1_
            on book0_.id=manuscript1_.fk_book
    where
        book0_.id=?
----------------------------------------------
... type: LAZY (via @OneToOne but with 2x selects) ...
----------------------------------------------
20:29:36,609 DEBUG [org.hibernate.SQL] -
    select
        post0_.id as id1_4_0_,
        post0_.title as title2_4_0_
    from
        post post0_
    where
        post0_.id=?
20:29:36,610 DEBUG [org.hibernate.SQL] -
    select
        postdetail0_.id as id1_5_0_,
        postdetail0_.created_by as created_2_5_0_,
        postdetail0_.created_on as created_3_5_0_,
        postdetail0_.post_id as post_id4_5_0_
    from
        post_details postdetail0_
    where
        postdetail0_.post_id=?
----------------------------------------------
... type: LAZY (via @Maps) ...
----------------------------------------------
20:29:36,611 DEBUG [org.hibernate.SQL] -
    select
        addressdet0_.id as id1_1_0_,
        addressdet0_.created_by as created_2_1_0_,
        addressdet0_.created_on as created_3_1_0_
    from
        address_details addressdet0_
    where
        addressdet0_.id=?
```

## Run
Run příkaz není potřeba, vše obslouží příkaz ```build.bat```.

## Sources
1. https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
2. https://thorben-janssen.com/hibernate-tip-lazy-loading-one-to-one/