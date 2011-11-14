package com.myrestlet.test.data;

import org.restlet.resource.*;


public interface StringResource {
	
	@Get
	public String getString();
	
	@Put
	public void setString(StringData data);
	
	@Delete
	public void removeString();
}
