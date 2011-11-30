package cz.cvut.felk.via.resources.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import cz.cvut.felk.via.data.CommentInfo;
import cz.cvut.felk.via.data.Event;
import cz.cvut.felk.via.datalayer.Xom;
import cz.cvut.felk.via.resources.EventResource;

public class EventResourceImpl extends ServerResource implements EventResource {

	private static Event test = null;
	private static final Logger logger = Logger.getLogger(EventResourceImpl.class.getName());
	
	@Get
	public ArrayList<Event> findEvents() {
		@SuppressWarnings("unused")
		Map<String, String> searchParameters = getParameters();
		Xom xom = new Xom();
		ArrayList<Event> events = xom.getCVUTEvents();
		logger.log(Level.INFO,events.toString());
		/*ArrayList<Event> events2 = new ArrayList();
		for (int i = 0; i < 8; ++i)	{
			events2.add(events.get(i));
		//	events.add(new Event());
		}*/
	//	events.addAll(events2);
	/*	Event e = events.get(0);
		events.clear();
		events.add(e);*/
		return events;
     /*   ArrayList<Event> ret = new ArrayList<Event>();
        Event e = new Event();
        e.setCategory("sport");
        e.setEventOrganiser("CVUT");
        e.setId(null);
        e.setLatitude(null);
        e.setLongitude(null);
        e.setLongDescription(null);
        e.setShortDescription("Super sport event.");
        e.setStartEvent(new Date(2012,10,1));
        e.setStopEvent(null);
        e.setComments(null);
        ret.add(e);
		return ret;		*/
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
