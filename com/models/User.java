package com.models;

public class User {
	private int id;	//unique Identification of user;
	private String name;	//user name
	private String DOB;	//users birth of date
	private String email;	//user email id
	private int mobile;	//user Mobile number;
	private Role role;	//user Role seller/buyer
	private Cart userCart;	//users cart
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	public int getMobile() {
		return mobile;
	}
	public Role getRole() {
		return role;
	}
	
	public String getDOB() {
		return DOB;
	}
	public User(int id, String name, String DOB, String email, int mobile, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.DOB = DOB;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
		this.userCart = new Cart();
	}
	
	
	
}
