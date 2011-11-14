package com.myrestlet.test.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class StartServer extends Application {

	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attachDefault(new Directory(getContext(), "war:///"));
		router.attach("/stringresource", StringResourceServer.class);
		
		return router;
	}
}
