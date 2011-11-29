/**
 * 
 */
package cz.cvut.felk.via.resources;

import java.util.ArrayList;

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
	
	/**
	 * Events are found by GET parameters.
	 * @return Query list of events.
	 */
	@Get
	public ArrayList<Event> findEvents();
	
	/**
	 * Add event if not exist.
	 * @param e Event to add.
	 * @return True if event was added.
	 */
	@Post
	public boolean addEvent(Event e);
	
	/**
	 * Update event.
	 * @param e Event to be updated.
	 * @return True if event was updated.
	 */
	@Put
	public boolean updateEvent(Event e);
	
	/**
	 * Delete events by DELETE parameters.
	 * @return Number of deleted events.
	 */
	@Delete
	public Integer deleteEvents();
}

