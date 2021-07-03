package com.angular_book.controller;

import com.angular_book.Interface.IBook;
import com.angular_book.model.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Storage implements IBook<Book> {
	
	private List<Book> books;
	private int book_Count;
	
	public Storage() {
		books = new ArrayList<Book>();
	}
	
	public long getBook_Count() {
		return book_Count;
	}
	
	public ArrayList<Book> getBooks() {
		return (ArrayList<Book>) this.books;
	}
	
	@Override
	public void add(Book book){
		boolean isAdded = false;
		if (books.isEmpty()) {
			books.add(new Book("Found","Margrett Hardix"));
		}
		
		if(books.add(book)) {
			isAdded = true;
			book_Count++;
			System.out.println("Book Added");
		} else if (isAdded == false){
			System.out.println("BookNotAddedException");
		}
	}
	
	@Override
	public void remove(Book book){
		boolean isRemoved = false;
		if(books.remove(book)) {
			isRemoved = true;
			System.out.println("Book Removed");
		}else if (isRemoved == false) {
			System.out.println("BookNotRemovedException");
		}
	}
	
	@Override
	public void update(Book book){
		Iterator<Book> iterate = books.iterator();
		Book book_update;
		boolean isUpdated = false;
		
		while (iterate.hasNext()) {
			book_update = iterate.next();
			if(book_update == book) {
				book_update = book;
				isUpdated = true;
				System.out.println("Book Updated");
			}
		}
		
		if (isUpdated == false) {
			System.out.println("BookNotUpdatedException");
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		
		Storage store = new Storage();
		CRUD crud = new CRUD();
		Book hamlet = new Book("Hamlet", "Shakespear");
		Book catcher = new Book("Catcher in The Rye" , "J.D. Salinger");
		Book found = new Book("Found", "Margret Hardix");
		Book sherlock = new Book("Sherlock Holmes", "Sir Author Conan Doyle");
		Book deleteDefault = new Book("Default", "Delete");
		Book updateBook = new Book("Adventures of Tom Sawyer", "James Twain");
		
		/*crud.create(hamlet);
		crud.create(catcher);
		crud.create(found);
		crud.create(sherlock);
		crud.create(deleteDefault);
		crud.create(updateBook);*/
		
		//crud.delete(deleteDefault);
		//updateBook.setAuthor("Mark Twain");
		//crud.update(updateBook);
		//crud.save_File();
		//crud.read_File();
		
	}
}