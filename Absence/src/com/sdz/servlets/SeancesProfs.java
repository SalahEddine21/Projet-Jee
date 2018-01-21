package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Seance;
import com.sdz.database.Operations_Prof;

public class SeancesProfs extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int id_groupe = Integer.valueOf(request.getParameter("groupe"));
		int id_module = Integer.valueOf(request.getParameter("module"));
		
		try {
			List<Seance> seances = Operations_Prof.getSeances(id_groupe, id_module);
			
			String result;
			result = "<form id='list_seances' class='row' action='seances' method = 'post' ><div class='col-lg-8'><label>Selectionner une Séance</label> <select name='id_seance' class='form-control'> ";
			for(int i=0 ;i<seances.size(); i++) result = result + "<option value ="+seances.get(i).getId()+">"+seances.get(i).getDate()+" à "+seances.get(i).getHeure()+"</option>";
			
			result = result + "</select> </div> <div class='col-lg-4'> <button type='submit' class='btn btn-pink' style='margin-top:30px;'> Faire L'appel </button></div> </form>";
			
			response.setContentType("text/html");
			response.getWriter().write(result);	
			
		} catch (Exception e) {
			request.setAttribute("query2", e.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int id_seance = Integer.valueOf(request.getParameter("id_seance"));
		
	}
}
