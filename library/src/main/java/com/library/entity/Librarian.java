package com.library.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "librarians")
public class Librarian extends UserAbstract {
		
	@Column(name = "position", length = 50)
	private String position;
		
	@Column(name = "education", length = 150)
	private String education;
		
	@OneToMany(mappedBy = "librarian", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reading> readingList;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public List<Reading> getReadingList() {
		return readingList;
	}

	public void setReadingList(List<Reading> readingList) {
		this.readingList = readingList;
	}

	public Librarian() {
		super();
	}
	public Librarian(long id, String login, String password, String name, String surname, String telephone, String adress,
			String position, String education, List<Reading> readingList) {
		super(id, login, password, name, surname, telephone, adress);
		this.position = position;
		this.education = education;
		this.readingList = readingList;
	}
}
