package com.sdz.Controles;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdz.Beans.Etudiant;
import com.sdz.database.Operations_Etudiant;
import com.sdz.database.Operations_Prof;

public class ControleEtd {

	private Map<String,String> errors;
	
	public ControleEtd(){
		
	}
	
	private void CheckNum(String num) throws Exception{
		if(num == null || num.isEmpty()) throw new Exception("Veuillez saisir votre numéro svp ! ");
	}	
	
	private void CheckPassword(String passe)throws Exception{
		if( passe == null || passe.isEmpty()) throw new Exception("Veuillez Saisir votre mot de passe svp !");
	}
	
	public Etudiant CheckEtd(HttpServletRequest request){
		
		String num = request.getParameter("num");
		String password = request.getParameter("passe");
		Etudiant etd = null;
		
		errors = new HashMap<String,String>();
		
		try {
			CheckNum(num);
		} catch (Exception e) {
			errors.put("num", e.getMessage());
		}
		
		try{
			CheckPassword(password);
		}catch(Exception e){
			errors.put("passe", e.getMessage());
		}
		
		if( errors.isEmpty()){
			try {
				etd = Operations_Etudiant.getEtdByNum(num);
				if( etd != null){
					if(!etd.getPasse().equals(password)) errors.put("passe", "mot de passe incorrect !");
				}else errors.put("num", "Numèro ètudiant inconnue !");
				
			} catch (Exception e) {
				errors.put("query", e.getMessage());
			}
			
			if( errors.isEmpty()) return etd;
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
