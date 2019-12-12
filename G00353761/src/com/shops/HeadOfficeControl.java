package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mysql.jdbc.CommunicationsException;
import com.sun.jdi.request.DuplicateRequestException;

@ManagedBean
@SessionScoped
public class HeadOfficeControl extends StoreControl {

	MongoDAO mongoDao;
	ArrayList<HeadOffice> headOffices;
	DAO dao;
	ArrayList<Store> stores;

	public ArrayList<HeadOffice> getHeadOffices() {
		return headOffices;
	}

	public ArrayList<Store> getStores() {
		return stores;
	}

	public HeadOfficeControl() {
		super();
		try {
			mongoDao = new MongoDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//CHECK IF STORE ID EXISTS
	public boolean checkStoreId(HeadOffice h) {
		boolean found = false;
		try {
			stores = dao.loadStores();
			for (int i = 0; i <= stores.size(); i++) {
				System.out.println(stores.get(i).id);
				if (stores.get(i).id == h._id) {
					found = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}

//LOAD HEAD OFFICES
	public void loadHeadOffices() throws Exception {
		System.out.println("In loadHeadOffices()");
		try {
			headOffices = mongoDao.loadHeadOffices();
		}catch (CommunicationsException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Mongo Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

//ADD HEAD OFFICE	
	public String addHeadOffice(HeadOffice h) {
		System.out.println("In addHeadOffice()");

		System.out.println(h.get_id());
		System.out.println(h.getLocation());

		// System.out.println("found store, attempting to add");
		try {
			mongoDao.addHeadOffice(h);
			
			return "ManageHeadOffices";
		} catch (MongoWriteException  e) {
			FacesMessage message = new FacesMessage("Error: Store ID already has a Location");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (CommunicationsException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Mongo Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return null;
	}
}
