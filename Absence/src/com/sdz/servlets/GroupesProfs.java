package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.database.Operations_Prof;

public class GroupesProfs extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int id_mod = Integer.valueOf(request.getParameter("module"));
		try {
			List<Integer> groupes = Operations_Prof.getGroupes(id_mod);
			String result;
			
			result = "<div id='test' ><label>Selectionner un Groupe</label> <select class='form-control'>";
			for(int i=0 ;i<groupes.size(); i++)	result = result + "<option value="+groupes.get(i)+">Groupe "+groupes.get(i)+"</option>";
			result = result +"</select><button id='disp_sceance' class='btn btn-deep-purple'> Afficher les séances </button> </div>";
			
			response.setContentType("text/html");
			response.getWriter().write(result);
			
		} catch (Exception e) {
			request.setAttribute("query1", e.getMessage());
		}
				
	}

}
