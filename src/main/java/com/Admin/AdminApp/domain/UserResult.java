package com.Admin.AdminApp.domain;

import java.math.BigInteger;
import java.util.ArrayList;

public class UserResult {
	private long id;
	private String email;
	private String name;
	private String password;
	private String phone;
	private String login;
	private ArrayList<RoleResult> roles;
	
	public UserResult() {
		
	}
	
	public UserResult(long id, String email, String name, String password, String phone, String login) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.login = login;
	}

	public UserResult(long id, String email, String name, String password, String phone, String login,
			ArrayList<RoleResult> roles) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.login = login;
		this.roles = roles;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public ArrayList<RoleResult> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<RoleResult> roles) {
		this.roles = roles;
	}
	
	
	
	

}
