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
	            <h1 class="page-header">Administrateur
	               <small>${sessionScope.admin.prenom} - ${sessionScope.admin.nom}</small>
	            </h1>
	            <ol class="breadcrumb">
		           <li><a href="">Accueil</a>
	               </li>
		           <li class="active">Admin/Detail_Groupe</li>
	            </ol>
	          </div>
	      </div>

	      <div class="row" style="margin-top:10px;">
	      	<div class="col-lg-12">
	      		<p>${requestScope.query}</p>
	      		<div class="col-lg-5" style="border: 2px solid black;">
	      			<p><strong>Groupe:</strong> ${requestScope.groupe.id}</p>
	      			<p><strong>Semestre:</strong> ${requestScope.groupe.semestre}</p>
	      			<p><strong>Total Etudiants:</strong> ${requestScope.count}</p>
	      		</div>
	      	</div>
	      </div>	      
	      <div class="row" style="margin-top:30px;">
	      	<div class="col-lg-6">
	      		<label>Liste des étudiants</label>
	      		<hr>
				<table class="table table-bordered table-sm">
					<thead class="mdb-color darken-3">
						<tr class="text-white">
							<th>Numèro</th>
							<th>Nom</th>
							<th>Prenom</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.etudiants}" var="etudiants">
						<tr>
							<td><a href="detailEtd?id_etd=${etudiants.id}" style="color:#f50057" target="_blank">${etudiants.num}</a></td>
							<td>${etudiants.nom}</td>
							<td>${etudiants.prenom}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>	      		
	      	</div>
	      	<div class="col-lg-5">
			    <label>Liste des Séances</label>
			    <hr>
				<c:choose>
					<c:when test="${!empty requestScope.no_seance}">
						<p>${requestScope.no_seance}</p>
					</c:when>
					<c:otherwise>	    
						<table class="table table-bordered table-sm">
							<thead class="mdb-color darken-3">
								<tr class="text-white">
									<th>Date</th>
									<th>Heure</th>
									<th>Appel</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${requestScope.seances}" var="seances">
								<tr>
									<td>${seances.date}</td>
									<td>${seances.heure}</td>
									<c:choose>
										<c:when test="${seances.appel == 1}">
											<td>Effectué</td>
										</c:when>
										<c:otherwise>
											<td>Non effectué</td>
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
		      		<c:when test="${!empty requestScope.no_leavers}">
		      			<p>${requestScope.no_leavers}</p>
		      		</c:when>
		      		<c:otherwise>
		      			<label>Liste des étudiants ayant changè le groupe</label>
		      			<ul>
			      			<c:forEach items="${requestScope.leavers}" var="etudiant">
			      				<li>
			      					<p>L'étudiant ${etudiant.nom} a quittè le groupe le ${etudiant.date_ch}</p>
			      				</li>
			      			</c:forEach>
		      			</ul>
		      		</c:otherwise>
		      	</c:choose>
			</div>
	      </div>
	      <div class="row">
			<div class="col-lg-12">
		      	<c:choose>
		      		<c:when test="${!empty requestScope.no_joiners}">
		      			<p>${requestScope.no_joiners}</p>
		      		</c:when>
		      		<c:otherwise>
		      		<label>Liste des étudiants ayant joignè le groupe</label>
		      			<ul>
			      			<c:forEach items="${requestScope.joiners}" var="etudiant">
			      				<li>
			      					<p>L'étudiant ${etudiant.nom} a joignè le groupe le ${etudiant.date_ch}</p>
			      				</li>
			      			</c:forEach>
		      			</ul>
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