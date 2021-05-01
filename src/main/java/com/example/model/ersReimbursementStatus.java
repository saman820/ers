package com.example.model;

public class ersReimbursementStatus {
	enum status{
		PENDING,
		MODIFY,
		REJECTED,
		APPROVED,
		OTHER
	}
	private int id;
	private status ersStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public status getErsStatus() {
		return ersStatus;
	}
	public void setErsStatus(status ersStatus) {
		this.ersStatus = ersStatus;
	}
	public ersReimbursementStatus(status ersStatus) {
		super();
		this.ersStatus = ersStatus;
	}
	public ersReimbursementStatus(int id, status ersStatus) {
		super();
		this.id = id;
		this.ersStatus = ersStatus;
	}
	@Override
	public String toString() {
		return "ersReimbursementStatus [id=" + id + ", ersStatus=" + ersStatus + "]";
	}
	
}
