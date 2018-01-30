package com.sdz.Controles;
import java.sql.Date;

public class ControleChangement {

	public static int CheckTime(){
		
		long time = System.currentTimeMillis();
		Date current = new Date(time);
		
		String date = "2018-02-10";
		Date bound = Date.valueOf(date);
		
		if(current.compareTo(bound)<0) return 1;
		return 0;
	}
}
