package cz.tc.learn.hibernate.model;

import java.time.*;
import javax.persistence.*;

@Entity
public class Book {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

	@Version
	private int version;
 
    private LocalDate date;
    private LocalDateTime dateTime;
	private ZonedDateTime zonedDateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

    // -- atributes under study -- 
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalDateTime getDateTime() {  return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
	public ZonedDateTime getZonedDateTime() { return zonedDateTime; }
    public void setZonedDateTime(ZonedDateTime zonedDateTime) { this.zonedDateTime = zonedDateTime; }

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
				+ ", date=" + date + ", dateTime=" + dateTime + ", zonedDateTime="+zonedDateTime
				+ "]";
	}    
}