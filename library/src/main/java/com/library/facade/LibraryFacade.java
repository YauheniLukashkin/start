package com.library.facade;

import java.util.List;
import com.library.entity.Book;
import com.library.entity.Reading;

public class LibraryFacade {
	private List<Reading> readNow;
	private List<Reading> history;
	private List<Book> booksLibrary;
	public List<Reading> getReadNow() {
		return readNow;
	}
	public void setReadNow(List<Reading> readNow) {
		this.readNow = readNow;
	}
	public LibraryFacade(List<Reading> readNow, List<Reading> history, List<Book> booksLibrary) {
		super();
		this.readNow = readNow;
		this.history = history;
		this.booksLibrary = booksLibrary;
	}
	public List<Reading> getHistory() {
		return history;
	}
	public void setHistory(List<Reading> history) {
		this.history = history;
	}
	public List<Book> getBooksLibrary() {
		return booksLibrary;
	}
	public void setBooksLibrary(List<Book> booksLibrary) {
		this.booksLibrary = booksLibrary;
	}
	public LibraryFacade() {
		super();
		// TODO Auto-generated constructor stub
	}
}
