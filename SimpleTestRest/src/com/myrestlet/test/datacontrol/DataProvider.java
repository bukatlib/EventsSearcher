package com.myrestlet.test.datacontrol;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.myrestlet.test.data.StringData;


public class DataProvider {
	
    private static final Logger logger = Logger.getLogger(DataProvider.class.getName());
	private static final PersistenceManagerFactory pmFactory = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	public static String getData(Long stringId)	{
		StringData data = null;
		PersistenceManager pm = null;
		logger.log(Level.INFO,"getData("+Long.toString(stringId)+")");
		try {
			pm = pmFactory.getPersistenceManager();
			data = pm.getObjectById(StringData.class, stringId);
		} catch (Exception e)	{
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			pm.close();
		}
		
		if (data != null)	{
			return data.value;
		} else {
			return null;
		}
	}
	
	public static void deleteData(Long stringId)	{
		PersistenceManager pm = null;
		logger.log(Level.INFO, "deleteData("+Long.toString(stringId)+")");
		try {
			pm = DataProvider.pmFactory.getPersistenceManager();
			StringData data = pm.getObjectById(StringData.class, stringId);
			pm.deletePersistent(data);
		} catch (Exception e)	{
			logger.log(Level.SEVERE,e.getMessage());
		} finally {
			pm.close();
		}
	}
	
	public static void setData(Long stringId, String value)	{
		PersistenceManager pm = null;
		logger.log(Level.INFO, "setData("+Long.toString(stringId)+","+value+")");
		try {
			pm = DataProvider.pmFactory.getPersistenceManager();			
			Query query = pm.newQuery(StringData.class, "stringId == "+stringId);
			Collection results = (Collection) query.execute();
			if (results.size() == 1)	{
				// Update element.
				StringData data = (StringData) results.iterator().next();
				data.value = value;
			} else {
				// Add element.
				pm.makePersistent(new StringData(stringId, value));
			}
		} catch (Exception e)	{
			logger.log(Level.SEVERE,e.getMessage());
		} finally {
			pm.close();
		}
	}
}
