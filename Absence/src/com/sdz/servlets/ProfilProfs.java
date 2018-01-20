package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Module;
import com.sdz.Beans.Professeur;
import com.sdz.database.Operations_Prof;

public class ProfilProfs extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Professeur prof = (Professeur) session.getAttribute("prof");
		int id_prof = prof.getId();
		
		try {
			List<Module> modules = Operations_Prof.getModules(id_prof);
			if(!modules.isEmpty()){ // the teacher should have at least one course, to run the app 
				if(modules.size() == 1){ // if the teacher have only one course, then automatically we'll display the groupes he have
					 List<Integer> groupes = Operations_Prof.getGroupes(modules.get(0).getId());
					 request.setAttribute("titre_module", modules.get(0).getTitre());
					 request.setAttribute("groupes", groupes);
					 //add the case if we have just one groupe -> display the sessions directly
				}
			}
			request.setAttribute("modules", modules);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/Profs/profil.jsp").forward(request, response);
	}
}
