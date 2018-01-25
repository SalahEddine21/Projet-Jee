package com.sdz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Absence;
import com.sdz.Beans.Professeur;
import com.sdz.database.Operations_Presences;

public class PresencesProfs extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//--------
		HttpSession session = request.getSession();
		Professeur prof = (Professeur) session.getAttribute("prof");
		int id_prof = prof.getId();
		//-----------
		int id_min = Integer.valueOf(request.getParameter("id_min"));
		int id_max = Integer.valueOf(request.getParameter("id_max"));
		int id_seance = Integer.valueOf(request.getParameter("id_seance"));
		
		String box_val,box_justifier;		
		int present, justifier;
		Absence a = new Absence();
		
		try{
			for(int i=id_min ;i<=id_max; i++){
				
				box_val = String.valueOf(request.getParameter(String.valueOf(i)));
				if(box_val.equals("on")) present = 1;
				else present = 0;
				
				box_justifier = String.valueOf(request.getParameter(String.valueOf(i+100)));
				if(box_justifier.equals("on")) justifier = 1;
				else justifier = 0;
				
				a.setId_etd(i);
				a.setId_seance(id_seance);
				a.setPresent(present);
				a.setJustifier(justifier);
				Operations_Presences.InsertLine(a);
			}
		}catch(Exception e){
			request.setAttribute("query", e.getMessage());
		}finally{
			response.sendRedirect(request.getContextPath()+"/Profs/presence_enregitre.jsp");
		}
		
	}

}
