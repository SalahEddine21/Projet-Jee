package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Changement;
import com.sdz.Beans.Etudiant;
import com.sdz.Beans.Etudiant_Note;
import com.sdz.Beans.Evaluation;
import com.sdz.Beans.Seance_Presence;
import com.sdz.Controles.ControleEval;
import com.sdz.database.Operations_Etudiant;

public class ProfilEtd extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		HttpSession session = request.getSession();
		Etudiant etd = (Etudiant) session.getAttribute("etd");
		
		int id_groupe = etd.getId_groupe();
		ControleEval controle = new ControleEval();
		
		try {
			List<Evaluation> evaluations_groupe = controle.getPending_Eval(id_groupe);
			if(!evaluations_groupe.isEmpty()) request.setAttribute("evaluations", evaluations_groupe);
			else request.setAttribute("eval_empty", "No eval detected ");
			
			List<Seance_Presence> presences = Operations_Etudiant.getPresence_Student(etd.getId());
			if(presences.isEmpty()) request.setAttribute("presences_empty", "Aucune données n'est disponible ");
			else request.setAttribute("presences", presences);
			
			List<Etudiant_Note> notes = Operations_Etudiant.getMarks_Student(etd.getId());
			if(notes.isEmpty()) request.setAttribute("notes_empty", "Aucune données n'est disponible ");
			else request.setAttribute("notes", notes);
			
			Changement ch = Operations_Etudiant.getChangement(etd.getId());
			if(ch == null) request.setAttribute("ch_empty", "Aucun changement de groupe n'est trouvè ");
			else request.setAttribute("changement", ch);
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/Etudiants/profil.jsp").forward(request, response);
	}
}
