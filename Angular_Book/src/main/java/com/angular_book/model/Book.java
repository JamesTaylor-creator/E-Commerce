package com.angular_book.model;

import java.io.Serializable;
import java.util.Random;

public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String title = "Great Gatsby";
	private String author = "F. Scott Fitzgerald";
	
	public Book(){
		super();
	}
	
	public Book(String title, String author){
		setTitle(title);
		setAuthor(author);
		id = new Random(1L).nextLong();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "Book: " + title + ", Author: " + author + ", Id: " + id;
	}
}
