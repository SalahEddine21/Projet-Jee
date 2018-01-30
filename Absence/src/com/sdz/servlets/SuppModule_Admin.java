package com.sdz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.database.Operations_Module;

public class SuppModule_Admin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int id_module = Integer.valueOf(request.getParameter("id_mod"));
		try {
			Operations_Module.DeleteAll_AboutMod(id_module);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		response.sendRedirect(request.getContextPath()+"/Admin/gestion_modules");
	}
}
