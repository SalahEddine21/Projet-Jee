package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Professeur;
import com.sdz.database.Operations_Prof;

public class GestionProfs_Admin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		try {
			List<Professeur> profs = Operations_Prof.SelectAllProfs();
			request.setAttribute("profs", profs);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/Admin/gestion_profs.jsp").forward(request, response);
	}
}
