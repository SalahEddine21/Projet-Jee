package com.sdz.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Operations_Changement {

	private static String SQL_INSERT_CHANGEMENT = " insert into changements values (?,?,NOW()) ";
	
	public static void insertLine(int id_etd, int src) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_INSERT_CHANGEMENT,connection,false,id_etd, src);
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");		
	}
	
	//--------------------------------------------------------------------------------------------------------------------------//
	private static PreparedStatement getPreparedStatement(String query,Connection connection,boolean ind,Object...objects) throws Exception{
		try{
			PreparedStatement prestat = connection.prepareStatement(query, ind ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
			for(int i = 0; i < objects.length; i++)
				prestat.setObject(i+1, objects[i]);
			return prestat;
		}catch(SQLException e){
			throw new Exception("problème lors du chargement de la requète !");
		}
	}
}
