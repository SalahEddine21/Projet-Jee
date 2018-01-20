package com.sdz.Controles;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdz.Beans.Professeur;
import com.sdz.database.Operations_Prof;

public class ControleProf {

	private Map<String,String> errors;
	
	public ControleProf(){
		
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

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	
	
}
