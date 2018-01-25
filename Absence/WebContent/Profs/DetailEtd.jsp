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
		           <li class="active">Professeur/Detail_Etudiant</li>
	            </ol>
	          </div>
	      </div>
	      
	      <div class="row" style="margin-top:10px;">
	      	<div class="col-lg-12">
	      		<div class="col-lg-5" style="border: 2px solid black;">
	      			<p><strong>Numèro:</strong> ${etudiant.num}</p>
	      			<p><strong>Nom:</strong> ${etudiant.nom}</p>
	      			<p><strong>Prènom:</strong> ${etudiant.prenom}</p>
	      			<p><strong>Email:</strong> ${etudiant.email}</p>
	      			<p><strong>Groupe:</strong> <a href="detailGrp?id_groupe=${etudiant.id_groupe}" style="color:#f50057" target="_blank">${etudiant.id_groupe}</a></p>
	      		</div>
	      	</div>
	      </div>
	      
	      	<div class="row" style="margin-top:30px;">
	      		<div class="col-lg-6">
	      			<label>Prèsence/Absence Etudiant</label>
	      			<hr>
	      			<c:choose>
	      				<c:when test="${!empty requestScope.presences_notfound}">
	      					<p>${requestScope.presences_notfound}</p>
	      				</c:when>
	      				<c:otherwise>
							<table class="table table-bordered table-sm">
							  <thead class="mdb-color darken-3">
							    <tr class="text-white">
							      <th>Date Seance</th>
							      <th>Heure Seance</th>
							      <th>Module</th>
							      <th>Prèsence</th>
							      <th>Justifié</th>
							    </tr>
							  </thead>
							  <tbody>
								<c:forEach items="${requestScope.presences}" var="presences">
								    <tr>
								      <td>${presences.date}</td>
								      <td>${presences.heure}</td>
								      <td>${presences.titre}</td>
								      <c:choose>
								      	<c:when test="${presences.present == 1}">
								      		<td style="color:#9933CC" >Oui</td>
								      		<td>---</td>
								      	</c:when>
								      	<c:otherwise>
								      		<td style="color:#f44336">Non</td>
										      <c:choose>
										      	<c:when test="${presences.justifier == 1}">
										      		<td style="color:#9933CC" >Oui</td>
										      	</c:when>
										      	<c:otherwise>
										      		<td style="color:#f44336">Non</td>
										      	</c:otherwise>
										      </c:choose>
										      
								      	</c:otherwise>
								      </c:choose>
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
	      				<c:when test="${!empty requestScope.notes_notfound}">
	      					<p>${requestScope.notes_notfound}</p>
	      				</c:when>
	      				<c:otherwise>
							<table class="table table-bordered table-sm">
							  <thead class="mdb-color darken-3">
							    <tr class="text-white">
							      <th>Module</th>
							      <th>Note</th>
							      <th>Etat</th>
							    </tr>
							  </thead>
							  <tbody>
								<c:forEach items="${requestScope.notes}" var="notes">
								    <tr>
								      <td>${notes.titre}</td>
								      <td>${notes.note}</td>
								      <c:choose>
								      	<c:when test="${notes.note < 12.00}">
								      		<td style="color:#f44336" >NV</td>
								      	</c:when>
								      	<c:otherwise>
								      		<td style="color:#00C851">V</td>
								      	</c:otherwise>
								      </c:choose>
									</tr>
								</c:forEach>
							 </tbody>
							</table>	
								      					
	      				</c:otherwise>
	      			</c:choose>
	      		</div>
	      	</div>
			<div class="row">
		      	<div class="col-lg-12">
		      		<hr>
		      	</div>
			</div>
			<div class="row">
		      	<div class="col-lg-12">
		      		<h4>Historique</h4>
		      	</div>
			</div>
			<div class="row">
		      	<div class="col-lg-12">
		      		<c:choose>
		      			<c:when test="${!empty requestScope.ch_notfound}">
		      				<p>${requestScope.ch_notfound}</p>
		      			</c:when>
		      			<c:otherwise>
		      				<p>Rejoint le groupe <a href="detailGrp?id_groupe=${etudiant.id_groupe}" style="color:#f50057" target="_blank">${etudiant.id_groupe}</a> le ${changement.date_ch} </p>
		      				<p>Groupe source: <a href="detailGrp?id_groupe=${changement.groupe_src}" style="color:#f50057" target="_blank">${changement.groupe_src}</a></p>
		      			</c:otherwise>
		      		</c:choose>
		      	</div>
			</div>
			<div class="row">
		      	<div class="col-lg-12">
		      		<hr>
		      	</div>
			</div>     
	    </div> 		
	</body>
</html>