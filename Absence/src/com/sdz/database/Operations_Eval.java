package com.sdz.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sdz.Beans.Evaluation;

public class Operations_Eval {

	private static String SQL_INSERT_EVAL = " insert into evaluations (date_eval, heure_eval, id_mod) values (?,?,?) ";
	private static String SQL_SELECT_ID_EVAL = "select id_eval from evaluations where id_mod = ?";
	private static String SQL_INSERT_NOTE_EVAL = "insert into etd_eval values (?,?,?)";
	private static String SQL_CHECK_EVAL_EXISTANCE = "select etd_eval.id_etd from evaluations, etd_eval, etudiants where evaluations.id_eval = etd_eval.id_eval and evaluations.id_mod = ? and etd_eval.id_etd = etudiants.id_etd and etudiants.id_groupe = ? ";
	
	public static void insertEval(Evaluation e) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_INSERT_EVAL,connection,false,e.getDate(), e.getHeure(), e.getId_mod());
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");		
	}
	
	public static int getIdEval(int id_mod) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_ID_EVAL,connection,false,id_mod);	
		result = preparedStatement.executeQuery();		
		
		return result.next() == true ? result.getInt("id_eval"):0;
	}
	
	public static void insertNoteEtd(int id_etd, int id_eval, float note) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_INSERT_NOTE_EVAL,connection,false,id_etd, id_eval, note);
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");		
	}	
	
	public static int checkEvalExistance(int id_mod, int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_CHECK_EVAL_EXISTANCE,connection,false,id_mod, id_groupe);	
		result = preparedStatement.executeQuery();		
		
		if(result.next()) return 1;
		return 0;
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
