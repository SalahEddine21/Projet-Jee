package com.sdz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Etudiant;
import com.sdz.Controles.ControleEtd;

public class Connexion_Etds extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		ControleEtd controle = new ControleEtd();
		Etudiant etd = controle.CheckEtd(request);
		
		if(etd != null){
			HttpSession session = request.getSession();
			session.setAttribute("connexion", "on");
			session.setAttribute("etd", etd);
			response.sendRedirect(request.getContextPath()+"/Etudiants/profil");			
		}else{
			request.setAttribute("controle", controle);
			this.getServletContext().getRequestDispatcher("/Etudiants/login.jsp").forward(request, response);			
		}
	}
}
