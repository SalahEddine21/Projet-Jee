<%@ page pageEncoding = "utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=utf-8">
		<title>${sessionScope.admin.prenom} - ${sessionScope.admin.nom}</title>
		<link href="../css/bootstrap.css" rel="stylesheet">
		<link href="../css/mdb.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page = "Admin_navbar.jsp" />

		<div class="container">
		
	      <div class="row" style="margin-top:50px;">
	      	 <div class="col-lg-12">
	            <h1 class="page-header">Profil
	               <small>${sessionScope.admin.prenom} - ${sessionScope.admin.nom}</small>
	            </h1>
	            <ol class="breadcrumb">
		           <li><a href="">Accueil</a>
	               </li>
		           <li class="active">Administrateur/Gestions des professeurs</li>
	            </ol>
	          </div>
	      </div>	
	      
	      <div class="row" >
	      	<div class="col-lg-4">
				<div class="list-group">
					 <a href="profil" class="list-group-item list-group-item-action">DashBoard</a>
					 <a href="gestion_profs" class="list-group-item active">Gestion des Professeurs</a>
					 <a href="gestion_modules" class="list-group-item list-group-item-action">Gestion des modules</a>
					 <a href="gestion_groupes" class="list-group-item list-group-item-action">Gestion des groupes</a>
					 <a href="gestion_seances" class="list-group-item list-group-item-action">Gestion des séances</a>
				</div>
	      	</div>
	      	
	      	<div class="col-lg-8">
				<div class="row">
				     <div class="col-lg-12">
				      	<h4 style="margin-top:10px;" >Espace de gestion des professeurs</h4>
				      	<hr>
				      	<p>Merci de remplir les informations suivantes</p>
				     </div>		
				</div>	   		
				<form action="ajoutProf" method="post">
					 <div class="col-lg-10">
						<div class="form-group">
							<input type="text" name="cin" class="form-control" placeholder="cin" />
							<p style="color:red;">${controle.errors.cin}</p>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="nom" placeholder="nom" >
							<p style="color:red;">${controle.errors.nom}</p>
						</div>	 
						<div class="form-group">
							<input type="text" class="form-control" name="prenom" placeholder="prenom" >
							<p style="color:red;">${controle.errors.prenom}</p>
						</div>									  
 						<div class="form-group">
							<input type="text" class="form-control" name="email" placeholder="email" >
							<p style="color:red;">${controle.errors.email}</p>
						</div>		
						<div class="form-group">
							<input type="password" class="form-control" name="passe" placeholder="mot de passe" >
							<p style="color:red;">${controle.errors.passe}</p>
						</div>	
						<div class="form-group">
							<button type="submit" class="btn btn-deep-purple">Ajouter</button>
						</div>
						<div class="form-group">
							<p style="color:red;">${requestScope.query}</p>
						</div>
						
						<div class="form-group">
							<c:if test="${controle.result == 1}">
								<div class="alert alert-success" >
									<strong>Well Done</strong>
									<p>Les modifications sont bien enregistrés</p>
								</div>										
							</c:if>
						</div>
					 </div>
				</form>	  
				<div class="row">
					<div class="col-lg-12">
						<hr>
					</div>
				</div> 
				<div class="row">
					<div class="col-lg-12">
					    <div class="col-lg-12">
					      	<h4 style="margin-top:10px;" >Liste des Professeurs</h4>
					    </div>							
					</div>					
				</div>
				<div class="col-lg-12" style="height: 280px; overflow: scroll;">
					<table class="table table-bordered table-sm">
						<thead class="mdb-color darken-3">
							<tr class="text-white">
							   <th>nom</th>
							   <th>prenom</th>
							   <th>email</th>
							   <th></th>
							 </tr>
						</thead>
						<tbody>
						<c:forEach items="${requestScope.profs}" var="prof">
							<tr>
								<td>${prof.nom}</td>
								<td>${prof.prenom}</td>
								<td>${prof.email}</td>
								<td><a href="DetailPrf?id_prof=${prof.id}" class="btn btn-info btn-sm" target='_blank' >Detail</a></td>							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>				 	      		
	      	</div>
	     </div>
	   </div>		
	</body>
</html>