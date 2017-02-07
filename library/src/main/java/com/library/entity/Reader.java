package com.library.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "readers")
public class Reader extends UserAbstract {
		
	@Column(name = "workAdress", length = 150)
	private String workAdress;
		
	@OneToMany(mappedBy = "reader", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reading> readingList;

	public String getWorkAdress() {
		return workAdress;
	}

	public void setWorkAdress(String workAdress) {
		this.workAdress = workAdress;
	}

	public List<Reading> getReadingList() {
		return readingList;
	}

	public void setReadingList(List<Reading> readingList) {
		this.readingList = readingList;
	}

	public Reader(long id, String login, String password, String name, String surname, String telephone, String adress,
			String workAdress, List<Reading> readingList) {
		super(id, login, password, name, surname, telephone, adress);
		this.workAdress = workAdress;
		this.readingList = readingList;
	}

	public Reader() {
		super();
	}
}
