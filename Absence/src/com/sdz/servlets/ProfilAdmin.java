package com.sdz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Admin;

public class ProfilAdmin extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");		
		
		this.getServletContext().getRequestDispatcher("/Admin/profil.jsp").forward(request, response);
	}
}
