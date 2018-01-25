package com.sdz.Beans;

public class Etudiant_Absence {

	private int id;
	private String num;
	private String nom;
	private String prenom;
	private int present;
	private int justifier;
	
	public int getJustifier() {
		return justifier;
	}
	public void setJustifier(int justifier) {
		this.justifier = justifier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
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
	public int getPresent() {
		return present;
	}
	public void setPresent(int present) {
		this.present = present;
	}
	
	
}
