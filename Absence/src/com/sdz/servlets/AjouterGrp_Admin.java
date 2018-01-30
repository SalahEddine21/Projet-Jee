package com.sdz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sdz.Controles.ControleFile;
import com.sdz.database.Operations_Etudiant;
import com.sdz.database.Operations_Groupe;

public class AjouterGrp_Admin extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String semestre = request.getParameter("semestre");
		
		try {
			int id_groupe = Operations_Groupe.InsertGrp(semestre);
			String[] modules = request.getParameterValues("modules");
			int id_mod;
			
			for(int i=0 ;i<modules.length; i++){
				id_mod = Integer.valueOf(modules[i]);
				Operations_Groupe.AddModToGrp(id_groupe, id_mod);
			}
			
			String chemin = this.getServletConfig().getInitParameter( ControleFile.CHEMIN);	
			Part part = request.getPart("etudiants");
			String nomFichier = ControleFile.getNomFichier(part);
			ControleFile.ecrireFichier(part, nomFichier, chemin);
			
			Operations_Etudiant.InsertStudents(id_groupe, chemin+nomFichier);
			
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		response.sendRedirect(request.getContextPath()+"/Admin/gestion_groupes");	
	}
}
