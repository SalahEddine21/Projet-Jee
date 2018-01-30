package com.sdz.Controles;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdz.Beans.Admin;


public class ControleAdmin {
	private Map<String,String> errors;
	
	public ControleAdmin(){
		
	}
	
	private void CheckCin(String cin) throws Exception{
		if(cin != null && !cin.isEmpty()){
			if(cin.trim().length() != 8) throw new Exception("CIN non valide !");
		}else throw new Exception("Veuillez saisir votre CIN svp ! ");
	}	
	
	private void CheckPassword(String passe)throws Exception{
		if( passe == null || passe.isEmpty()) throw new Exception("Veuillez Saisir votre mot de passe svp !");
	}
	
	public Admin CheckAdmin(HttpServletRequest request){
		
		String cin = request.getParameter("cin");
		String password = request.getParameter("passe");
		Admin a = null;
		
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
			if(!cin.equals("WB175491")) errors.put("cin", "CIN inconnue !");
			if(!password.equals("test")) errors.put("passe", "mot de passe incorrect !");
			if(errors.isEmpty()){
				a = new Admin();
				a.setCin(cin);
				a.setNom("Admin");
				a.setPrenom("Admin");
			}
		}
		return a;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
}
