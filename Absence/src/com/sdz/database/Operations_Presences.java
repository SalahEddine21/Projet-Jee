package com.sdz.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdz.Beans.Absence;
import com.sdz.Beans.Etudiant_Absence;

public class Operations_Presences {

	private static String SQL_INSERT_QUERY = "insert into absences (id_etd, id_seance, present, justifier) values (?,?,?,?) ";
	private static String SQL_SELECT_STUDENTS_ABS = "select etudiants.id_etd as id, etudiants.num_etd as num, etudiants.nom_etd as nom, etudiants.prenom_etd as prenom, absences.present as present, absences.justifier as justifier from etudiants, absences where etudiants.id_etd = absences.id_etd and absences.id_seance = ?";
	private static String SQL_UPDATE_PRESENCE = "update absences set present = ? , justifier = ? where id_etd = ? and id_seance = ?";
	private static String SQL_SELECT_SEANCE = "select * from absences where id_seance = ?";
	
	public static void InsertLine(Absence a) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_INSERT_QUERY,connection,false,a.getId_etd(),a.getId_seance(),a.getPresent(),a.getJustifier());
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");	
	}
	
	public static void UpdateLine(Absence a) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_UPDATE_PRESENCE,connection,false,a.getPresent(),a.getJustifier(),a.getId_etd(),a.getId_seance());
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("update data error !");			
	}
	
	public static List<Etudiant_Absence> getStudents_Presences(int id_seance) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_STUDENTS_ABS,connection,false,id_seance);	
		result = preparedStatement.executeQuery();	
		
		List<Etudiant_Absence> etd_abs = new ArrayList<Etudiant_Absence>();
		Etudiant_Absence ea;
		
		while(result.next()){
			ea = new Etudiant_Absence();
			
			ea.setId(result.getInt("id"));
			ea.setNum(result.getString("num"));
			ea.setNom(result.getString("nom"));
			ea.setPrenom(result.getString("prenom"));
			ea.setPresent(result.getInt("present"));
			ea.setJustifier(result.getInt("justifier"));
			
			etd_abs.add(ea);
		}
		return etd_abs;
	}

	public static int CheckExistance(int id_seance) throws Exception{
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_SEANCE,connection,false,id_seance);	
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
