package com.sdz.Beans;

public class Etudiant {

	private int id;
	private String num;
	private String nom;
	private String prenom;
	private String email;
	private int id_groupe;
	private String passe;
	
	public String getPasse() {
		return passe;
	}
	public void setPasse(String passe) {
		this.passe = passe;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId_groupe() {
		return id_groupe;
	}
	public void setId_groupe(int id_groupe) {
		this.id_groupe = id_groupe;
	}
	
	
}
