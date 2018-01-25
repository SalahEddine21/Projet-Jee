package com.sdz.Beans;

public class Dashboard {
	private int id_seance ;
	private String heure_seance ;
	private int id_groupe ;
	private String titre_module ;
	private String nom_prof ;
	
	public int getId_seance() {
		return id_seance;
	}
	public void setId_seance(int id_seance) {
		this.id_seance = id_seance;
	}
	public String getHeure_seance() {
		return heure_seance;
	}
	public void setHeure_seance(String heure_seance) {
		this.heure_seance = heure_seance;
	}
	public int getId_groupe() {
		return id_groupe;
	}
	public void setId_groupe(int id_groupe) {
		this.id_groupe = id_groupe;
	}
	public String getTitre_module() {
		return titre_module;
	}
	public void setTitre_module(String titre_module) {
		this.titre_module = titre_module;
	}
	public String getNom_prof() {
		return nom_prof;
	}
	public void setNom_prof(String nom_prof) {
		this.nom_prof = nom_prof;
	}
	

}
