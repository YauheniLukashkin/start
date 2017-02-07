package com.library.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends MappedModel {

	@Column(name = "name", length = 150)
	private String name;

	@Column(name = "author", length = 100)
	private String author;

	@Column(name = "yearPublish")
	private String yearPublish;
		
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reading> readingList;
		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYearPublish() {
		return yearPublish;
	}
	public void setYearPublish(String yearPublish) {
		this.yearPublish = yearPublish;
	}

	public List<Reading> getReadingList() {
		return readingList;
	}

	public void setReadingList(List<Reading> readingList) {
		this.readingList = readingList;
	}
	public Book() {
		super();
	}
	public Book(long id, String name, String author, String yearPublish, List<Reading> readingList) {
		super(id);
		this.name = name;
		this.author = author;
		this.yearPublish = yearPublish;
		this.readingList = readingList;
	}	
}
