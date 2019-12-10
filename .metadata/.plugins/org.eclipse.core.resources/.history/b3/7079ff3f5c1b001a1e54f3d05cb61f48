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
	
	public String addProduct(Product p) {
		System.out.println(p.getPid());
		System.out.println(p.getSid());
		System.out.println(p.getProdName());
		System.out.println(p.getPrice());
		
		try {
			dao.addProduct(p);
			return "index";
		}catch(SQLIntegrityConstraintViolationException e) {
			FacesMessage message = 
			new FacesMessage("Error: Product ID already exists");
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