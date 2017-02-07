package com.library.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
abstract public class UserAbstract extends MappedModel {
		
	@Column(name = "login", unique = true, nullable = false, length = 50)
	private String login;
		
	@Column(name = "password", nullable = false, length = 50)
	private String password;
		
	@Column(name = "name", length = 50)
	private String name;
		
	@Column(name = "surname", length = 50)
	private String surname;
		
	@Column(name = "telephone", length = 50)
	private String telephone;
		
	@Column(name = "adress", length = 150)
	private String adress;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

		
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public UserAbstract(long id, String login, String password, String name, String surname, String telephone, String adress) {
		super(id);
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.telephone = telephone;
		this.adress = adress;
	}
	public UserAbstract() {
		super();
	}
}
