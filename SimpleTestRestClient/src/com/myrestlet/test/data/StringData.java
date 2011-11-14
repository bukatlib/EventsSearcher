package com.myrestlet.test.data;

import java.io.Serializable;

public class StringData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Long stringId;
	public String value;
	
	public StringData()	{ }
	
	public StringData(Long stringId, String value) {
		super();
		this.stringId = stringId;
		this.value = value;
	}
}
