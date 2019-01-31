package com.controllers;

import java.util.ArrayList;

import com.models.Product;
import com.models.dao.InventoryDAO;

public class InventoryOperations {
	
	public static void addProduct(String name, int price, int qty) {
		InventoryDAO.addProductToInventory(name, price, qty);
	}
	
	
	
}
