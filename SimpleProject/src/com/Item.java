package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Item {
	
	public Connection connect() throws ClassNotFoundException, SQLException {
		
		Connection con = null ;		
					
		System.out.println("Successfully connected!");
		
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf2021", "root", "");
			
			System.out.println("Successfully connected!");
	
		return con;
				
	}
	
	public String insertItem (String code, String name, String price, String desc) {
		
		String output = "";
		
		try {
			
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database";
				}
		
				String query = " insert into items (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)" + " values (?, ?, ?, ?, ?)";
		
				// create a prepared statement
				PreparedStatement preparedStmt = con.prepareStatement(query);
		
				// binding the new values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, code);
				preparedStmt.setString(3, name);
				preparedStmt.setDouble(4, Double.parseDouble(price));
				preparedStmt.setString(5, desc);
				
				//execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting";
				System.err.println(e.getMessage());
			}
			return output;
	
		}
	
		public String updateItem (String itemID, String code, String name, String price, String desc) {
		
			String output = "";
			
			try {
				
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database";
				}
				
				String query = "update items set itemCode = ?, itemName = ?, itemPrice = ?, itemDesc = ? where itemID = ?" ;
				
				// create a prepared statement
				PreparedStatement preparedStmt = con.prepareStatement(query);
		
				// binding values
				preparedStmt.setString(1, code);
				preparedStmt.setString(2, name);
				preparedStmt.setDouble(3, Double.parseDouble(price));
				preparedStmt.setString(4, desc);
				preparedStmt.setInt(5, Integer.parseInt(itemID));
				
				//execute the statement
				preparedStmt.execute();
				con.close();
				output = "Item ID = " + itemID + " updated successfully";
				
			}
			catch (Exception e)
			{
				output = "Error while updating";
				System.err.println(e.getMessage());
			}
			return output;
	
		}

		public String deleteItem (String itemId) {
			
			int itemID = Integer.parseInt(itemId);
			String output = "";
			
			try {
				
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database";
				}
		
				// create a prepared statement		
				String query = "delete from items where itemID = ?" ;							
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding the value of itemID
				preparedStmt.setInt(1, itemID);
		
				//execute the statement
				preparedStmt.execute();
				con.close();
				output = "Item id = " + itemID + " deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting";
				System.out.println(itemID);
				System.err.println(e.getMessage());
			}
			return output;
	
		}		
				
		public String readItems() {
			
			String output = "";
		
			try {
		
				Connection con = connect();
		
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
		
				// Prepare the HTML table to be displayed
				output = "<table border=‘1’><tr><th>Item Code</th>"
											  +"<th>Item Name</th>"
											  +"<th>Item Price</th>"
											  +"<th>Item Description</th>"
											  +"<th>Update</th>"
											  +"<th>Remove</th></tr>";
				
				String query = "select * from items";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// Iterate through the rows in the result set
				while (rs.next()) {
					
					String itemID = Integer.toString(rs.getInt("itemID"));
					String itemCode = rs.getString("itemCode");
					String itemName = rs.getString("itemName");
					String itemPrice = Double.toString(rs.getDouble("itemPrice"));
					String itemDesc = rs.getString("itemDesc");
				
					// Add into the HTML table
					output += "<tr><td>" + itemCode + "</td>";
					output += "<td>" + itemName + "</td>";
					output += "<td>" + itemPrice + "</td>";
					output += "<td>" + itemDesc + "</td>";
					
					// buttons
					output += "<td><form method='post' action='update.jsp'>"
							+ "<input name='btnUpdate' type='submit' value='Update' class='btn btn-success'>"
							+ "<input name='itemID' type='hidden' value='" + itemID + "'>" 
							+ "<input name='itemCode' type='hidden' value='" + itemCode + "'>" 
							+ "<input name='itemName' type='hidden' value='" + itemName + "'>" 
							+ "<input name='itemPrice' type='hidden' value='" + itemPrice + "'>" 
							+ "<input name='itemDesc' type='hidden' value='" + itemDesc + "'>" +"</form></td>"
							+ "<td><form method='post' action='items.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='itemID' type='hidden' value='" + itemID + "'>" + "</form></td></tr>";
				}
				con.close();
					
				// Complete the HTML table
				output += "</table>";
				
			}
			catch (Exception e)	{
				
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			
			return output;
				
		}
	
}
