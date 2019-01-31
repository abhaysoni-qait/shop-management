package com.controllers;

import com.models.User;

public class CartOperations {

	//addToCart should return status of product was it added successfully or not 
    //in the product table or not
	private static String addToCart() {
		return "success";
	}
	
	
	//removeFromCart should return the status of product was it
	//successfully removed from product database table or not
	private static String removeFromCart() {
		return "success";
	}

	
	private static String viewCartProducts() {
		return "product List Formatted";
	}
	
}
