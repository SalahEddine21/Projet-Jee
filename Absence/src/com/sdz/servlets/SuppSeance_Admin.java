package com.sdz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.database.Operations_seances;

public class SuppSeance_Admin extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int id_seance = Integer.valueOf(request.getParameter("id_seance"));
		try {
			Operations_seances.DeleteSc(id_seance);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		response.sendRedirect(request.getContextPath()+"/Admin/gestion_seances");
	}
}
