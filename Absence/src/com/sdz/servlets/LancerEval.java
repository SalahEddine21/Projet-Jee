package com.sdz.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Evaluation;
import com.sdz.Beans.Module;
import com.sdz.Beans.Professeur;
import com.sdz.database.Operations_Eval;
import com.sdz.database.Operations_Prof;

public class LancerEval extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		HttpSession session = request.getSession();
		Professeur prof = (Professeur) session.getAttribute("prof");
		int id_prof = prof.getId();
		
		try {
			List<Module> modules = Operations_Prof.getModules(id_prof);
			if(modules.isEmpty()) request.setAttribute("no_module", "Vous n'enseigner aucun module !");
			request.setAttribute("modules", modules);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/Profs/lancer_eval.jsp").forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
						
		try {
			int id_mod = Integer.valueOf(request.getParameter("module"));
			Date date = Date.valueOf(request.getParameter("date"));
			String heure = request.getParameter("heure");
			
			Evaluation e = new Evaluation();
			e.setDate(date);
			e.setHeure(heure);
			e.setId_mod(id_mod);
			Operations_Eval.insertEval(e);
		} catch (Exception e1) {
			response.sendRedirect(request.getContextPath()+"/Profs/erreur");
		}
		response.sendRedirect(request.getContextPath()+"/Profs/profil");
	}
}
