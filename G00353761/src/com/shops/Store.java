package com.shops;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Store {

	int id;
	String name;
	Date Founded;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getFounded() {
		return Founded;
	}
	public void setFounded(Date founded) {
		Founded = founded;
	}
	
	
	
}
