package com.sdz.Beans;

import java.sql.Date;

public class Seance {

	private int id;
	private Date date;
	private String heure;
	private int appel;
	
	public int getAppel() {
		return appel;
	}

	public void setAppel(int appel) {
		this.appel = appel;
	}

	public Seance(){}

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
	
}
