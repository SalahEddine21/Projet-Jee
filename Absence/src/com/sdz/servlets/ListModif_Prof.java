package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Etudiant;
import com.sdz.Beans.Etudiant_Absence;
import com.sdz.Beans.Seance;
import com.sdz.database.Operations_Presences;
import com.sdz.database.Operations_seances;

public class ListModif_Prof extends HttpServlet {

	private int id_seance, count,id_min,id_max, id_groupe;
	private List<Etudiant_Absence> etd_abs;
	private Seance s;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		if(etd_abs.isEmpty()) request.setAttribute("not_found", "Vous n'avez pas fait l'appel pour cette seance !");
		else{
			request.setAttribute("etudiants", etd_abs);
			request.setAttribute("groupe", id_groupe);
			request.setAttribute("date_seance", s.getDate());
			request.setAttribute("heure_seance", s.getHeure());
			
			request.setAttribute("count", count);
			request.setAttribute("id_min", id_min);
			request.setAttribute("id_max", id_max);
			request.setAttribute("id_seance", id_seance);			
		}
		this.getServletContext().getRequestDispatcher("/Profs/liste_a_modifier.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		id_seance = Integer.valueOf(request.getParameter("id_seance"));	
		HttpSession session = request.getSession();
		try {
			
			etd_abs = Operations_Presences.getStudents_Presences(id_seance);
			if(etd_abs.isEmpty()) request.setAttribute("not_found", "Vous n'avez pas fait l'appel pour cette seance !");
			else{
				id_groupe = Operations_seances.getGroupeID(id_seance);
				s = Operations_seances.getSeanceByID(id_seance);
				count = etd_abs.size();
				
				request.setAttribute("etudiants", etd_abs);
				request.setAttribute("groupe", id_groupe);
				request.setAttribute("date_seance", s.getDate());
				request.setAttribute("heure_seance", s.getHeure());
				
				request.setAttribute("count", count);
				request.setAttribute("id_seance", id_seance);
				
				String[] ids = new String[count];
				String id;
				int i=0;
				
				for(Etudiant_Absence e : etd_abs){
					id = String.valueOf(e.getId());
					ids[i] = id;
					i = i +1;
				}
				//---
				session.setAttribute("ids", ids);
			}
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Profs/liste_a_modifier.jsp").forward(request, response);	
	}
}
