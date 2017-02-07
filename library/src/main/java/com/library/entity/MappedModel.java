package com.library.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MappedModel {
	@Id
	@Column(name = "id", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MappedModel() {
		super();
	}

	public MappedModel(long id) {
		super();
		this.id = id;
	}
		
}
