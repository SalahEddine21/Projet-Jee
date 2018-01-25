package com.sdz.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.Beans.Dashboard;
import com.sdz.database.DaoException;
import com.sdz.database.OperationAdmin;

@WebServlet("/ProfilAdmin")
public class ProfilAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Date> dates ;
	Calendar calendar = Calendar.getInstance();
    public ProfilAdmin() {
        super();
    }
    public void init() throws ServletException {
		 try {
			dates=  OperationAdmin.getDateSeances();
		} catch (DaoException e) {
			e.getMessage();
		}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
		request.setAttribute("dates", dates);
		for(Date todayTest:dates){	

		    if(ourJavaDateObject.toString().equals(todayTest.toString())){
				request.setAttribute("currentdate", todayTest);
		    	break ;
		    }
		}
		this.getServletContext().getRequestDispatcher("/Admin/profilAdmin.jsp").forward(request, response);
	
	}

	 static Scanner sc=new Scanner(System.in) ;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String dateString = request.getParameter("name");
		 Date date=java.sql.Date.valueOf(dateString) ;
		 System.out.println(" date recuperé de jsp"+date);
		 String result=null ;
			List<Dashboard> dashs=new ArrayList<Dashboard>() ;
			
		
						try {
							dashs = OperationAdmin.getData(date) ;
							} catch (Exception e) {
										System.out.println("erreur recuperation data");
								} 
							if(dashs.isEmpty()){
								result=" " ;
							}
							else{	
									

							result="<tr>";
							
							for(int i=0 ;i<dashs.size(); i++){
												result = result+dashs.get(i).getId_seance()+"</td><td>"+dashs.get(i).getId_groupe()+"</td><td>"+dashs.get(i).getHeure_seance()+"</td><td>"+dashs.get(i).getTitre_module()+"</td><td>"+dashs.get(i).getNom_prof()+"</td><td>"+"Oui</td><td><a href='#'>Liste d'appel</a></td></tr>";
							}
									System.out.println(""+result);
									PrintWriter out = response.getWriter();
									 response.setContentType("text/html");
									out.println(result);   
							} 
						
		 } 
		// result="<tr><td>dsf</td><td>dsfd</td><td>dsf</td><td>dsfds</td> <td>dsfd</td><td>qsfq</td></tr><tr><td>dsf</td><td>dsfd</td><td>dsf</td><td>dsfds</td> <td>dsfd</td><td>qsfq</td></tr>" ;
	
	}
