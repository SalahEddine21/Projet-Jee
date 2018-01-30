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
		<jsp:include page = "Prof_navbar.jsp" />
		<div class="container">
		
	      <div class="row" style="margin-top:50px;">
	      	 <div class="col-lg-12">
	            <h1 class="page-header">Profil
	               <small>${sessionScope.prof.prenom} - ${sessionScope.prof.nom}</small>
	            </h1>
	            <ol class="breadcrumb">
		           <li><a href="">Accueil</a>
	               </li>
		           <li class="active">Professeur/Attributions Notes</li>
	            </ol>
	          </div>
	      </div>
	      
	      <div class="row">
	      	<div class="col-lg-12">
	      	
				<div class="col-lg-offset-3 col-lg-6" style="margin-top:40px;">
					<c:choose>
						<c:when test="${!empty requestScope.eval_founded}">
							<p><strong>${requestScope.eval_founded}</strong> <a href="attribuerNote" class="btn btn-amber">Retour</a></p>
						</c:when>
						<c:otherwise>
							<h5>Sélectionné: Groupe <a href="detailGrp?id_groupe=${requestScope.groupe}" style="color:#f50057" target="_blank">${requestScope.groupe}</a></h5>
							<hr>
							<form action="noteFinaux" method ="post">
								<table class="table table-bordered table-sm">
								  <thead class="mdb-color darken-3">
								    <tr class="text-white">
								      <th>Numèro</th>
								      <th>Nom</th>
								      <th>Prenom</th>
								      <th>Note</th>
								    </tr>
								  </thead>
								  <tbody>
									<c:forEach items="${requestScope.etudiants}" var="etudiants">
										<tr>
										  <td><a href="detailEtd?id_etd=${etudiants.id}" style="color:#f50057" target="_blank">${etudiants.num}</a></td>
									      <td>${etudiants.nom}</td>
									      <td>${etudiants.prenom}</td>
									      <td><input type="text" name="${etudiants.id}"></td>
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
											<a href="attribuerNote" class="btn btn-amber">Retour</a>	
											<button type="submit" class="btn btn-deep-orange">Enregistrer</button>
										</div>
									</div>
	 							</div>							
							<input type="hidden" value="${requestScope.ids}" name="ids" />	
							<input type="hidden" value="${requestScope.module}" name="id_module" />	
						</form>	
						</c:otherwise>
					</c:choose>					
				</div>
				  		
	      	</div>
	      </div>	
	    </div>  	
	</body>
</html>