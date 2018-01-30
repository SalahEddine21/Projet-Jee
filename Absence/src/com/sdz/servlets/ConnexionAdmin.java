package com.sdz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Admin;
import com.sdz.Controles.ControleAdmin;

public class ConnexionAdmin extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		ControleAdmin controle = new ControleAdmin(); // partie controle à ne pas merger avec la code de la servlet
		Admin admin = controle.CheckAdmin(request);
		
		if(admin != null){
			HttpSession session = request.getSession();
			session.setAttribute("connexion", "on");
			session.setAttribute("admin", admin);
			response.sendRedirect(request.getContextPath()+"/Admin/profil");			
		}else{
			request.setAttribute("controle", controle);
			this.getServletContext().getRequestDispatcher("/Admin/login.jsp").forward(request, response);			
		}
	}
}
