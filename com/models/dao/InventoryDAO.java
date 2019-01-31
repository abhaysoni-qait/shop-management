package com.models.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Product;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class InventoryDAO {
	private static Connection con = null;

	private InventoryDAO() {

	}

	private static void initializeCon() {
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/shopManagement", "root",
						"1234");
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

	public static boolean addProductToInventory(String name, int price, int qty) {
		boolean status = false;
		try {
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement("INSERT INTO inventory (name, price, quantity) values (?, ?, ?)");
			stmt.setString(1, name);
			stmt.setInt(2, price);
			stmt.setInt(3, qty);
			status = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	private static ArrayList<Product> viewAllProductsInInventory() {
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM inventory");
			ResultSet rs = (ResultSet) stmt.executeQuery();
			return createProductList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String sellProducts(int pid, int qty, int userID) throws SQLException {
		
		ResultSet rs = getProductResultSet(pid);
		rs.next();
		if(pid==rs.getInt(1) && rs.getInt("quantity")>=qty ) {
			
			if(rowsEffected>0) {
				return "success";
			}
		}
		return "some error occured";
	}
	
	private static ResultSet getProductResultSet(int pid) throws SQLException {
		PreparedStatement stmt;
		stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM inventory where pid = ?");
		stmt.setInt(1, pid);
		ResultSet rs = (ResultSet)stmt.executeQuery();
		return rs;
	}
	
	private static int insertSoldProductDetail(ResultSet soldProduct, int userID, int soldQty) throws SQLException{
		PreparedStatement stmt;
		stmt = (PreparedStatement)con.prepareStatement("INSERT INTO productsSold (userID, productID, actualPrice, offeredPrice, qtySOld) values (?,?,?,?,?)");
		stmt.setInt(1, userID);
		stmt.setInt(2, soldProduct.getInt("pid"));
		stmt.setInt(3, soldProduct.getInt("price"));
		stmt.setInt(4, soldProduct.getInt("price")*qty);
		stmt.setInt(5, soldQty);
		int rowsEffected = stmt.executeUpdate();
	}

	private static ArrayList<Product> productSearchByName() {
		return null;
	}

	private static ArrayList<Product> productSearchByPrice() {
		return null;
	}

	private static ArrayList<Product> createProductList(ResultSet rs) throws SQLException{
		ArrayList<Product> productList = new ArrayList<Product>();
		if (rs.next()) {
			productList.add(
					new Product(rs.getInt("pid"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity")));
		}
		return productList;
	}

}
