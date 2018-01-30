package com.sdz.Controles;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdz.Beans.Professeur;
import com.sdz.database.Operations_Prof;

public class ControleProf {

	private Map<String,String> errors;
	private int result;
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public ControleProf(){
		
	}
	
	private void CheckName(String name) throws Exception{
		if( name == null || name.isEmpty()) throw new Exception("Veuillez Saisir le nom du prof !");
	}

	private void CheckLastName(String lastname) throws Exception{
		if( lastname == null || lastname.isEmpty()) throw new Exception("Veuillez Saisir le prenom du prof !");
	}

	public void CheckEmail(String email) throws Exception{
		if(email == null || email.isEmpty()) throw new Exception("Veuillez Saisir l'adresse email du prof"+email);
		else{
			if(email.trim().length() != 0){
				if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) throw new Exception("format de l'email non supportè !");
			}else throw new Exception("Veuillez Saisir l'adresse email du prof 1");
		}
	}	
	
	private void CheckCin(String cin) throws Exception{
		if(cin != null && !cin.isEmpty()){
			if(cin.trim().length() != 8) throw new Exception("CIN non valide !");
		}else throw new Exception("Veuillez saisir votre CIN svp ! ");
	}	
	
	private void CheckPassword(String passe)throws Exception{
		if( passe == null || passe.isEmpty()) throw new Exception("Veuillez Saisir votre mot de passe svp !");
	}
	
	public Professeur CheckProf(HttpServletRequest request){
		
		String cin = request.getParameter("cin");
		String password = request.getParameter("passe");
		Professeur prof = null;
		
		errors = new HashMap<String,String>();
		
		try {
			CheckCin(cin);
		} catch (Exception e) {
			errors.put("cin", e.getMessage());
		}
		
		try{
			CheckPassword(password);
		}catch(Exception e){
			errors.put("passe", e.getMessage());
		}
		
		if( errors.isEmpty()){
			try {
				prof = Operations_Prof.findProfByCin(cin);
				if( prof != null){
					if(!prof.getPasse().equals(password)) errors.put("passe", "mot de passe incorrect !");
				}
			} catch (Exception e) {
				errors.put("query", e.getMessage());
			}
			
			if( errors.isEmpty()) return prof;
			else return null;
		}
		return null;
	}

	public void checkProf(Professeur prof) throws Exception{
		
		errors = new HashMap<String,String>();
		
		try {
			CheckCin(prof.getCin());
		} catch (Exception e) {
			errors.put("cin", e.getMessage());
		}
		
		try {
			CheckName(prof.getNom());
		} catch (Exception e) {
			errors.put("nom", e.getMessage());
		}
		
		try {
			CheckLastName(prof.getPrenom());
		} catch (Exception e) {
			errors.put("prenom", e.getMessage());
		}
		
		try {
			CheckEmail(prof.getEmail());
		} catch (Exception e) {
			errors.put("email", e.getMessage());
		}
		
		try {
			CheckPassword(prof.getPasse());
		} catch (Exception e) {
			errors.put("passe", e.getMessage());
		}
		
		if(errors.isEmpty()){
			Operations_Prof.InsertProf(prof);
			result = 1;
		}
		
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	
	
}
