package com.sdz.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ConnexionAdmin")
public class ConnexionAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Map<String,String> errors;
    public ConnexionAdmin() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cin=request.getParameter("cin") ;
		String pass=request.getParameter("passe") ;
		errors = new HashMap<String,String>();
		try{
			if(cin != null && !cin.isEmpty()){
				if(cin.trim().length() != 8) throw new Exception ("cin est non valide");	
			} 
			else throw new Exception ("Veuillez saisir votre CIN svp ! ");
		}catch(Exception e) {errors.put("cin", e.getMessage());}
			
		try{
			if(pass.isEmpty() || pass==null) throw new Exception ("Veuillez Saisir votre mot de passe svp !");
		}catch(Exception e) {errors.put("pass", e.getMessage());}
		
		if( errors.isEmpty()){
			
				if( !cin.equals("admin001") && !cin.equals("admin002") ){
					 errors.put("cin", "cin incorrect !");
						request.setAttribute("error", errors);
						this.getServletContext().getRequestDispatcher("/Admin/loginAdmin.jsp").forward(request, response);

				}
				else{
					if(cin.equals("admin001")){
						if(pass.equals("test1")) {
							HttpSession session = request.getSession();
							session.setAttribute("nom", "LECHIAKH");
							response.sendRedirect(request.getContextPath()+"/Admin/profilAdmin");	
						}
						else {
							errors.put("pass", "mot de passe incorrect !");		
							request.setAttribute("error", errors);
							this.getServletContext().getRequestDispatcher("/Admin/loginAdmin.jsp").forward(request, response);

						
						}
					}
					else {
								if(pass.equals("test2")) {
									HttpSession session = request.getSession();
									session.setAttribute("nom", "LAHMAM");
									response.sendRedirect(request.getContextPath()+"/Admin/profilAdmin");	
									}
								else {
									errors.put("pass", "mot de passe incorrect !");	
									request.setAttribute("error", errors);
									this.getServletContext().getRequestDispatcher("/Admin/loginAdmin.jsp").forward(request, response);

								}
					}
				}
										
			} 
		else {
			request.setAttribute("error", errors);
			this.getServletContext().getRequestDispatcher("/Admin/loginAdmin.jsp").forward(request, response);

		}
	}
}

