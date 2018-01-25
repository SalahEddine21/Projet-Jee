package com.sdz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Professeur;
import com.sdz.database.Operations_Eval;

public class StockNotes_Prof extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		//--------
		HttpSession session = request.getSession();
		Professeur prof = (Professeur) session.getAttribute("prof");
		int id_prof = prof.getId();
		//-----------

		try {
			
			int id_min = Integer.valueOf(request.getParameter("id_min"));
			int id_max = Integer.valueOf(request.getParameter("id_max"));
			int id_mod = Integer.valueOf(request.getParameter("id_module"));
			int id_eval = Operations_Eval.getIdEval(id_mod);
			
			for(int i=id_min ;i<=id_max; i++){
				float note = Float.valueOf(request.getParameter(String.valueOf(i)));
				Operations_Eval.insertNoteEtd(i, id_eval, note);
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/Profs/erreur.jsp");		
		}
		response.sendRedirect(request.getContextPath()+"/Profs/profil");		

	}
}
