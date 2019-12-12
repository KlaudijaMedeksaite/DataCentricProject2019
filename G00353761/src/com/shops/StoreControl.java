package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.eclipse.jdt.internal.compiler.lookup.ConstraintExceptionFormula;

import com.mysql.jdbc.CommunicationsException;

@ManagedBean
@SessionScoped
public class StoreControl {
	DAO dao;
	ArrayList<Store> stores;

	public ArrayList<Store> getStores() {
		return stores;
	}

	// CONSTRUCTOR
	public StoreControl() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// LOAD STORES
	public void loadStores() {
		try {
			stores = dao.loadStores();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//check existence of Store
	public boolean checkStores(Store s) {
		boolean found = false;
		try {
			stores = dao.loadStores();
			for(int i = 0; i<stores.size();i++) {
				
				if (stores.get(i).name.equals(s.name)) {
					found = true;
				}
			}
			return found;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}

	//generate ID
	public int genID() {
		int genID = 0;
		
		try {
			stores = dao.loadStores();
			genID = stores.size() + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}	
			
		return genID;
	}
	
	// DELETE STORE
	public String deleteStore(Store s) {
		System.out.println(s.getId());
		System.out.println(s.getName());
		System.out.println(s.getFounded());

		try {
			dao.deleteStore(s);
			return "ManageStores";
		} catch (CommunicationsException e) {
			FacesMessage message = new FacesMessage("Error: Can't communicate with DB");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage(
					"Error: Store " + s.getName() + " has not been deleted from MySQL DB, it contains products");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// ADD STORE
	public String addStore(Store s) {
		System.out.println(genID());
		s.setId(genID());
		System.out.println(s.getName());
		System.out.println(s.getFounded());

		if(checkStores(s) == false) {
			try {
				dao.addStore(s);
				return "ManageStores";
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Can't communicate with DB");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error " + e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
		else {
			FacesMessage message = new FacesMessage("Error: Store Name already exists");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
}
