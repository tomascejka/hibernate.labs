package cz.tc.learn.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "PostDetails")
@Table(name = "post_details")
public class PostDetails {
 
    @Id
    @GeneratedValue
    private Long id;
 
    @Column(name = "created_on")
    private Date createdOn;
 
    @Column(name = "created_by")
    private String createdBy;
 
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
 
    public PostDetails() {}
 
    public PostDetails(String createdBy) {
        createdOn = new Date();
        this.createdBy = createdBy;
    }

    public void setPost(Post post) {
        this.post = post;
    }
 
    //Getters and setters omitted for brevity
}
