package cz.tc.learn.hibernate.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

@Entity
/* old style, before v2.2
@NamedQueries({
    @NamedQuery(name = “Book.findByTitle”, query = “SELECT b FROM Book b WHERE b.title = :title”),
    @NamedQuery(name = “Book.findByPublishingDate”, query = “SELECT b FROM Book b WHERE b.publishingDate = :publishingDate”)
})
*/
@NamedQuery(name = "Book.findByTitle", query ="SELECT b FROM Book b WHERE b.title = :title")
@NamedQuery(name = "Book.findByPublishingDate", query ="SELECT b FROM Book b WHERE b.publishingDate = :publishingDate")
public class Book {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

	@Version
	private int version;

	private String title;
 
    private LocalDate publishingDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(LocalDate publishingDate) {
		this.publishingDate = publishingDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Book)) {
			return false;
		}
		Book other = (Book) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", version=" + version 
				+ ", title=" + title + ", publishingDate=" + publishingDate
				+ "]";
	}    
}