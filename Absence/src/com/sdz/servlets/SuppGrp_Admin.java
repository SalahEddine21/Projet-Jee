package com.sdz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.database.Operations_Etudiant;
import com.sdz.database.Operations_Groupe;
import com.sdz.database.Operations_seances;

public class SuppGrp_Admin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int id_groupe = Integer.valueOf(request.getParameter("id_groupe"));
		
		try {
			Operations_Groupe.DeleteGrp(id_groupe);
			Operations_Groupe.DeleteModGrp(id_groupe);
			Operations_seances.DeleteScGrp(id_groupe);
			Operations_Etudiant.DeleteStudents(id_groupe);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		response.sendRedirect(request.getContextPath()+"/Admin/gestion_groupes");	
	}
}
