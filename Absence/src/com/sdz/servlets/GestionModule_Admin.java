package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Module;
import com.sdz.Beans.Professeur;
import com.sdz.Controles.ControleModule;
import com.sdz.database.Operations_Module;
import com.sdz.database.Operations_Prof;

public class GestionModule_Admin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		try {
			List<Professeur> profs = Operations_Prof.SelectAllProfs();
			request.setAttribute("professeurs", profs);
			
			List<Module> modules = Operations_Module.getAllModules();
			request.setAttribute("modules", modules);
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Admin/gestion_modules.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		ControleModule controle = new ControleModule();
		controle.CheckModule(request);
		request.setAttribute("controle", controle);
		
		List<Professeur> profs;
		try {
			profs = Operations_Prof.SelectAllProfs();
			request.setAttribute("professeurs", profs);
			
			List<Module> modules = Operations_Module.getAllModules();
			request.setAttribute("modules", modules);
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		
		
		this.getServletContext().getRequestDispatcher("/Admin/gestion_modules.jsp").forward(request, response);
	}
}
