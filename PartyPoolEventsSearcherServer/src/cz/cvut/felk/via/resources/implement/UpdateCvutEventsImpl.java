package cz.cvut.felk.via.resources.implement;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import cz.cvut.felk.via.data.Event;
import cz.cvut.felk.via.datalayer.DatabaseHandler;
import cz.cvut.felk.via.datalayer.EventJDO;
import cz.cvut.felk.via.datalayer.Xom;
import cz.cvut.felk.via.resources.UpdateCvutEvents;

public class UpdateCvutEventsImpl extends ServerResource implements UpdateCvutEvents {

	private static final Logger logger = Logger.getLogger(UpdateCvutEventsImpl.class.getName());	
	
	@Override
	@Get
	public void refreshCvutEvents() {
		logger.log(Level.INFO,"Starting to refresh cvut events...");
		Xom xom = new Xom();
		ArrayList<Event> events = xom.getCVUTEvents();
		DatabaseHandler dbHandler = new DatabaseHandler();
		for (Event e : events) {
			if (!dbHandler.isCvutEventRecorded(new EventJDO(e)))	{
				dbHandler.addEvent(new EventJDO(e));
			}
		}
		logger.log(Level.INFO,"Cvut events refresh completed...");
	}
}
