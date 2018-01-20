package com.sdz.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection_Database {
	
	public static Connection connection = null;
	public static Connection MakeConnection() throws Exception{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			throw new Exception("Driver n'est pas correctement charger !");
		}
		
		String url ="jdbc:mysql://localhost:3306/gestion_absences?useSSL=false";
		String user = "root";
		String password = "";
		connection = DriverManager.getConnection(url, user, password);
		return connection;	
	}
	
	public static Connection getConnecion() throws Exception{
		if( connection == null) MakeConnection();
		return connection;
	}
	
}
