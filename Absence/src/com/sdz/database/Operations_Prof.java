package com.sdz.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdz.Beans.Groupe;
import com.sdz.Beans.Module;
import com.sdz.Beans.Professeur;
import com.sdz.Beans.Seance;

public class Operations_Prof {

	private static String SQL_SELECT_PROF = " select * from professeurs where cin_prof = ? ";
	private static String SQL_SELECT_PROF_BY_ID = " select * from professeurs where id_prof = ? ";
	private static String SQL_SELECT_MODULE_PROF = "select * from modules where id_prof = ? ";
	private static String SQL_SELECT_GROUPES_PROF = " select id_groupe from mod_group where id_mod = ? ";
	private static String SQL_SELECT_SEANCES_PROF = "select * from seances where id_groupe = ? and id_mod = ?";
	private static String SQL_SELECT_MOD_EVAL = " select id_mod as id, titre_mod as titre from modules where id_prof = ? and id_mod in (select id_mod from evaluations) ";
	private static String SQL_INSERT_PROF = "insert into professeurs (cin_prof, nom_prof, prenom_prof, email_prof, pass_prof) values (?,?,?,?,?) ";
	private static String SQL_SELECT_ALL_PROF = " select * from professeurs ";
	private static String SQL_SELECT_GRP_PROF = " select groupes.id_groupe as groupe, groupes.semestre_groupe as semestre from groupes where id_groupe in (select id_groupe from mod_group where id_mod in (select id_mod from modules where id_prof = ?)) ";
	
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
			m.setSemestre(result.getString("semestre_mod"));
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
	
	public static List<Seance> getSeances(int groupe, int module) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		List<Seance> seances = new ArrayList<Seance>();

		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_SEANCES_PROF, connection, false, groupe, module);
		result = preparedStatement.executeQuery();
		
		while(result.next()){
			Seance s = new Seance();
			s.setId(result.getInt("id_seance"));
			s.setDate(result.getDate("date_seance"));
			s.setHeure(result.getString("heure_seance"));
			seances.add(s);
		}
		
		return seances;
	}
	
	public static List<Module> getModules_Eval(int id_prof) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		

		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_MOD_EVAL, connection, false, id_prof);
		result = preparedStatement.executeQuery();		
		
		List<Module> modules = new ArrayList<Module>();
		Module m;
		
		while(result.next()){
			m = new Module();
			m.setId(result.getInt("id"));
			m.setTitre(result.getString("titre"));
			
			modules.add(m);
		}
		return modules;
	}
	
	public static void InsertProf(Professeur prof) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_INSERT_PROF,connection,false,prof.getCin(), prof.getNom(), prof.getPrenom(), prof.getEmail(), prof.getPasse());
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");			
	}
	
	public static List<Professeur> SelectAllProfs() throws Exception{
		Connection connection=null;
		Statement statement = null;
		ResultSet result=null;			
		
		connection = Connection_Database.getConnecion();
		statement = connection.createStatement();
		result = statement.executeQuery(SQL_SELECT_ALL_PROF);
		
		List<Professeur> profs = new ArrayList<Professeur>();
		Professeur prof;
			
		while(result.next()){
			prof = new Professeur();
			
			prof.setId(result.getInt("id_prof"));
			prof.setCin(result.getString("cin_prof"));
			prof.setNom(result.getString("nom_prof"));
			prof.setPrenom(result.getString("prenom_prof"));
			prof.setEmail(result.getString("email_prof"));
			prof.setPasse(result.getString("pass_prof"));
			
			profs.add(prof);
		}	
		return profs;
	}
	
	public static List<Groupe> getGrpProf(int id_prof) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		

		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_GRP_PROF, connection, false, id_prof);
		result = preparedStatement.executeQuery();	
		
		List<Groupe> grps = new ArrayList<Groupe>();
		Groupe p;
		
		while(result.next()){
			 p = new Groupe();
			 p.setId(result.getInt("groupe"));
			 p.setSemestre(result.getString("semestre"));
			 
			 grps.add(p);
		}
		
		return grps;
	}
	
	public static Professeur getProfByID(int id_prof) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;			
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_SELECT_PROF_BY_ID, connection, false, id_prof);
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
