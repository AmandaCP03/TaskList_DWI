package edu.ifsp.modelo;

import java.util.Date;

//A iniciar
//Em andamento
//Concluída

public class Task {
	private String text;
	private Date deadline;
	private String status;
	private int id;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date date) {
		this.deadline = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
