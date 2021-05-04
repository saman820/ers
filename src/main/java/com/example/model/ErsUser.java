package com.example.model;

public class ErsUser {
	private int id;
	private String userName;
	private String passWord;
	private String fName;
	private String lName;
	private String email;
	private int roleId;
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
		if(userName==null)
			{throw new IllegalArgumentException("username cannot be null");}
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public ErsUser(int id, String userName, String passWord, String fName, String lName, String email, int roleId) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.roleId = roleId;
	}
	
	public ErsUser(String userName, String passWord, String fName, String lName, String email, int roleId) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "ErsUser [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", fName=" + fName
				+ ", lName=" + lName + ", email=" + email + ", roleId=" + roleId + "]";
	}
	
	
}
