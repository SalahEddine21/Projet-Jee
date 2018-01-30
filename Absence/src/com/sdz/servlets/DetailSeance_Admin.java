package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Admin;
import com.sdz.Beans.Etudiant_Absence;
import com.sdz.Beans.Groupe;
import com.sdz.database.Operations_Groupe;
import com.sdz.database.Operations_Presences;

public class DetailSeance_Admin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");	
		
		int id_seance = Integer.valueOf(request.getParameter("id_seance"));
		try {
			List<Etudiant_Absence> etd_abs = Operations_Presences.getStudents_Presences(id_seance);
			
			if(etd_abs.isEmpty()) request.setAttribute("not_founded", "Appel pas encore efféctué ");
			else request.setAttribute("etudiants", etd_abs);
			
			Groupe grp = Operations_Groupe.getDataGroupeBySeance(id_seance);
			request.setAttribute("groupe", grp);
			
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Admin/DetailSeance.jsp").forward(request, response);
	}
}
