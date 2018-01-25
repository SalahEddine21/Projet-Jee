package com.sdz.Beans;

import java.sql.Date;

public class Seance_Presence {

	private Date date;
	private String heure;
	private String titre;
	private int present;
	private int justifier;
	
	public int getJustifier() {
		return justifier;
	}
	public void setJustifier(int justifier) {
		this.justifier = justifier;
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
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getPresent() {
		return present;
	}
	public void setPresent(int present) {
		this.present = present;
	}
	
	
}
