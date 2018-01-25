package com.sdz.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdz.Beans.Seance;

public class Operations_seances {

	private static String SQL_SELECT_IDGROUPE_SEANCE = "select id_groupe from seances where id_seance = ?";
	private static String SQL_SELECT_SEANCE = "select * from seances where id_seance = ?";
	private static String SQL_CHECK_APPEL_EXISTANCE = "select * from absences where id_seance = ?";
	
	public static int getGroupeID(int id_seance) throws Exception{
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_IDGROUPE_SEANCE, connection, false, id_seance);
		result = preparedStatement.executeQuery();		
		result.next();
		
		int id_groupe = Integer.valueOf(result.getString("id_groupe"));
		return id_groupe;
	}
	
	public static Seance getSeanceByID(int id_seance) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_SEANCE, connection, false, id_seance);
		result = preparedStatement.executeQuery();		
		result.next();	
		
		Seance s = new Seance();
		s.setId(id_seance);
		s.setDate(result.getDate("date_seance"));
		s.setHeure(result.getString("heure_seance"));
		
		return s;
	}
	
	public static int CheckAppel(int id_seance) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_CHECK_APPEL_EXISTANCE, connection, false, id_seance);
		result = preparedStatement.executeQuery();		
		
		return result.next() == true ? 1:0;
	}
	//---------------------------------------------------------------------------------------------------------------------------//
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
