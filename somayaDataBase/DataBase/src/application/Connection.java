package application;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
	 public java.sql.Connection getConnection() throws SQLException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        
	        java.sql.Connection connection = null;
	        try {
	            connection = DriverManager.getConnection(
	                    "jdbc:mysql://127.0.0.1:3306/clinicmanagementsystem?sslmode=require",
	                    "root", "1234EE");
	            System.out.println("Connected to the database!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return connection;
	    }

}
