<%@ page pageEncoding = "utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=utf-8">
		<title>${sessionScope.etd.prenom} - ${sessionScope.etd.nom}</title>
		<link href="../css/bootstrap.css" rel="stylesheet">
		<link href="../css/mdb.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page = "EtdNavBar.jsp" />
		<div class="container">
		
	      <div class="row" style="margin-top:50px;">
	      	 <div class="col-lg-12">
	            <h1 class="page-header">Profil
	               <small>${sessionScope.etd.prenom} - ${sessionScope.etd.nom}</small>
	            </h1>
	            <ol class="breadcrumb">
		           <li><a href="">Accueil</a>
	               </li>
		           <li class="active">Etudiant/Tableau de bord</li>
	            </ol>
	          </div>
	      </div>
	      
	      <div class="row" style="margin-top:10px;">
	      		<div class="col-lg-5" >
	      			<div style="border: 2px solid black; padding-right:50px;">
	      			<p><strong>Numèro:</strong> ${sessionScope.etd.num}</p>
	      			<p><strong>Nom:</strong> ${sessionScope.etd.nom}</p>
	      			<p><strong>Prènom:</strong> ${sessionScope.etd.prenom}</p>
	      			<p><strong>Email:</strong> ${sessionScope.etd.email}</p>
	      			<p><strong>Groupe:</strong> ${sessionScope.etd.id_groupe}</p>	      			
	      			</div>
	      		</div>
	      		<div class="col-lg-5">
	      			<c:choose>
	      				<c:when test="${empty requestScope.eval_empty}">
							<div class="alert alert-warning" >
								<strong>Attention</strong>
								<c:forEach items="${requestScope.evaluations}" var="evaluations">
									<p>Exam en ${evaluations.titre_mod} prévu le ${evaluations.date} à ${evaluations.heure} </p>
								</c:forEach>
							</div>	      				
	      				</c:when>
	      			</c:choose>
	      		</div>
	      </div>
	      
	      	<div class="row" style="margin-top:30px;">
	      		<div class="col-lg-6">
	      			<label>Prèsence/Absence Etudiant</label>
	      			<hr>
	      			<c:choose>
	      				<c:when test="${!empty requestScope.presences_empty}">
	      					<p>${requestScope.presences_empty}</p>
	      				</c:when>
	      				<c:otherwise>
							<table class="table table-bordered table-sm">
							  <thead class="mdb-color darken-3">
							    <tr class="text-white">
							      <th>Date Seance</th>
							      <th>Heure Seance</th>
							      <th>Module</th>
							      <th>Prèsence</th>
							      <th>Justifiè</th>
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
	      				<c:when test="${!empty requestScope.notes_empty}">
	      					<p>${requestScope.notes_empty}</p>
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
	      				<c:when test="${!empty requestScope.ch_empty}">
	      					<p>Vous n'avez fait aucun changement de groupe</p>
	      				</c:when>
	      				<c:otherwise>
	      					<p>Groupe source: ${requestScope.changement.groupe_src} </p>
	      					<p>Date changement: ${requestScope.changement.date_ch}</p>
	      				</c:otherwise>
	      			</c:choose>
	      		</div>
	      	</div>  	      
	   </div> 	
	</body>
</html>