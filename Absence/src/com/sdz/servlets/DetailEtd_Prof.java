package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Changement;
import com.sdz.Beans.Etudiant;
import com.sdz.Beans.Etudiant_Note;
import com.sdz.Beans.Seance_Presence;
import com.sdz.database.Operations_Etudiant;

public class DetailEtd_Prof extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		int id_etd = Integer.valueOf(request.getParameter("id_etd"));
		
		try {
			Etudiant e = Operations_Etudiant.getStudent(id_etd);
			request.setAttribute("etudiant", e);
			
			List<Seance_Presence> etd_presences = Operations_Etudiant.getPresence_Student(id_etd);
			if(etd_presences.isEmpty()) request.setAttribute("presences_notfound", "Aucun élément n'est Disponible");
			else request.setAttribute("presences", etd_presences);
			
			List<Etudiant_Note> etd_notes = Operations_Etudiant.getMarks_Student(id_etd);
			if(etd_notes.isEmpty()) request.setAttribute("notes_notfound", "Aucun élément n'est Disponible");
			else request.setAttribute("notes", etd_notes);
			
			Changement ch = Operations_Etudiant.getChangement(id_etd);
			if(ch == null) request.setAttribute("ch_notfound", "Aucun changement n'est trouvé");
			else request.setAttribute("changement", ch);
			
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Profs/DetailEtd.jsp").forward(request, response);
	}

}
