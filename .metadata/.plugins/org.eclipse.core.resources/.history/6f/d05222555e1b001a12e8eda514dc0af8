package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;

@ManagedBean
@SessionScoped
public class ProductControl {
	DAO dao;
	ArrayList<Product> products;
	
	public ArrayList<Product> getProducts(){
		return products;
	}
	
	public ProductControl() {
		super();
		try {
			dao = new DAO();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadProducts() {
		try {
			products = dao.loadProducts();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}