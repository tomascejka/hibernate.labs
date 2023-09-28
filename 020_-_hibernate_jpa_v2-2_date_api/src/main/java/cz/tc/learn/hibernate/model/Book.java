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

	private String title;
 
    private LocalDate date;
 
    private LocalTime time;
 
    private LocalDateTime dateTime;
 
    private OffsetTime offsetTime;
 
    private OffsetDateTime offsetDateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    // -- atributes under study -- 
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public LocalDateTime getDateTime() {  return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public OffsetTime getOffsetTime() { return offsetTime; }
    public void setOffsetTime(OffsetTime offsetTime) { this.offsetTime = offsetTime; }
    public OffsetDateTime getOffsetDateTime() { return offsetDateTime; }
    public void setOffsetDateTime(OffsetDateTime offsetDateTime) { this.offsetDateTime = offsetDateTime; }

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
		return "Book [id=" + id + ", version=" + version + ", title=" + title
				+ ", date=" + date + ", time=" + time + ", dateTime=" + dateTime
                + ", offsetTime="+ offsetTime +", offsetDateTime="+ offsetDateTime
				+ "]";
	}    
}