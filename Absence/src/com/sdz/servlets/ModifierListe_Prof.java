package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Etudiant_Absence;
import com.sdz.Beans.Module;
import com.sdz.Beans.Professeur;
import com.sdz.Beans.Seance;
import com.sdz.database.Operations_Presences;
import com.sdz.database.Operations_Prof;
import com.sdz.database.Operations_seances;

public class ModifierListe_Prof extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Professeur prof = (Professeur) session.getAttribute("prof");
		int id_prof = prof.getId();
		
		try {
			List<Module> modules = Operations_Prof.getModules(id_prof);
			if(modules.isEmpty()) request.setAttribute("no_module", "Vous n'enseignez aucun module !");
			request.setAttribute("modules", modules);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/Profs/modifier_list_absence.jsp").forward(request, response);		
	}

}
