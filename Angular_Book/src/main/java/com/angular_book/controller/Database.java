package com.angular_book.controller;

import org.bson.Document;

import com.angular_book.exceptions.BookNotFoundException;
import com.angular_book.model.Book;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.*;

public class Database{
	MongoClient mongoClient = null;
	MongoDatabase mongoDatabase = null;
	MongoCollection<Document> mongoCollection = null;
	
	public Database() 
	{
		super();
	}
	
	public boolean connect() {
		boolean isRunning = true;
		try {
			mongoClient = new MongoClient("localhost",27017);
			mongoDatabase = mongoClient.getDatabase("Angular");
			mongoCollection = mongoDatabase.getCollection("Books");
			System.out.println("Connected to MongoDB");
		} 
		catch (MongoException | NullPointerException e) {
			System.out.println("MongodbConnectionException");
			isRunning = false;
		} 
		return isRunning;
	}
	
	public void write_Database(ArrayList<Book> books) {
		List<Document> document_List = new ArrayList<Document>();
		try {
			for (Book book : books) {
				if (book != null) {
					document_List.add(new Document("Title", book.getTitle()).append("Author", book.getAuthor()));
				}
			}
			mongoCollection.insertMany(document_List);
			System.out.println("Database Write Successful");
		} 
		catch (MongoException | NullPointerException e) {
			System.out.println("MongodbWriteException");
		}
		finally {
			document_List.removeAll(document_List);
		}
	}
	
	public void write_Database(Book book) {
		try {
			if (book != null) {
				mongoCollection.insertOne(new Document("Title", book.getTitle()).append("Author", book.getAuthor()));
				System.out.println("Database Insert Successful");
			} 
		} 
		catch (MongoException e) {
			System.out.println("MongoInsertException: " + book.getTitle());
		}
	}
	
	public void read_Database() {
		MongoCursor<Document> cursor = null;
		try {
			cursor = mongoCollection.find().iterator();
			while(cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
		}
		catch (MongoException | NullPointerException e) {
			System.out.println("MongoDeleteException");
		} 
		finally {
			cursor.close();
		}
	}
	
	public boolean delete_Book(Book book) throws BookNotFoundException {
		boolean isDelete = true;
		try {
			mongoCollection.deleteOne(Filters.eq("Title", book.getTitle()));
			System.out.println("Book: " + book.getTitle() + " Deleted");
		}
		catch (MongoException | NullPointerException e) {
			System.out.println("MongoDeleteException");
			isDelete = false;
		}
		return isDelete;
	}
	
	public boolean update_Book(Book book) throws BookNotFoundException {
		boolean isUpdate = true;
		try {
			mongoCollection.updateOne(Filters.eq("Title", book.getTitle()), new Document("$set", new Document("Title", book.getTitle()).append("Author", book.getAuthor())));
			System.out.println("Update " + book.getTitle() + " " + book.getAuthor() + " Successful");
		} 
		catch (MongoException | NullPointerException e) {
			System.out.println("MongoUpdateException");
			isUpdate = false;
		}
		return isUpdate;
	}
	
	public void close_Database() 
	{
		if (mongoClient != null) {
			mongoClient.close();
			System.out.println("MongoDB Database Closed");
		}
	}
}
