package com.Admin.AdminApp.domain;

public class LogIn {
	String Email;
	String Password;
	
	public LogIn() {}
	public LogIn(String email, String password) {
		super();
		Email = email;
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	

}
