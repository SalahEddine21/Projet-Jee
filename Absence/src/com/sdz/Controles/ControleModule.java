package com.sdz.Controles;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdz.Beans.Module;
import com.sdz.database.Operations_Module;

public class ControleModule {

	private Map<String,String> errors;
	private int result=0;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public void CheckTitre(String titre) throws Exception{
		if(titre == null || titre.isEmpty()) throw new Exception("Veuillez saisir le titre du module !");
	}
	
	public void CheckModule(HttpServletRequest request){
		String titre = request.getParameter("titre");
		String semestre = request.getParameter("semestre");
		int id_prof = Integer.valueOf(request.getParameter("id_prof"));
		
		errors = new HashMap<String,String>();
		Module module = null;
		
		try {
			CheckTitre(titre);
		} catch (Exception e) {
			errors.put("titre", e.getMessage());
		}
		
		if(errors.isEmpty()){
			module = new Module();
			module.setTitre(titre);
			module.setSemestre(semestre);
			module.setProf(id_prof);
			
			try {
				Operations_Module.InsertMod(module);
				result = 1;
			} catch (Exception e) {
				errors.put("query", e.getMessage());
			}
		}
	}
}
