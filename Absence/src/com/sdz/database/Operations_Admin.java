package com.sdz.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdz.Beans.Seances_Admin;

public class Operations_Admin {

	private static String SQL_SELECT_DASHBOARD = " select seances.id_seance as seance, seances.heure_seance as heure, groupes.id_groupe as groupe, groupes.semestre_groupe as semestre, modules.titre_mod as module, professeurs.nom_prof as prof from seances, groupes, modules, professeurs where seances.id_groupe = groupes.id_groupe and seances.id_mod = modules.id_mod and modules.id_prof = professeurs.id_prof and seances.date_seance = ? ";
	
	public static List<Seances_Admin> getData(Date date) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_DASHBOARD,connection,false,date);	
		result = preparedStatement.executeQuery();		
		
		List<Seances_Admin> seances = new ArrayList<Seances_Admin>();
		Seances_Admin sa;
		
		while(result.next()){
			sa = new Seances_Admin();
			sa.setId_seance(result.getInt("seance"));
			sa.setModule(result.getString("module"));
			sa.setHeure(result.getString("heure"));
			sa.setId_groupe(result.getInt("groupe"));
			sa.setSemestre(result.getString("semestre"));
			sa.setProf(result.getString("prof"));
			
			seances.add(sa);
		}
		
		return seances;
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
