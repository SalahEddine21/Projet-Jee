package com.sdz.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdz.Beans.Changement;
import com.sdz.Beans.Etudiant;
import com.sdz.Beans.Evaluation;
import com.sdz.Beans.Groupe;
import com.sdz.Beans.Seance;

public class Operations_Groupe {
	
	private static String SQL_SELECT_GROUPE_STUDENTS = "select * from etudiants where id_groupe = ?";
	private static String SQL_SELECT_SEANCES = "select * from seances where id_groupe = ?";
	private static String SQL_SELECT_LEAVERS = "select etudiants.nom_etd as nom, etudiants.prenom_etd as prenom, changements.date_ch as date from etudiants, changements where etudiants.id_etd = changements.id_etd and changements.id_groupe_src = ?";
	private static String SQL_SELECT_JOINERS = "select etudiants.nom_etd as nom, etudiants.prenom_etd as prenom, changements.date_ch as date from etudiants, changements where etudiants.id_etd = changements.id_etd and etudiants.id_groupe = ? and changements.id_groupe_src != ?  ";
	private static String SQL_SELECT_GROUPE = "select * from groupes where id_groupe = ?";
	private static String SQL_EVAL_GROUPE = "select evaluations.date_eval as date,evaluations.heure_eval as heure, modules.titre_mod as module from groupes, modules, mod_group, evaluations where groupes.id_groupe=mod_group.id_groupe and mod_group.id_mod = modules.id_mod and modules.id_mod=evaluations.id_mod and groupes.id_groupe = ? ";
	private static String SQL_SELECT_SAME_GRPS = " select id_groupe from groupes where semestre_groupe in (select semestre_groupe from groupes where id_groupe = ?) and id_groupe != ? ";
	
	public static List<Etudiant> getStudents(int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_GROUPE_STUDENTS, connection, false, id_groupe);
		result = preparedStatement.executeQuery();	
		
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		Etudiant e;
		
		while(result.next()){
			e = new Etudiant();
			e.setId(result.getInt("id_etd"));
			e.setNum(result.getString("num_etd"));
			e.setNom(result.getString("nom_etd"));
			e.setPrenom(result.getString("prenom_etd"));
			e.setEmail(result.getString("email_etd"));
			e.setId_groupe(id_groupe);
			etudiants.add(e);
		}
		return etudiants;
	}
	
	public static List<Seance> getSeances(int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_SEANCES, connection, false, id_groupe);
		result = preparedStatement.executeQuery();		
		
		List<Seance> seances = new ArrayList<Seance>();
		Seance s;
		
		while(result.next()){
			s = new Seance();
			s.setId(result.getInt("id_seance"));
			s.setDate(result.getDate("date_seance"));
			s.setHeure(result.getString("heure_seance"));
			s.setAppel(Operations_seances.CheckAppel(s.getId()));
			
			seances.add(s);
		}
		return seances;
	}
	
	public static List<Changement> getLeavers(int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_LEAVERS, connection, false, id_groupe);
		result = preparedStatement.executeQuery();	
		
		List<Changement> etudiants = new ArrayList<Changement>();
		Changement c;		
		
		while(result.next()){
			c = new Changement();
			c.setNom(result.getString("nom"));
			c.setPrenom(result.getString("prenom"));	
			c.setDate_ch(result.getDate("date"));
			
			etudiants.add(c);
		}
		return etudiants;
	}
	
	public static List<Changement> getJoiners(int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_JOINERS, connection, false, id_groupe, id_groupe);
		result = preparedStatement.executeQuery();	
		
		List<Changement> etudiants = new ArrayList<Changement>();
		Changement c;		
		
		while(result.next()){
			c = new Changement();
			c.setNom(result.getString("nom"));
			c.setPrenom(result.getString("prenom"));	
			c.setDate_ch(result.getDate("date"));
			
			etudiants.add(c);
		}
		return etudiants;		
	}
	
	public static Groupe getDataGroupe(int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_GROUPE, connection, false, id_groupe);
		result = preparedStatement.executeQuery();	
		
		Groupe g = new Groupe();
		
		if(result.next()){
			g.setId(result.getInt("id_groupe"));
			g.setSemestre(result.getString("semestre_groupe"));
			g.setAnnee(result.getString("annee_groupe"));
		}
		return g;
	}
	
	public static List<Evaluation> getEvalGroupe(int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_EVAL_GROUPE, connection, false, id_groupe);
		result = preparedStatement.executeQuery();		
		
		List<Evaluation> evaluations = new ArrayList<Evaluation>();
		Evaluation e;
		
		while(result.next()){
			e = new Evaluation();
			e.setTitre_mod(result.getString("module"));
			e.setDate(result.getDate("date"));
			e.setHeure(result.getString("heure"));
			
			evaluations.add(e);
		}
		
		return evaluations;
		
	}
	
	public static List<Groupe> getSameGrps(int id_groupe) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_SAME_GRPS, connection, false, id_groupe, id_groupe);
		result = preparedStatement.executeQuery();		
		
		List<Groupe> grps = new ArrayList<Groupe>();
		Groupe p;
		while(result.next()){
			p = new Groupe();
			p.setId(result.getInt("id_groupe"));
			
			grps.add(p);
		}
		
		return grps;
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
