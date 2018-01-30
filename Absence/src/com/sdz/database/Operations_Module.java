package com.sdz.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdz.Beans.Module;

public class Operations_Module {

	private static String SQL_INSERT_MODULE = "insert into modules (titre_mod, semestre_mod, id_prof) values (?,?,?) ";
	private static String SQL_SELECT_ALL_MODULES = " select modules.id_mod as module, modules.titre_mod as titre, modules.semestre_mod as semestre, professeurs.nom_prof as prof from modules, professeurs where modules.id_prof = professeurs.id_prof ";
	private static String SQL_DELETE_MODULE = " delete from modules where id_mod = ? ";
	private static String SQL_DELETE_GROUPE_MODULE = " delete from mod_group where id_mod = ? ";
	private static String SQL_DELETE_SEANCE_MODULE = " delete from seances where id_mod = ? ";
	private static String SQL_SELECT_ALL_MODS = "select * from modules";
	
	public static void InsertMod(Module m) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_INSERT_MODULE,connection,false,m.getTitre(), m.getSemestre(), m.getProf());
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");		
	}
	
	public static List<Module> getAllModules() throws Exception{
		Connection connection=null;
		Statement statement = null;
		ResultSet result=null;			
		
		connection = Connection_Database.getConnecion();
		statement = connection.createStatement();
		result = statement.executeQuery(SQL_SELECT_ALL_MODULES);	
		
		List<Module> modules = new ArrayList<Module>();
		Module m;
		
		while(result.next()){
			m = new Module();
			m.setId(result.getInt("module"));
			m.setTitre(result.getString("titre"));
			m.setSemestre(result.getString("semestre"));
			m.setNom_prof(result.getString("prof"));
			
			modules.add(m);
		}
		
		return modules;
		
	}
	
	public static void DeleteModule(int id_mod) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_DELETE_MODULE,connection,false,id_mod);
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");		
	}
	
	public static void DeleteModGrp(int id_mod) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_DELETE_GROUPE_MODULE,connection,false,id_mod);
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");			
	}
	
	public static void DeleteScMod(int id_mod) throws Exception{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		connection = Connection_Database.getConnecion();
		preparedStatement = getPreparedStatement(SQL_DELETE_SEANCE_MODULE,connection,false,id_mod);
		int statuts = preparedStatement.executeUpdate();
		if(statuts == 0) throw new Exception("insert data error !");			
	}
	
	public static void DeleteAll_AboutMod(int id_mod) throws Exception{
		DeleteModule(id_mod);
		DeleteModGrp(id_mod);
		DeleteScMod(id_mod);
	}
	
	public static List<Module> getAllMods() throws Exception{
		Connection connection=null;
		Statement statement = null;
		ResultSet result=null;			
		
		connection = Connection_Database.getConnecion();
		statement = connection.createStatement();
		result = statement.executeQuery(SQL_SELECT_ALL_MODS);	
		
		List<Module> modules = new ArrayList<Module>();
		Module m;
		
		while(result.next()){
			m = new Module();
			m.setId(result.getInt("id_mod"));
			m.setTitre(result.getString("titre_mod"));
			m.setSemestre(result.getString("semestre_mod"));
			
			modules.add(m);
		}
		return modules;
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
