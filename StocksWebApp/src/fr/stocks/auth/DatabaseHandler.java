package fr.stocks.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseHandler {
	
	Connection conn;
	public static boolean isCo = false;
	
	public DatabaseHandler() {
		
	}
	
	public boolean authUser(String username, String password) {
		String url = "jdbc:mysql://localhost:3306/stocks";
        
		try {
			
			//conn = DriverManager.getConnection(url, username, password)
				//Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url,"root","Hughesk123");
			Statement stmt = conn.createStatement();
			//ResultSet rs = stmt.executeQuery("SELECT * from stockWebJavaAppUsers WHERE username='"+username+"' and password='"+password+"'");
			ResultSet rs = stmt.executeQuery("SELECT * from stockWebJavaAppUsers WHERE username='"+username+"' and password='"+password+"'");
			
			int len = 0;
			while(rs.next()) {
				len++;
			}
			//then you are set to try the connection if it's working you have a mysql server running on your
			// computer then you only have to create a database like the one I sent (or import the one I sent) 
			//and changing the password in the code (auth::DatabaseHandler.java I used "root" and "" as username and password 
			//replace that as well as the name of the database and table I used "test" and stocks)
			
			
			rs.close();
			stmt.close();
			conn.close();
			
			if(len == 1) {
				System.out.println("Conn accepted");
				isCo = true;
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Conn refused");
		return false;
	}
	
}
