package cz.cvut.felk.via.resources.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import cz.cvut.felk.via.resources.UpdateCvutEvents;

public class UpdateCvutEventsImpl extends ServerResource implements UpdateCvutEvents {

	private static final Logger logger = Logger.getLogger(UpdateCvutEventsImpl.class.getName());	
	
	@Override
	@Get
	public void refreshCvutEvents() {
		logger.log(Level.INFO,"Autocron called!");
	}
}
