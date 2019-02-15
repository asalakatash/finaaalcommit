package com.Admin.AdminApp.domain;

public class UsersOnHP {
	String userName;
	String phone;
	String role;
	String loggedin;
	
	public UsersOnHP() {
		
	}
	
	public UsersOnHP(String userName, String phone, String role, String loggedin) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.role = role;
		this.loggedin = loggedin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLoggedin() {
		return loggedin;
	}

	public void setLoggedin(String loggedin) {
		this.loggedin = loggedin;
	}

	
	
	
	
}
