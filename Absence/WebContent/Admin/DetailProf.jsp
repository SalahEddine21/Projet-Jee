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
		           <li class="active">Administrateur / Detail_Professeur</li>
	            </ol>
	          </div>
	      </div>	
	      
	      <div class="row" style="margin-top:10px;">
	      	<div class="col-lg-12">
	      		<div class="col-lg-5" style="border: 2px solid black;">
	      			<p><strong>Nom:</strong> ${prof.nom}</p>
	      			<p><strong>Prènom:</strong> ${prof.prenom}</p>
	      			<p><strong>Email:</strong> ${prof.email}</p>
	      		</div>
	      	</div>
	      </div>
	      
	      <div class="row" style="margin-top:30px;">
	      	<div class="col-lg-5">
	      		<label>Prèsence/Absence Etudiant</label>
	      		<hr>	
	      		<c:choose>
	      			<c:when test="${!empty requestScope.no_module}" >
	      				<p>${requestScope.no_module}</p>
	      			</c:when>
	      			<c:otherwise>
	      			
							<table class="table table-bordered table-sm">
							  <thead class="mdb-color darken-3">
							    <tr class="text-white">
							      <th>Module</th>
							      <th>Titre</th>
							      <th>Semestre</th>
							    </tr>
							  </thead>
							  <tbody>
								<c:forEach items="${requestScope.modules}" var="module">
								    <tr>
								      <td>${module.id}</td>
								      <td>${module.titre}</td>
								      <td>${module.semestre}</td>
									</tr>
								</c:forEach>
							 </tbody>
							</table>
									      				
	      			</c:otherwise>
	      		</c:choose>      		
	      	</div>
	      	<div class="col-lg-5">
	      		<label>Notes Etudiant</label>
	      		<hr>
	      		<c:choose>
	      			<c:when test="${!empty requestScope.no_groupe}" >
	      				<p>${requestScope.no_groupe}</p>
	      			</c:when>
	      			<c:otherwise>
	      			
							<table class="table table-bordered table-sm">
							  <thead class="mdb-color darken-3">
							    <tr class="text-white">
							      <th>Groupe</th>
							      <th>Semestre</th>
							    </tr>
							  </thead>
							  <tbody>
								<c:forEach items="${requestScope.groupes}" var="groupe">
								    <tr>
								      <td><a href="detailGrp?id_groupe=${groupe.id}" style="color:#f50057" target="_blank" >${groupe.id}</a></td>
								      <td>${groupe.semestre}</td>
									</tr>
								</c:forEach>
							 </tbody>
							</table>
									      				
	      			</c:otherwise>
	      		</c:choose>   	      		
	      	</div>
	      </div>
	   </div>   		
	</body>
</html>