package com.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

public class ToDo {

	public ToDo(int id, String userName, String description, LocalDate finalDate, boolean completition) {
		super();
		this.id = id;
		this.userName = userName;
		this.description = description;
		this.finalDate = finalDate;
		this.completition = completition;
	}

	private int id;
	private String userName;
	private String description;

	private LocalDate finalDate;
	private boolean completition;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}

	public boolean isCompletition() {
		return completition;
	}

	public void setCompletition(boolean completition) {
		this.completition = completition;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", userName=" + userName + ", description=" + description + ", finalDate=" + finalDate
				+ ", completition=" + completition + "]";
	}

}
