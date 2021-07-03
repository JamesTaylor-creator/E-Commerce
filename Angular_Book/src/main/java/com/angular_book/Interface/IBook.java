package com.angular_book.Interface;

/**
 * Assures that there will be a type for the given parameters in the method
 * @author thabi
 *
 * @param <T>
 */
public interface IBook<T> {
	final static Long id = 3000L;
	void add(T book);
	void remove(T book);
	void update(T book);
}
