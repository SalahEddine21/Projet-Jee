package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.database.Operations_Prof;

public class GroupesProfs extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int id_mod = Integer.valueOf(request.getParameter("module"));
		try {
			List<Integer> groupes = Operations_Prof.getGroupes(id_mod);
			String result;
			
			result = "<div id='test' class='row'> <div class='col-lg-8'><label>Selectionner un Groupe</label> <select class='form-control' id='groupe' name='groupe'>";
			for(int i=0 ;i<groupes.size(); i++)	result = result + "<option value="+groupes.get(i)+">Groupe "+groupes.get(i)+"</option>";
			result = result +"</select> </div> <div class='col-lg-4'> <button id='dispseance' class='btn btn-deep-purple' style='margin-top:20px;'> Afficher les séances </button></div> </div>";
			
			response.setContentType("text/html");
			response.getWriter().write(result);
			
		} catch (Exception e) {
			request.setAttribute("query1", e.getMessage());
		}
				
	}

}
