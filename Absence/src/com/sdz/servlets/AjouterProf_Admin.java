package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Professeur;
import com.sdz.Controles.ControleProf;
import com.sdz.database.Operations_Prof;

public class AjouterProf_Admin extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String cin = request.getParameter("cin");
		String name = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String password = request.getParameter("passe");
		
		Professeur prof = new Professeur();
		prof.setCin(cin);
		prof.setNom(name);
		prof.setPrenom(prenom);
		prof.setEmail(email);
		prof.setPasse(password);
		
		try {
			ControleProf controle = new ControleProf();
			controle.checkProf(prof);
			request.setAttribute("controle", controle);
			
			List<Professeur> profs = Operations_Prof.SelectAllProfs();
			request.setAttribute("profs", profs);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/Admin/gestion_profs.jsp").forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.sendRedirect(request.getContextPath()+"/Admin/gestion_profs");	
	}
}
