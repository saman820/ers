package com.example.model;

public class ErsUserRoles {
	enum role{
		FINANCEMANAGER,
		HR,
		SALES,
		ADMINISTRATIVE,
		BOOKKEEPING,
		FINANCE,
		OTHER
	}
	
	private int id;
	private role ersRole;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public role getErsRole() {
		return ersRole;
	}
	public void setErsRole(role ersRole) {
		this.ersRole = ersRole;
	}
	public ErsUserRoles(role ersRole) {
		super();
		this.ersRole = ersRole;
	}
	public ErsUserRoles(int id, role ersRole) {
		super();
		this.id = id;
		this.ersRole = ersRole;
	}
	@Override
	public String toString() {
		return "ErsUserRoles [id=" + id + ", ersRole=" + ersRole + "]";
	}
	
	
}
