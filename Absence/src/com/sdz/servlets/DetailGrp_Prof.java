package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Changement;
import com.sdz.Beans.Etudiant;
import com.sdz.Beans.Groupe;
import com.sdz.Beans.Seance;
import com.sdz.database.Operations_Groupe;

public class DetailGrp_Prof extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int id_groupe = Integer.valueOf(request.getParameter("id_groupe"));

		List<Etudiant> etudiants;
		try {
			etudiants = Operations_Groupe.getStudents(id_groupe);
			request.setAttribute("etudiants", etudiants);
			
			Groupe g = Operations_Groupe.getDataGroupe(id_groupe);
			request.setAttribute("groupe", g);
			
			int count = etudiants.size();	
			request.setAttribute("count", count);
			
			List<Seance> seances = Operations_Groupe.getSeances(id_groupe);
			request.setAttribute("seances", seances);
			
			List<Changement> leavers = Operations_Groupe.getLeavers(id_groupe);
			if(leavers.isEmpty()) request.setAttribute("no_leavers", "Aucun étudiant n'a changé ce groupe");
			else request.setAttribute("leavers", leavers);
			
			List<Changement> joiners = Operations_Groupe.getJoiners(id_groupe);
			if(joiners.isEmpty()) request.setAttribute("no_joiners", "Aucun étudiant n'a joigné ce groupe");
			else request.setAttribute("joiners", joiners);			
			
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/Profs/DetailGrp.jsp").forward(request, response);
	}
}
