package com.sdz.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdz.Beans.Module;
import com.sdz.Beans.Professeur;

public class Operations_Prof {

	private static String SQL_SELECT_PROF = " select * from professeurs where cin_prof = ? ";
	private static String SQL_SELECT_MODULE_PROF = "select * from modules where id_prof = ? ";
	private static String SQL_SELECT_GROUPES_PROF = " select id_groupe from mod_group where id_mod = ? ";
	
	public static Professeur findProfByCin(String cin)throws Exception{
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;			
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_PROF, connection, false, cin);
		result = preparedStatement.executeQuery();
		
		if(result.next()){
			Professeur prof = new Professeur();
			
			prof.setId(result.getInt("id_prof"));
			prof.setCin(result.getString("cin_prof"));
			prof.setNom(result.getString("nom_prof"));
			prof.setPrenom(result.getString("prenom_prof"));
			prof.setEmail(result.getString("email_prof"));
			prof.setPasse(result.getString("pass_prof"));
			
			return prof;
		}else return null;
	}
	
	public static List<Module> getModules(int id) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;		
		List<Module> modules = new ArrayList<Module>();
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_MODULE_PROF, connection, false, id);
		result = preparedStatement.executeQuery();
		
		while(result.next()){
			Module m = new Module();
			m.setId(result.getInt("id_mod"));
			m.setTitre(result.getString("titre_mod"));
			modules.add(m);
		}
		
		return modules;
	}
	
	public static List<Integer> getGroupes(int id_mod)throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;		
		List<Integer> groupes = new ArrayList<Integer>();
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_GROUPES_PROF, connection, false, id_mod);
		result = preparedStatement.executeQuery();
		
		while(result.next()) groupes.add(result.getInt("id_groupe"));	
		return groupes;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------//
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
