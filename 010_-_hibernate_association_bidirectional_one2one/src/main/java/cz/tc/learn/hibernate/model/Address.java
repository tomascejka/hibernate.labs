package cz.tc.learn.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Address")
@Table(name = "address")
public class Address {
 
    @Id
    @GeneratedValue
    private Long id;
 
    private String title;
  
    //Getters and setters omitted for brevity

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
