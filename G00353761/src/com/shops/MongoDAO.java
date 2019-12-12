package com.shops;
import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mysql.jdbc.PreparedStatement;

import static com.mongodb.client.model.Projections.*;

import java.util.ArrayList;


public class MongoDAO {
	
	String mongoDB = "storeHeadOfficeDB";
	String mongoCollection = "storeHeadOffice";
	
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	//Constructor
	public MongoDAO() throws Exception {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}
	
	// load HeadOffices
	public ArrayList<HeadOffice> loadHeadOffices() throws Exception {
		
		ArrayList<HeadOffice> hOffices = new ArrayList<HeadOffice>();
		Gson gson = new Gson();

		FindIterable<Document> headOffices = collection.find();
		
		for( Document d : headOffices) {
			HeadOffice h = gson.fromJson(d.toJson(), HeadOffice.class);
			hOffices.add(h);
		} 
		return hOffices;
	
	}

	//add HeadOffices
	public void addHeadOffice(HeadOffice h) throws Exception{
		ArrayList<HeadOffice> hOffices = loadHeadOffices();
		
		Document query = new Document("_id", h._id).
                append("location", h.location);
            collection.insertOne(query);
            System.out.println("Document inserted successfully");
		
	}

	

}
