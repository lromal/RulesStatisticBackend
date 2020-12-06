package com.lromal.rulesStatistic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rule {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;

	private String notes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String email) {
		this.notes = email;
	}
}
