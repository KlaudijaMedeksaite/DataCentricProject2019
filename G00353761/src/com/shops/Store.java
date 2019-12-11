package com.shops;


import java.sql.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Store {

	int id;
	String name;
	String founded;
	int selected;
	
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
	public String getFounded() {
		return founded;
	}
	public void setFounded(String founded) {
		this.founded = founded;
	}
	public int getSelected() {
		return selected;
	}
	public String setSelected(Store selected) {
		System.out.println(getSelected());
		this.selected = selected.id;
		
		return "storeProdDetails";
	}
	
	
	
	
}
