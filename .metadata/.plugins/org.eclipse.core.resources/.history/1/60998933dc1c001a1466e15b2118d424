package com.shops;

import java.sql.SQLException;
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
	public void loadProducts(int id) {
		try {
			products = dao.loadProducts(id);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public String deleteProduct(Product p) {
		System.out.println(p.getPid());
		System.out.println(p.getSid());
		System.out.println(p.getProdName());
		System.out.println(p.getPrice());
		
		try {
			dao.deleteProduct(p);
			return "ManageProducts";
		}
		 catch(CommunicationsException e) {
			FacesMessage message = 
			new FacesMessage("Error: Can't communicate with DB");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}