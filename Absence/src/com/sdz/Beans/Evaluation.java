package com.sdz.Beans;

import java.sql.Date;

public class Evaluation {

	private int id;
	private Date date;
	private String heure;
	private int id_mod;
	private String titre_mod;
	
	public String getTitre_mod() {
		return titre_mod;
	}
	public void setTitre_mod(String titre_mod) {
		this.titre_mod = titre_mod;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public int getId_mod() {
		return id_mod;
	}
	public void setId_mod(int id_mod) {
		this.id_mod = id_mod;
	}
	
	
}
