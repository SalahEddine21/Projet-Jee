package com.sdz.Controles;

import com.sdz.database.Operations_Presences;

public class ControleSeance {

	public ControleSeance(){
		
	}
	
	public static int exist(int id_seance) throws Exception{
		return Operations_Presences.CheckExistance(id_seance);
	}
}
