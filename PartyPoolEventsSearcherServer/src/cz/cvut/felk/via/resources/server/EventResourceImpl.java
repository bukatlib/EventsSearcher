package cz.cvut.felk.via.resources.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import cz.cvut.felk.via.data.Event;
import cz.cvut.felk.via.resources.EventResource;

public class EventResourceImpl extends ServerResource implements EventResource {

	private static Event test = null;
	
	@Get
	public List<Event> findEvents() {
		@SuppressWarnings("unused")
		Map<String, String> searchParameters = getParameters();
		List<Event> ret = new ArrayList<Event>();
		ret.add(test);
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
