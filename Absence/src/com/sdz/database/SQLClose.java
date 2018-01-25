package com.sdz.database;

import java.sql.*;

public class SQLClose {
	
	/* Fermeture silencieuse du resultset */
	public static void fermetureSilencieuse( ResultSet resultSet ) {
		if ( resultSet != null ) {
			try {
			resultSet.close();
			} catch ( SQLException e ) {
			System.out.println( "échec de la fermeture de resultset " + e.getMessage() );
			}
		}
	}
	
	/* Fermeture silencieuse du statement */
	public static void fermetureSilencieuse( Statement statement ) {
		if ( statement != null ) {
			try {
			statement.close();
			} catch ( SQLException e ) {
			System.out.println( "échec de la fermeture du statement" + e.getMessage() );
			}
		}
	}
	
	/* Fermeture silencieuse de la connexion */
	public static void fermetureSilencieuse( Connection connexion ) {
		if ( connexion != null ) {
			try {
			connexion.close();
			} catch ( SQLException e ) {
			System.out.println( "échec de la fermeture du connexion" + e.getMessage() );
			}
		}
	}
	
	/* Fermetures silencieuses du statement et de la connexion */
	public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
		fermetureSilencieuse( statement );
		fermetureSilencieuse( connexion );
	}
	
	/* Fermetures silencieuses du resultset, du statement et de la connexion */
	public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
		fermetureSilencieuse( resultSet );
		fermetureSilencieuse( statement );
		fermetureSilencieuse( connexion );
	}
}
