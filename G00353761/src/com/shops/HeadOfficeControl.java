package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;
@ManagedBean
public class HeadOfficeControl {

	MongoDAO mongoDao;
	
	ArrayList<HeadOffice> headOffices;
	
	public ArrayList<HeadOffice> getHeadOffices(){
		return headOffices;
	}
	public HeadOfficeControl() {
		super();
		try {
			mongoDao = new MongoDAO();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
//LOAD HEAD OFFICES
	public void loadHeadOffices() throws Exception{
		System.out.println("In loadHeadOffices()");
		try {
			headOffices = mongoDao.loadHeadOffices();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
//ADD HEAD OFFICE	
	public String addHeadOffice(HeadOffice h) {
		System.out.println("In addHeadOffice()");

		System.out.println(h.get_id());
		System.out.println(h.getLocation());
		
		try {
			mongoDao.addHeadOffice(h);
			return "index";
		}catch(SQLIntegrityConstraintViolationException e) {
			FacesMessage message = 
			new FacesMessage("Error: Store ID already has a headOffice");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		catch(CommunicationsException e) {
			FacesMessage message = 
			new FacesMessage("Error: Can't communicate with DB");
			FacesContext.getCurrentInstance().addMessage(null, message);

		}
		catch (Exception e) {
			FacesMessage message = 
			new FacesMessage("Error " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);		
		}
		return null;
	}
}
