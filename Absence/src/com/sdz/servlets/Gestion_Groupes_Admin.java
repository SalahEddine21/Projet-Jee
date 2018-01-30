package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Groupe;
import com.sdz.Beans.Module;
import com.sdz.database.Operations_Groupe;
import com.sdz.database.Operations_Module;

public class Gestion_Groupes_Admin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		try {
			List<Groupe> groupes = Operations_Groupe.getAllGrps();
			request.setAttribute("groupes", groupes);
			
			List<Module> modules = Operations_Module.getAllMods();
			request.setAttribute("modules", modules);
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Admin/gestion_groupes.jsp").forward(request, response);
	}
	
}
