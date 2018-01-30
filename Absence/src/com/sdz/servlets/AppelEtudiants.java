package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Etudiant;
import com.sdz.Beans.Seance;
import com.sdz.Controles.ControleSeance;
import com.sdz.database.Operations_Groupe;
import com.sdz.database.Operations_seances;

public class AppelEtudiants extends HttpServlet {
	
	private int id_seance;
	private int id_groupe;
	private Seance s;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		id_seance = Integer.valueOf(request.getParameter("id_seance"));	
		int count;
		try {
			if(ControleSeance.exist(id_seance) == 1){
				request.setAttribute("found", "Vous avez déjà fait l'appel pour cette séance !");
			}else{
				id_groupe = Operations_seances.getGroupeID(id_seance);
				s = Operations_seances.getSeanceByID(id_seance);
				List<Etudiant> etudiants = Operations_Groupe.getStudents(id_groupe);	
				
				count = etudiants.size();
				String[] ids = new String[count];
				String id;
				int i=0;
				
				for(Etudiant e : etudiants){
					id = String.valueOf(e.getId());
					ids[i] = id;
					i = i +1;
				}
				//---
				session.setAttribute("ids", ids);
				
				request.setAttribute("etudiants", etudiants);	
				request.setAttribute("count", count);
				request.setAttribute("date_seance", s.getDate());
				request.setAttribute("heure_seance", s.getHeure());
				request.setAttribute("groupe", id_groupe);
				
				request.setAttribute("id_seance", id_seance);
			}
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Profs/appel_etd.jsp").forward(request, response);
	}
	
	// -- if the user refresh the page, then we have to display the list of students as we already did in the dopost method
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int count;
		try {
			
			List<Etudiant> etudiants = Operations_Groupe.getStudents(id_groupe);
			count = etudiants.size();
			//-----
			request.setAttribute("etudiants", etudiants);	
			request.setAttribute("count", count);
			request.setAttribute("date_seance", s.getDate());
			request.setAttribute("heure_seance", s.getHeure());
			request.setAttribute("groupe", id_groupe);
			
			request.setAttribute("id_seance", id_seance);
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Profs/appel_etd.jsp").forward(request, response);		
	}
}
