package cz.cvut.felk.via.datalayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

import cz.cvut.felk.via.data.CommentInfo;
import cz.cvut.felk.via.data.Event;

@PersistenceCapable
public class EventJDO implements Serializable {
	
	private static final long serialVersionUID = 4674383743754L;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
    @Persistent
    private String eventOrganiser;
    
    @Persistent
    private String category;
    
    @Persistent
    private Text shortDescription;
    
    @Persistent
    private Text longDescription;
     
    @Persistent
    private Date startEvent;
    
    @Persistent
    private Date stopEvent;
    
    @Persistent
    private Long latitude;
    
    @Persistent
    private Long longitude;
    
    @Persistent
    private ArrayList<CommentInfoJDO> comments;

    public EventJDO() { }
    
    public EventJDO(Event e)	{
    	id = e.getId();
    	eventOrganiser = e.getEventOrganiser();
    	category = e.getCategory();
    	shortDescription = new Text(e.getShortDescription());
    	longDescription = new Text(e.getLongDescription());
    	startEvent = e.getStartEvent();
    	stopEvent = e.getStopEvent();
    	latitude = e.getLatitude();
    	longitude = e.getLongitude();
    	if (e.getComments() != null)	{
    		comments = new ArrayList<CommentInfoJDO>();
    		for (CommentInfo ci : e.getComments())	{
    			comments.add(new CommentInfoJDO(ci));
    		}
    	}
    }
    
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

	public Text getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(Text shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Text getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(Text longDescription) {
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

	public ArrayList<CommentInfoJDO> getComments() {
		return comments;
	}

	public void setComments(ArrayList<CommentInfoJDO> comments) {
		this.comments = comments;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}   
}
