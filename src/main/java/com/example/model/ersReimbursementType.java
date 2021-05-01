package com.example.model;

public class ersReimbursementType {
	enum type{
		FOOD,
		GAS,
		TAXI,
		BUS,
		AIRTRAVEL,
		CLOTHING,
		OTHER
	}
	private int id;
	private type ersType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public type getErsType() {
		return ersType;
	}
	public void setErsType(type ersType) {
		this.ersType = ersType;
	}
	public ersReimbursementType(type ersType) {
		super();
		this.ersType = ersType;
	}
	public ersReimbursementType(int id, type ersType) {
		super();
		this.id = id;
		this.ersType = ersType;
	}
	@Override
	public String toString() {
		return "ersReimbursementType [id=" + id + ", ersType=" + ersType + "]";
	}
	
}
