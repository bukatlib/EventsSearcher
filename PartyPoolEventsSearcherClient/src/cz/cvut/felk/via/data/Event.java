package cz.cvut.felk.via.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Libor Bukata
 * Base data class of event.
 */
public class Event implements Serializable {

	private static final long serialVersionUID = 4674383743754L;

    private Long id;

    private String eventOrganiser;
    
    private String category;

    private String shortDescription;

    private String longDescription;

    private Date startEvent;

    private Date stopEvent;

    private Long latitude;

    private Long longitude;

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
