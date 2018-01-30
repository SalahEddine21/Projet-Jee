package com.sdz.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdz.Beans.Seance;
import com.sdz.Beans.Seances_Admin;

public class Operations_seances {

	private static String SQL_SELECT_IDGROUPE_SEANCE = "select id_groupe from seances where id_seance = ?";
	private static String SQL_SELECT_SEANCE = "select * from seances where id_seance = ?";
	private static String SQL_CHECK_APPEL_EXISTANCE = "select * from absences where id_seance = ?";
	private static String SQL_DELETE_SC_BYGRP = " delete from seances where id_groupe = ? ";
	private static String SQL_SELECT_ALL_DATA_SC = " select seances.id_seance as seance, seances.heure_seance as heure, seances.date_seance as date, groupes.id_groupe as groupe, groupes.semestre_groupe as semestre, modules.titre_mod as module, professeurs.nom_prof as prof from seances, groupes, modules, professeurs where seances.id_groupe = groupes.id_groupe and seances.id_mod = modules.id_mod and modules.id_prof = professeurs.id_prof";
	private static String SQL_INSERT_SEANCE = " insert into seances (date_seance, heure_seance, id_groupe, id_mod) values (?,?,?,?) ";
	private static String SQL_DELETE_SEANCE = " delete from seances where id_seance = ? ";
	
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
	
	public static void DeleteScGrp(int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_DELETE_SC_BYGRP,connection,false,id_groupe);
		int statuts = preparedStatement.executeUpdate();	
		if (statuts == 0) throw new Exception("cannot insert data");		
	}
	
	public static List<Seances_Admin> SelectAllSc() throws Exception{
		Connection connection=null;
		Statement statement = null;
		ResultSet result=null;			
		
		connection = Connection_Database.getConnecion();
		statement = connection.createStatement();
		result = statement.executeQuery(SQL_SELECT_ALL_DATA_SC);	
		
		List<Seances_Admin> sa = new ArrayList<Seances_Admin>();
		Seances_Admin s;
		
		while(result.next()){
			s = new Seances_Admin();
			s.setId_seance(result.getInt("seance"));
			s.setHeure(result.getString("heure"));
			s.setDate(result.getDate("date"));
			s.setModule(result.getString("module"));
			s.setId_groupe(result.getInt("groupe"));
			s.setSemestre(result.getString("semestre"));
			s.setProf(result.getString("prof"));
			
			sa.add(s);
		}
		return sa;
	}
	
	public static void InsertSc(int groupe, int mod, Date date, String heure) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_INSERT_SEANCE,connection,false, date, heure, groupe, mod);
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");		
	}
	
	public static void DeleteSc(int id_seance) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_DELETE_SEANCE,connection,false, id_seance);
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");			
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
