package cz.cvut.felk.via.android;

import java.util.Map;
import java.util.Set;

import org.restlet.resource.ClientResource;

import cz.cvut.felk.via.resources.EventResource;

import android.content.Context;
import android.widget.Toast;


public class RestConnection {

	private Context context;
	private ClientResource clientResource = null;
	private EventResource eventResource = null;
	final private static String ServiceURL = "http://1.events-searcher.appspot.com/events";
	
	public RestConnection(Context context) {
		this.context = context;
	}
	
	public void createClientResource()	{
    	System.setProperty("java.net.preferIPv6Addresses", "false");
		try {
			clientResource = new ClientResource(ServiceURL);
			clientResource.setRequestEntityBuffering(true);	// Workaround GAE bug.
			clientResource.setResponseEntityBuffering(true);
			setEventResource(clientResource.wrap(EventResource.class));
		} catch (Exception e) {
			setClientResource(null); setEventResource(null);
        	Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }       			
	}
	
	public void addQueries(final Map<String,String> queries)	{
		Set<String> keys = queries.keySet();
		for (String key : keys)	{
			clientResource.addQueryParameter(key, queries.get(key));
		}
	}

	public void setClientResource(ClientResource clientResource) {
		this.clientResource = clientResource;
	}

	public ClientResource getClientResource() {
		return clientResource;
	}

	public void setEventResource(EventResource eventResource) {
		this.eventResource = eventResource;
	}

	public EventResource getEventResource() {
		return eventResource;
	}
}
