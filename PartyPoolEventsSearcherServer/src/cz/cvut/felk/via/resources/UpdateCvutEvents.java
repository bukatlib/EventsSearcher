/**
 * 
 */
package cz.cvut.felk.via.resources;

import org.restlet.resource.Get;

/**
 * @author Libor Bukata
 * Cvut update scheduled from Cron.
 */
public interface UpdateCvutEvents {
	
	/**
	 * Update DB and add new CVUT events.
	 */
	@Get
	public void refreshCvutEvents();
}
