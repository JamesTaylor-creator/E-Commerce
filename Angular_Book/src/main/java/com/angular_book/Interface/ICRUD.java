package com.angular_book.Interface;


public interface ICRUD<T> {
	public void delete(T book);
	public void create(T book);
	public void update(T book);
	public void remove();
}
