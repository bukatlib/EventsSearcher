package cz.cvut.felk.via.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author Libor Bukata
 * Base data class of event.
 */
@PersistenceCapable
public class Event implements Serializable {

	private static final long serialVersionUID = 4674383743754L;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
    @Persistent
    private String eventOrganiser;
    
    @Persistent
    private String category;
    
    @Persistent
    private String shortDescription;
    
    @Persistent
    private String longDescription;
     
    @Persistent
    private Date startEvent;
    
    @Persistent
    private Date stopEvent;
    
    @Persistent
    private Long latitude;
    
    @Persistent
    private Long longitude;
    
    @Persistent
    private ArrayList<CommentInfo> comments;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventOrganiser() {
		return eventOrganiser;
	}

	public void setEventOrganiser(String eventOrganiser) {
		this.eventOrganiser = eventOrganiser;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Date getStartEvent() {
		return startEvent;
	}

	public void setStartEvent(Date startEvent) {
		this.startEvent = startEvent;
	}

	public Date getStopEvent() {
		return stopEvent;
	}

	public void setStopEvent(Date stopEvent) {
		this.stopEvent = stopEvent;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public ArrayList<CommentInfo> getComments() {
		return comments;
	}

	public void setComments(ArrayList<CommentInfo> comments) {
		this.comments = comments;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}   
}
