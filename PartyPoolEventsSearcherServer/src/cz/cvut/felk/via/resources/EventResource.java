/**
 * 
 */
package cz.cvut.felk.via.resources;

import java.util.List;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

import cz.cvut.felk.via.data.*;

/**
 * @author Libor Bukata
 * Base resource to CRUD operations by events.
 */
public interface EventResource {
	
	@Get
	public List<Event> findEvents();
	
	@Post
	public boolean addEvent(Event e);
	
	@Put
	public boolean updateEvent(Event e);
	
	@Delete
	public Integer deleteEvents();
}

