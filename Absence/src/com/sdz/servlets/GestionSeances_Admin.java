package com.sdz.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Groupe;
import com.sdz.Beans.Module;
import com.sdz.Beans.Seances_Admin;
import com.sdz.database.Operations_Groupe;
import com.sdz.database.Operations_Module;
import com.sdz.database.Operations_seances;

public class GestionSeances_Admin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try {
			List<Groupe> groupes = Operations_Groupe.getAllGrps();
			request.setAttribute("groupes", groupes);
			
			List<Module> modules = Operations_Module.getAllMods();
			request.setAttribute("modules", modules);
			
			List<Seances_Admin> sa = Operations_seances.SelectAllSc();
			request.setAttribute("seances", sa);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Admin/gestion_seances.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int id_groupe = Integer.valueOf(request.getParameter("groupe"));
		int id_mod = Integer.valueOf(request.getParameter("module"));
		Date date = Date.valueOf(request.getParameter("date"));
		String heure = request.getParameter("heure");
		
		try {
			Operations_seances.InsertSc(id_groupe, id_mod, date, heure);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		response.sendRedirect(request.getContextPath()+"/Admin/gestion_seances");	
	}
}
