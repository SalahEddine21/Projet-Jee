package com.sdz.Controles;

import java.util.ArrayList;
import java.util.List;

import com.sdz.Beans.Evaluation;
import com.sdz.database.Operations_Groupe;

public class ControleEval {

	private List<Evaluation> evaluations_pendantes;
	
	public List<Evaluation> getPending_Eval(int id_groupe) throws Exception{
		
		List<Evaluation> evaluations_groupe = Operations_Groupe.getEvalGroupe(id_groupe);
		evaluations_pendantes = new ArrayList<Evaluation>();
		
		long time = System.currentTimeMillis();
	    java.sql.Date date_current = new java.sql.Date(time);
		
		for(int i=0 ;i<evaluations_groupe.size(); i++){
			java.sql.Date date_eval = evaluations_groupe.get(i).getDate();
			if(date_current.compareTo(date_eval) < 0) evaluations_pendantes.add(evaluations_groupe.get(i));
		}
		
		return evaluations_pendantes;
	}
}
