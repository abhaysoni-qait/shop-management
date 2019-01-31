package com.interfaces.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ProductOperations<P> {
	String addProduct();
	P viewAllProducts();
	String sellProduct();
	ArrayList<P> productSearchByName(String productName);
	ArrayList<P> productSearchByPrice(double price);
}
