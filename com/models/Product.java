package com.models;

public class Product {
	private int pid;  //unique product id for identification
	private String name; //name of product
	private double price; //price of product
	private int qty; //quantity available in inventory
	
	
	public int getPid() {
		return pid;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getQty() {
		return qty;
	}
	
	public Product(int pid, String name, double price, int qty) {
		this.pid=pid;
		this.name=name;
		this.price=price;
		this.qty=qty;
	}
	
}
