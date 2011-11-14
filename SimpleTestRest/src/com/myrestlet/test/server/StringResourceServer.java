package com.myrestlet.test.server;

import java.util.Map;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.myrestlet.test.data.StringData;
import com.myrestlet.test.data.StringResource;
import com.myrestlet.test.datacontrol.DataProvider;

public class StringResourceServer extends ServerResource implements StringResource {
	
	@Get
	public String getString()  {
		String ret = null;
		Form form = Request.getCurrent().getResourceRef().getQueryAsForm();
		Map<String,String> parameters = form.getValuesMap();
		if (parameters.containsKey("stringId"))	{
			Long stringId = Long.parseLong(parameters.get("stringId"));
			ret = DataProvider.getData(stringId);
		}
		return ret;
	}

	@Put
	public void setString(StringData data)	{
		DataProvider.setData(data.stringId, data.value);
	}
	
	@Delete	
	public void removeString()	{
		Form form = Request.getCurrent().getResourceRef().getQueryAsForm();
		Map<String,String> parameters = form.getValuesMap();
		if (parameters.containsKey("stringId"))	{	
			Long stringId = Long.parseLong(parameters.get("stringId"));
			DataProvider.deleteData(stringId);
		}
	}
}
