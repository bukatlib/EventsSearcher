package cz.cvut.felk.via.resources.implement;

import java.util.ArrayList;
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

import cz.cvut.felk.via.data.Event;
import cz.cvut.felk.via.datalayer.DatabaseHandler;
import cz.cvut.felk.via.datalayer.EventJDO;
import cz.cvut.felk.via.resources.EventResource;

public class EventResourceImpl extends ServerResource implements EventResource {

	private static final Logger logger = Logger.getLogger(EventResourceImpl.class.getName());
	
	@Get
	public ArrayList<Event> findEvents() {
		logger.log(Level.INFO, "findEvents called");
		Map<String, String> searchParameters = getParameters();
		DatabaseHandler db = new DatabaseHandler();
		return db.searchEvents(searchParameters);
	}
	
	@Post
	public boolean addEvent(Event e) {
		DatabaseHandler db = new DatabaseHandler();
		return db.addEvent(new EventJDO(e));
	}

	@Put
	public boolean updateEvent(Event e) {
		return true;
	}
	
	@Delete
	public Integer deleteEvents() {
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
