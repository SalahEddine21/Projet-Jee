package com.sdz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Professeur;
import com.sdz.Controles.ControleProf;

public class ConnexionProfs extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		ControleProf controle = new ControleProf();
		Professeur prof = controle.CheckProf(request);
		
		if(prof != null){ // if no errors, we create the prof session
			HttpSession session = request.getSession();
			session.setAttribute("connexion", "on");
			session.setAttribute("prof", prof);
			response.sendRedirect(request.getContextPath()+"/Profs/profil");			
		}else{
			request.setAttribute("controle", controle);
			this.getServletContext().getRequestDispatcher("/Profs/login.jsp").forward(request, response);
		}
		
	}
	
}
