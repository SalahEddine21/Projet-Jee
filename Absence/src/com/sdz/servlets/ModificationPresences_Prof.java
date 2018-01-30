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

public class ModificationPresences_Prof extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//--------
		HttpSession session = request.getSession();
		//-----------
		int id_seance = Integer.valueOf(request.getParameter("id_seance"));
		
		int present, justifier;
		Absence a = new Absence();
		
		try{
			String[] ids = (String[]) session.getAttribute("ids");
			
			for(int i=0 ;i<=ids.length; i++){
				if(request.getParameter(ids[i]) != null) present = 1;
				else present = 0;
				
				if(request.getParameter(String.valueOf(Integer.valueOf(ids[i])+100)) != null) justifier = 1;
				else justifier = 0;
				
				a.setId_etd(Integer.valueOf(ids[i]));
				a.setId_seance(id_seance);
				a.setPresent(present);
				a.setJustifier(justifier);					
				Operations_Presences.UpdateLine(a);
			}
			session.removeAttribute("ids");
		}catch(Exception e){
			request.setAttribute("query", e.getMessage());
		}finally{
			response.sendRedirect(request.getContextPath()+"/Profs/presence_enregitre.jsp");
		}		
	}
}
