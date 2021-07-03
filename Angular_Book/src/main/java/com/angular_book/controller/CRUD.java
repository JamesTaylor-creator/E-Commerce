package com.angular_book.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.angular_book.Interface.ICRUD;
import com.angular_book.exceptions.BookNotFoundException;
import com.angular_book.model.Book;

public class CRUD implements ICRUD<Book>{
	private ArrayList<Book> books = new ArrayList<Book>();
	Database database = new Database();
	
	public CRUD() {
		//Load books from file
		database.connect();
	}
	
	public long getCount() {
		return books.size();
	}
	
	//This is where a list of books will be stored so i can check and see if the book is in the database
	@Override
	public void delete(Book book) 
	{
		try {
			database.delete_Book(book);
			books.remove(book);
		} 
		catch (BookNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		} 
	}
	
	//Create  all these custom message exception class 
	public boolean save_File() {
		FileOutputStream output = null;
		ObjectOutputStream outputStream = null;
		boolean isSaved = true;
		
		try {
			output = new FileOutputStream("temp");
			outputStream = new ObjectOutputStream(output);
			outputStream.writeObject(books);
			outputStream.close();
			output.close();
		} 
		catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			isSaved = false;
		} 
		finally {
			System.out.println("Book Saved To File");
		}
		
		return isSaved;
	}
	
	@SuppressWarnings("unchecked")
	public boolean read_File() {
		boolean isRead = true;
		
		try {
			FileInputStream inputStream = new FileInputStream("temp");
			ObjectInputStream objectStream = new ObjectInputStream(inputStream);
			
			books = (ArrayList<Book>) objectStream.readObject();
			objectStream.close();
			inputStream.close();
		} 
		catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			isRead = false;
		}
		finally {
			for (Book book : books) {
				System.out.println(book.toString());
			}
			System.out.println("Book Read Successful");
		}
		
		return isRead;
	}

	@Override
	public void update(Book book_update) {
		try {
			books.add(book_update);
			database.update_Book(book_update);
		} 
		catch(BookNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void create(Book book) {
		try {
			if (books.contains(book)) {
				System.out.println("Book Already Exists");
			} else {
				books.add(book);
				database.write_Database(book);
			}	
		} 
		catch (NullPointerException e) {
			System.out.println("DatabaseException");
		} 
	}
}
