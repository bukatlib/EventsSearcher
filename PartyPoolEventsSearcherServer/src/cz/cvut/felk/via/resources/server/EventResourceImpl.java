package cz.cvut.felk.via.resources.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import cz.cvut.felk.via.data.CommentInfo;
import cz.cvut.felk.via.data.Event;
import cz.cvut.felk.via.resources.EventResource;

public class EventResourceImpl extends ServerResource implements EventResource {

	private static Event test = null;
	
	@Get
	public ArrayList<Event> findEvents() {
		@SuppressWarnings("unused")
		Map<String, String> searchParameters = getParameters();
		ArrayList<Event> ret = new ArrayList<Event>();
		Event e = new Event();
		e.setCategory("sport");
		e.setEventOrganiser("CVUT");
		e.setId(new Long(58));
		e.setLatitude(new Long(0));
		e.setLongitude(new Long(0));
		e.setLongDescription("Long description....");
		e.setShortDescription("Super sport event.");
		e.setStartEvent(new Date(2012,10,1));
		e.setStopEvent(new Date(2012,10,2));
		ArrayList<CommentInfo> comments = new ArrayList<CommentInfo>();
		e.setComments(comments);
		ret.add(e);
		return ret;
	}
	
	@Post
	public boolean addEvent(Event e) {
		test = e;
		return true;
	}

	@Put
	public boolean updateEvent(Event e) {
		test = e;
		return true;
	}
	
	@Delete
	public Integer deleteEvents() {
		test = null;
		return 1;
	}

	/**
	 * Get parameters from URL.
	 * @return HashMap of parameters (parameterName -> value).
	 */
	private Map<String,String> getParameters()	{
		Form form = Request.getCurrent().getResourceRef().getQueryAsForm();
		return form.getValuesMap();		
	}
}
