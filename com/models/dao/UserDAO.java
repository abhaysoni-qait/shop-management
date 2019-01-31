package com.models.dao;


import java.sql.*;

import com.models.Role;
import com.models.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;


public class UserDAO {
	private static Connection con = null;

	
	
	private UserDAO() {
		//by making this constructor we are stopping any external code from creating
		//instance of this class
	}
	
	
	private static void initializeCon() {
		if(con==null) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/shopManagement","root","1234");  
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean registerUser(String name, String pass, String email, String DOB, String role, int mobile) {
		PreparedStatement stmt = null;
		boolean status = false;

		try {
			stmt = (PreparedStatement) con.prepareStatement("INSERT INTO users (name, email, DOB, role, mobile, pass) values (?,?,?,?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, DOB);
			stmt.setString(4, role);
			stmt.setInt(5, mobile);
			stmt.setString(6, pass);
			status = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	public static User login(String email, String pass) {
		PreparedStatement stmt = null;
			try {
				stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM users where email=? and pass=?");
				stmt.setString(1, email);
				stmt.setString(2, pass);
				ResultSet result = (ResultSet) stmt.executeQuery();
				if(result != null) {
					return createUser(result);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
	}
	
	private static User createUser(ResultSet rs) {
		try {
			String name = rs.getString("name");
			String email = rs.getString("email");
			int mobile = rs.getInt("mobile");
			String DOB = rs.getString("DOB");
			String role = rs.getString("role");
			Role role1 = null;
			if(role.equalsIgnoreCase("regular")) {
				role1=Role.REGULAR;
			}else if(role.equalsIgnoreCase("seller")){
				role1=Role.SELLER;
			}
			int id = rs.getInt("id");
			return new User(id, name, DOB, email, mobile, role1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	                              
	
	
}
