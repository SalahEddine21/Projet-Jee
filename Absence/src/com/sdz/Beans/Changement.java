package com.sdz.Beans;

import java.sql.Date;

public class Changement {

	private int groupe_src;
	private Date date_ch;
	private String nom,prenom;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getGroupe_src() {
		return groupe_src;
	}
	public void setGroupe_src(int groupe_src) {
		this.groupe_src = groupe_src;
	}
	public Date getDate_ch() {
		return date_ch;
	}
	public void setDate_ch(Date date_ch) {
		this.date_ch = date_ch;
	}
	
	
}
