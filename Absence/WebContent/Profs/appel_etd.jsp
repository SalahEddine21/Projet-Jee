<%@ page pageEncoding = "utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta content="text/html; charset=utf-8">
		<title>${sessionScope.prof.prenom} - ${sessionScope.prof.nom}</title>
		<link href="../css/bootstrap.css" rel="stylesheet">
		<link href="../css/mdb.css" rel="stylesheet">
	</head>
	<body>
		<header>
			<nav Class="navbar navbar-expand-lg navbar-dark black-sell">
			    <logo><a class="navbar-brand" href="#">Accueil</a></logo>
			
			        <ul class="navbar-nav mr-auto">
			            <li class="nav-item">
			                <a class="nav-link waves-light" href="accueilClient" >Appel</a>
			            </li>
			        </ul>
	
			 		<div class="form-inline  waves-light" >
			 			<a class="btn btn-outline-default waves-effect btn-sm" href="deconnexion" >Deconnexion</a>
			 		</div> 
			</nav>
		</header>

		<div class="container">
		
	      <div class="row" style="margin-top:50px;">
	      	 <div class="col-lg-12">
	            <h1 class="page-header">Profil
	               <small>${sessionScope.prof.prenom} - ${sessionScope.prof.nom}</small>
	            </h1>
	            <ol class="breadcrumb">
		           <li><a href="">Accueil</a>
	               </li>
		           <li class="active">Professeur</li>
	            </ol>
	          </div>
	      </div>
	      
	      <div class="row">
	      	<div class="col-lg-12">
	      	
				<div class="col-lg-offset-3 col-lg-6" style="margin-top:40px;">
					<c:choose>
						<c:when test="${!empty requestScope.found}">
							<p><strong>${requestScope.found}</strong> <a href="profil" class="btn btn-amber">Retour</a></p>
						</c:when>
						<c:otherwise>
							<h5>Sélectionné: Groupe <a href="detailGrp?id_groupe=${requestScope.groupe}" style="color:#f50057" target="_blank">${requestScope.groupe}</a> / Séance du: ${requestScope.date_seance} (${requestScope.heure_seance}) </h5>
							<hr>
							<form action="presence" method ="post">
								<table class="table table-bordered table-sm">
								  <thead class="mdb-color darken-3">
								    <tr class="text-white">
								      <th>Numèro</th>
								      <th>Nom</th>
								      <th>Prenom</th>
								      <th>Absent?</th>
								      <th>Jusitifé</th>
								    </tr>
								  </thead>
								  <tbody>
									<c:forEach items="${requestScope.etudiants}" var="etudiants">
									    <tr>
									      <td><a href="detailEtd?id_etd=${etudiants.id}" style="color:#f50057" target="_blank">${etudiants.num}</a></td>
									      <td>${etudiants.nom}</td>
									      <td>${etudiants.prenom}</td>
									      <td><input type="checkbox" name="${etudiants.id}"></td>
									      <td><input type="checkbox" name="${etudiants.id+100}"></td>
									    </tr>
									</c:forEach>
								  </tbody>
								</table>	
								
								<div class="row">
									<div class="col-lg-12">
										<p>Total étudiants: ${requestScope.count}</p>										
									</div>								
									<div class="col-lg-12">
										<hr>	
									</div>
								</div>	
								<div class="row">
									<div class="col-lg-12">
										<div class="pull-right">
											<a href="profil" class="btn btn-amber">Retour</a>	
											<button type="submit" class="btn btn-deep-orange">Enregistrer</button>
										</div>
									</div>
								</div>								
								<input type="hidden" value="${requestScope.id_seance}" name="id_seance" />	
							</form>						
						</c:otherwise>
					</c:choose>
				</div>
				      		
	      	</div>
	      </div>	
	    </div>  		
	</body>
</html>