package cz.cvut.felk.via.datalayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class DatabaseHandler {
	
    private static final Logger logger = Logger.getLogger(DatabaseHandler.class.getName());
	private static final PersistenceManagerFactory pmFactory = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	public boolean addEvent(EventJDO e)	{
		logger.log(Level.INFO,"addEvent called");
		PersistenceManager pm = null;
		try {
			pm = pmFactory.getPersistenceManager();
			pm.makePersistent(e);
		} catch (Exception ex)	{
			logger.log(Level.SEVERE, "addEvent exception: "+ex.getMessage());
			return false;
		} finally {
			pm.close();
		}
		
		return true;
	}
	
	public boolean addEvents(ArrayList<EventJDO> events)	{
		logger.log(Level.INFO,"addEvents called");
		PersistenceManager pm = null;
		try {
			pm = pmFactory.getPersistenceManager();
			for (EventJDO e : events)	{
				pm.makePersistent(e);
			}
		} catch (Exception ex)	{
			logger.log(Level.SEVERE, "addEvents exception: "+ex.getMessage());
			return false;
		} finally {
			pm.close();
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<EventJDO> searchEvents(Map<String, String> parameters)	{
		logger.log(Level.INFO,"searchEvents called");
		PersistenceManager pm = null;
		ArrayList<EventJDO> foundEvents = null;
		try {
			pm = pmFactory.getPersistenceManager();
		//	Query newQuery = pm.newQuery("SELECT FROM EventJDO WHERE "+query);		
		//	List<EventJDO> result = (List<EventJDO>) newQuery.execute();
		//	foundEvents = new ArrayList(result);
		} catch (Exception ex)	{
			logger.log(Level.SEVERE, "searchEvents exception: "+ex.getMessage());
			return null;
		} finally {
			pm.close();
		}
			
		return foundEvents;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isCvutEventRecorded(EventJDO e) 	{
		logger.log(Level.INFO,"isCvutEventRecorded called");
		PersistenceManager pm = null;
		try {
			pm = pmFactory.getPersistenceManager();
			Query query = pm.newQuery(EventJDO.class);
			query.setFilter("eventOrganiser == p1 && category == p2 && shortDescription == p3 && startEvent == p4");
			query.declareParameters("String p1, String p2, String p3, Date p4");
			query.declareImports("import com.google.appengine.api.datastore.Text; import java.util.Date;");
			Object[] parameters = { e.getEventOrganiser(), e.getCategory(), e.getShortDescription().getValue(), e.getStartEvent() };
			List<EventJDO> result = (List<EventJDO>) query.executeWithArray(parameters);
			pm.close();
			if (result.isEmpty())	{
				return false;
			} else {
				return true;
			}
		} catch (Exception ex)	{
			logger.log(Level.SEVERE, "isCvutEventRecorded exception: "+ex.getMessage());
			return true;
		} finally {
			pm.close();
		}
	}
	
	public boolean updateEvent(EventJDO e)	{
		// TODO implement
		return false;
	}
	
	public boolean deleteEvent(EventJDO e)	{
		// TODO implement
		return false;
	}
	
	
}
