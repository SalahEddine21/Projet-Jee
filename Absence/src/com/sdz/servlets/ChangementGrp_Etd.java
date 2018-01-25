package com.sdz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdz.Beans.Etudiant;
import com.sdz.Beans.Groupe;
import com.sdz.database.Operations_Changement;
import com.sdz.database.Operations_Etudiant;
import com.sdz.database.Operations_Groupe;

public class ChangementGrp_Etd extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		HttpSession session = request.getSession();
		Etudiant etd = (Etudiant) session.getAttribute("etd");	
		
		try {
			int old_ch = Operations_Etudiant.checkOldCh(etd.getId());
			if(old_ch == 1){
				request.setAttribute("ch_founded", "Vous avez déjà fait un changement, pas plus !");
			}else{
				int id_groupe = etd.getId_groupe();	
				List<Groupe> grps = Operations_Groupe.getSameGrps(id_groupe);
				request.setAttribute("groupes", grps);
			}
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/Etudiants/choixgrps.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Etudiant etd = (Etudiant) session.getAttribute("etd");	
		
		int id_etd = etd.getId();
		int id_groupe_src = etd.getId_groupe();	
		int id_groupe_dest = Integer.valueOf(request.getParameter("groupe"));
		
		try {
			Operations_Changement.insertLine(id_etd, id_groupe_src);
			Operations_Etudiant.UpdateGrp(id_etd, id_groupe_dest);
		} catch (Exception e) {
			request.setAttribute("query", e.getMessage());
		}
		response.sendRedirect(request.getContextPath()+"/Etudiants/profil");	
	}
}
