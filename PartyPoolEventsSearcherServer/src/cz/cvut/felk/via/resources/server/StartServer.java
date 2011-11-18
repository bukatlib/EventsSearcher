package cz.cvut.felk.via.resources.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

/**
 * Start server and assign REST URL's.
 * @author Libor Bukata
 */
public class StartServer extends Application {
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attachDefault(new Directory(getContext(), "war:///"));
		router.attach("/events", EventResourceImpl.class);
		
		return router;
	}
}
