package com.sdz.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Seances_Admin;
import com.sdz.database.Operations_Admin;

public class SeancesInfo_Admin extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String date = request.getParameter("date");
		Date date_c = Date.valueOf(date);
		String result;
		
		try {
			List<Seances_Admin> seances = Operations_Admin.getData(date_c);
			if(seances.isEmpty()) result = "<div id='seances'> Aucune seance n'est prévu dans la date"+date+"</div>";
			else{
					result = "<div id='seances'> <table class='table table-bordered table-sm'> <thead class='mdb-color darken-3'> ";
					
					result = result + "<tr class='text-white'>";
						result = result + "<th>Seance</th>";
						result = result + "<th>Module</th>";
						result = result + "<th>Heure</th>";
						result = result + "<th>Groupe</th>";
						result = result + "<th>Semestre</th>";
						result = result + "<th>Prof</th>";
						result = result + "<th>Absence</th>";
					result = result + "</tr>";
					result = result + "</thead>";
					
					result = result + "<tbody>";
					
					for(int i=0 ;i<seances.size(); i++){
						result = result + "<tr>";
							result = result + "<td>"+seances.get(i).getId_seance()+"</td>";
							result = result + "<td>"+seances.get(i).getModule()+"</td>";
							result = result + "<td>"+seances.get(i).getHeure()+"</td>";
							result = result + "<td><a href='detailGrp?id_groupe="+seances.get(i).getId_groupe()+"' style='color:#f50057' target='_blank' > "+seances.get(i).getId_groupe()+"</a></td>";
							result = result + "<td>"+seances.get(i).getSemestre()+"</td>";
							result = result + "<td>"+seances.get(i).getProf()+"</td>";
							result = result + "<td> <a href='DetailSeance?id_seance="+seances.get(i).getId_seance()+"' class='btn btn-pink' target='_blank' >Voir</a> </td>";
						result = result + "</tr>";
					}
					
					result = result + "</tbody>";
					result = result + "</table>";
					result = result + "</div>";
			}
			
			response.setContentType("text/html");
			response.getWriter().write(result);
			
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/Admin/erreur.jsp");	
		}
	}
}
