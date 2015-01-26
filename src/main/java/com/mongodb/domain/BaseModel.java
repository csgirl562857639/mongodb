package com.mongodb.domain;

import org.bson.types.ObjectId;

public class BaseModel {

	private ObjectId _id;
	
	private String addDate;

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	
}
