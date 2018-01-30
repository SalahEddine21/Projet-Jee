package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Groupe;
import com.sdz.Beans.Module;
import com.sdz.Beans.Professeur;
import com.sdz.database.Operations_Prof;

public class DetailProf_Admin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int id_prof = Integer.valueOf(request.getParameter("id_prof"));
		try {
			Professeur prof = Operations_Prof.getProfByID(id_prof);
			request.setAttribute("prof", prof);
			
			List<Groupe> grps = Operations_Prof.getGrpProf(id_prof);	
			if(grps.isEmpty()) request.setAttribute("no_groupe", "Ce prof n'a aucun groupe en charge ");
			else request.setAttribute("groupes", grps);
			
			List<Module> modules = Operations_Prof.getModules(id_prof);
			if(modules.isEmpty()) request.setAttribute("no_module", "Ce prof n'a aucun module en charge");
			else request.setAttribute("modules", modules);
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Admin/DetailProf.jsp").forward(request, response);
	}

}
