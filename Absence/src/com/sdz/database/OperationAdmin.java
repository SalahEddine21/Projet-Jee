package com.sdz.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdz.Beans.Dashboard;



public class OperationAdmin {
	private static String SELECT_Date_seance = " select DISTINCT date_seance from seances order by date_seance desc ; ";
	private static String SELECT_DASHBOARD = "select id_seance,heure_seance,id_groupe,titre_mod,nom_prof from seances,modules,professeurs where seances.id_mod=modules.id_mod and modules.id_prof=professeurs.id_prof and date_seance='2018-01-24';";
	
	OperationAdmin(){
		super() ;
	}
	static public List<Date> getDateSeances() throws DaoException{
		
			Connection connection=null ;
			ResultSet result=null;	
			java.sql.Statement statement = null;
			List<Date> maListe = new ArrayList<Date>() ;
		try {
			connection = Connection_Database.getConnecion();
			statement = connection.createStatement();
			result = statement.executeQuery(SELECT_Date_seance);
			while (result.next()) {
			    Date date = result.getDate("date_seance");
			    maListe.add(date) ;
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de données");
		} catch (Exception e) {
			throw new DaoException("Les données de la base sont invalides");
		}
		finally {
	         try {
	             if (connection != null) {
	                 connection.close();  
	             }
	         } catch (SQLException e) {
	             throw new DaoException("Impossible de communiquer avec la base de données");
	         }
	     }
		return maListe ;
	}
	
	static public List<Dashboard> getData(Date date) throws DaoException{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;	
		List<Dashboard> dashList = new ArrayList<Dashboard>();
		
		try {
			connection = Connection_Database.getConnecion();
	        preparedStatement = connection.prepareStatement(SELECT_DASHBOARD);
			preparedStatement.setDate(1, date);
			result = preparedStatement.executeQuery();
			
			while(result.next()){
				Dashboard dash=new Dashboard() ;
				dash.setId_seance(result.getInt("id_seance"));
				dash.setHeure_seance(result.getString("heure_seance"));
				dash.setId_groupe(result.getInt("id_groupe"));
				dash.setTitre_module(result.getString("titre_mod"));
				dash.setNom_prof(result.getString("nom_prof"));	
				dashList.add(dash) ;
			}
		} catch (Exception e) {
	         throw new DaoException("1_Impossible de communiquer avec la base de données");
		}
		finally {
	         try {
	             if (connection != null) {
	                 connection.close();  
	             }
	         } catch (SQLException e) {
	             throw new DaoException("Impossible de communiquer avec la base de données");
	         }
	     }
	        return dashList;
			
	    }
	
	
	}

	
	
