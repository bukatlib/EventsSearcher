package com.myrestlet.test.data;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class StringData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(primaryKey="true")
	public Long stringId;
	
	@Persistent
	public String value;
	
	public StringData()	{ }
	
	public StringData(Long stringId, String value) {
		super();
		this.stringId = stringId;
		this.value = value;
	}
}
