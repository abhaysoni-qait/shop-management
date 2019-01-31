package com.controllers;

import com.models.User;
import com.models.dao.UserDAO;

public class UserOperations {

	public static User loginUser(String email, String pass) {
		User user = UserDAO.login(email, pass);
		if(user!=null) {
			return user;
		}else {
			return null;
		}
	}

	public static String registerUser(String name, String email, String role, String DOB, int mobile, String pass,
			String repass) {
		if (pass.equals(repass)) {
			boolean status = UserDAO.registerUser(name, pass, email, DOB, role, mobile);
			if (status) {
				return "user successfully registered";
			} else {
				return "Some Error Occured";
			}
		} else {
			return "pass and repass did not match";
		}
	}

	public static void checkOut() {
		
	}

}
