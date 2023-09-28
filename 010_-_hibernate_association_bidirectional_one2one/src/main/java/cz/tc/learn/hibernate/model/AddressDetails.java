package cz.tc.learn.hibernate.model;

import java.util.Date;

import javax.persistence.*;

@Entity(name = "AddressDetails")
@Table(name = "address_details")
public class AddressDetails {
 
    @Id
    private Long id;
 
    @Column(name = "created_on")
    private Date createdOn;
 
    @Column(name = "created_by")
    private String createdBy;
 
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Address address;
 
    public AddressDetails() {}
 
    public AddressDetails(String createdBy) {
        createdOn = new Date();
        this.createdBy = createdBy;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
 
    //Getters and setters omitted for brevity
}
