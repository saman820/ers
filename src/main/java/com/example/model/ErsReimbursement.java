package com.example.model;

import java.awt.image.BufferedImage;

public class ErsReimbursement {
//	enum currency{
//		USD,
//		CAD,
//		EURO,
//		OTHER
//	}

	

	private int id;
	private double amount;
	private String ersCurrency;
	private String submitted;
	private String resolved;
	private String description;
	private BufferedImage receipt;
	private int authorId;
	private String authorUserName;
	private int resolverId;
	private String resolverUserName;
	private int statusId;
	private int typeId;
	
	public String getAuthorUserName() {
		return authorUserName;
	}
	public void setAuthorUserName(String authorUserName) {
		this.authorUserName = authorUserName;
	}
	public String getResolverUserName() {
		return resolverUserName;
	}
	public void setResolverUserName(String resolverUserName) {
		this.resolverUserName = resolverUserName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getErsCurrency() {
		return ersCurrency;
	}
	public void setErsCurrency(String ersCurrency) {
		this.ersCurrency = ersCurrency;
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
	public String getResolved() {
		return resolved;
	}
	public void setResolved(String resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeID) {
		this.typeId = typeID;
	}
	public ErsReimbursement(int id, double amount, String ersCurrency, String submitted, String resolved, String description,
			int authorId, int resolverId, int statusId, int typeID) {
		super();
		this.id=id;
		this.amount = amount;
		this.ersCurrency = ersCurrency;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeID;
	}
	
	public ErsReimbursement(int id, double amount, String ersCurrency, String submitted, String resolved,
			int authorId, int resolverId, int statusId, int typeID) {
		super();
		this.id = id;
		this.amount = amount;
		this.ersCurrency = ersCurrency;
		this.submitted = submitted;
		this.resolved = resolved;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeID;
	}
	
	
	public ErsReimbursement(double amount, String ersCurrency, String description, int authorId, int resolverId,
			int typeId) {
		super();
		this.amount = amount;
		this.ersCurrency = ersCurrency;
		this.description = description;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.typeId = typeId;
	}
	public ErsReimbursement(double amount, String ersCurrency, String description, int resolverId, int typeID) {
		super();
		this.amount = amount;
		this.ersCurrency = ersCurrency;
		this.description = description;
		this.resolverId = resolverId;
		this.typeId = typeID;
	}
	
	public ErsReimbursement(double amount, String ersCurrency, int authorId, int resolverId, int typeID) {
		super();
		this.amount = amount;
		this.ersCurrency = ersCurrency;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.typeId = typeID;
	}
	
	@Override
	public String toString() {
		return "ErsReimbursement [id=" + id + ", amount=" + amount + ", ersCurrency=" + ersCurrency + ", submitted="
				+ submitted + ", resolved=" + resolved + ", description=" + description + ", receipt=" + receipt
				+ ", authorId=" + authorId + ", authorUserName=" + authorUserName + ", resolverId=" + resolverId
				+ ", resolverUserName=" + resolverUserName + ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}
	

	
	
	
}
