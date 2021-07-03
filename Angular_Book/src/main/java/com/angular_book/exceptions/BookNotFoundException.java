package com.angular_book.exceptions;

public class BookNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public BookNotFoundException(String message) {
		super(message);
	}
	
	public final class TitleNotFoundException extends BookNotFoundException{
		
		private static final long serialVersionUID = 3729453045554395085L;

		public TitleNotFoundException() {
			super("TitleNotFoundException");
		}
	
	}
	
	public final class AuthorNotFoundException extends BookNotFoundException{

		private static final long serialVersionUID = -2724355397786615724L;

		public AuthorNotFoundException() {
			super("AuthorNotFoundException");
		}
	}
}
