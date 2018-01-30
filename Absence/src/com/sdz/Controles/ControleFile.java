package com.sdz.Controles;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.Part;

public class ControleFile {

	public static final String CHEMIN = "chemin";
	public static final int TAILLE_TAMPON = 10240;
	
	public static String getNomFichier( Part part ) {
		for ( String contentDisposition : part.getHeader( "content-disposition").split( ";" ) ) {
			if ( contentDisposition.trim().startsWith("filename") ) {
			return contentDisposition.substring(contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
			}
		}
		return null;
	}
	
	public static void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
			sortie = new BufferedOutputStream( new FileOutputStream( new File(chemin+nomFichier) ),TAILLE_TAMPON );
			/*
			* Lit le fichier reçu et écrit son contenu dans un fichier sur le
			* disque.
			*/
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ( ( longueur = entree.read( tampon ) ) > 0 ) {
				sortie.write( tampon, 0, longueur );
			}
			
		} finally {	
			try {
			sortie.close();
			} catch ( Exception ignore ) {
				System.out.print("hiuezfz");
			}
			
			try {
			entree.close();
			} catch ( IOException ignore ) {}
		}
	}
}
