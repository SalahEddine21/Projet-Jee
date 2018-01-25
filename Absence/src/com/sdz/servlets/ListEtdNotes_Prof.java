package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Etudiant;
import com.sdz.database.Operations_Eval;
import com.sdz.database.Operations_Groupe;

public class ListEtdNotes_Prof extends HttpServlet {
	
	private int id_mod, id_groupe, count, id_min, id_max, exist;
	private List<Etudiant> etudiants;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		if(exist == 1){
			request.setAttribute("eval_founded", "Vous avez déjà attribué les notes de ce modules à ce groupes !");
		}else{		
			request.setAttribute("etudiants", etudiants);
			request.setAttribute("count", count);
			request.setAttribute("module", id_mod);
			request.setAttribute("groupe", id_groupe);
			request.setAttribute("id_min", id_min);
			request.setAttribute("id_max", id_max);		
		}
		this.getServletContext().getRequestDispatcher("/Profs/attrNotesEtd.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		id_mod = Integer.valueOf(request.getParameter("module"));
		id_groupe = Integer.valueOf(request.getParameter("groupe"));
		
		try {
			exist = Operations_Eval.checkEvalExistance(id_mod, id_groupe);
			if(exist == 1){
				request.setAttribute("eval_founded", "Vous avez déjà attribué les notes de ce modules à ce groupes !");
			}else{
				etudiants = Operations_Groupe.getStudents(id_groupe);
				count = etudiants.size();
				id_min = etudiants.get(0).getId();
				id_max = etudiants.get(count-1).getId();
				
				request.setAttribute("etudiants", etudiants);
				request.setAttribute("count", count);
				request.setAttribute("module", id_mod);
				request.setAttribute("groupe", id_groupe);
				request.setAttribute("id_min", id_min);
				request.setAttribute("id_max", id_max);
			}
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		
		
		this.getServletContext().getRequestDispatcher("/Profs/attrNotesEtd.jsp").forward(request, response);
	}
}
