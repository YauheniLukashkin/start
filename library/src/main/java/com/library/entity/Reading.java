package com.library.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "reading")
public class Reading extends MappedModel {
		
	@Column(name = "dateStart")
	private Date dateStart;
		
	@Column(name = "dateFinish")
	private Date dateFinish;
		
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idLibrarian")
	private Librarian librarian;
		
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idReader")
	private Reader reader;
		
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idBook")
	private Book book;

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public Librarian getLibrarian() {
		return librarian;
	}

	public void setLibrarian(Librarian librarian) {
		this.librarian = librarian;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Reading() {
		super();
	}

	public Reading(long id, Date dateStart, Date dateFinish, Librarian librarian, Reader reader, Book book) {
		super(id);
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.librarian = librarian;
		this.reader = reader;
		this.book = book;
	}
}
