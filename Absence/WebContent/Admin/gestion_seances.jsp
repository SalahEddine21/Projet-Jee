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
		           <li class="active">Administrateur/Gestion des modules</li>
	            </ol>
	          </div>
	      </div>	
	      <div class="row" >
	      	<div class="col-lg-4">
				<div class="list-group">
					 <a href="profil" class="list-group-item list-group-item-action">DashBoard</a>
					 <a href="gestion_profs" class="list-group-item list-group-item-action">Gestion des Professeurs</a>
					 <a href="gestion_modules" class="list-group-item list-group-item-action">Gestion des modules</a>
					 <a href="gestion_groupes" class="list-group-item list-group-item-action ">Gestion des groupes</a>
					 <a href="gestion_seances" class="list-group-item active">Gestion des séances</a>
				</div>
	      	</div>
	      	
	      	<div class="col-lg-8">
				<div class="row">
				    <div class="col-lg-12">
				      	<h4 style="margin-top:10px;" >Ajouter Une Seance</h4>
				      	<hr>
				      	<p>${requestScope.query}</p>
				    </div>		
				</div>
				<form action="gestion_seances" method="post" >
					 <div class="col-lg-10">
					 	<div class="form-group">
					 		<label for="sel1">Selectionner le Groupe</label>
							<select class="form-control" name="groupe">
								<c:forEach items="${requestScope.groupes}" var="groupe" >
									<option value="${groupe.id}" >${groupe.id} / ${groupe.semestre}</option>
								</c:forEach>
							</select>
					 	</div> 
						<div class="form-group">
							<label for="sel1">Selectionner Le modules concerné</label>
							<select class="form-control" name="module">
								<c:forEach items="${requestScope.modules}" var="module" >
									<option value="${module.id}" >${module.titre} / ${module.semestre}</option>
								</c:forEach>
							</select>
						</div>	
						<div class="form-group">
							<input type="date" class="form-control" name="date" />
						</div>
						<div class="form-group">
							<label for="sel1">Selectionner L'heure</label>
							<select class="form-control" name="heure">
								<option value="8h-10h">8h-10h</option>
								<option value="10h-12h">10h-12h</option>
								<option value="12h-14h">12h-14h</option>
								<option value="14h-16h">14h-16h</option>
							</select>
						</div>
						
						<div class="form-group">
							<button type="submit" class="btn btn-deep-purple">Ajouter</button>
						</div>
						<div class="form-group">
							<p style="color:red;">${controle.errors.query}</p>
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
					      	<h4 style="margin-top:10px;" >Liste des Séances programmée</h4>
					    </div>							
					</div>					
				</div>
				<div class="col-lg-12" style="height: 600px; overflow: scroll;">
					<table class="table table-bordered table-sm">
						<thead class="mdb-color darken-3">
							<tr class="text-white">
							   <th>Séance</th>
							   <th>Module</th>
							   <th>Groupe</th>
							   <th>Date</th>
							   <th>Heure</th>
							   <th>Semestre</th>
							   <th>Prof</th>
							   <th></th>
							   <th></th>
							 </tr>
						</thead>
						<tbody>
						<c:forEach items="${requestScope.seances}" var="seance">
							<tr>
								<td>${seance.id_seance}</td>
								<td>${seance.module}</td>
								<td>${seance.id_groupe}</td>
								<td>${seance.date}</td>
								<td>${seance.heure}</td>
								<td>${seance.semestre}</td>
								<td>${seance.prof}</td>
								<td><a href="DetailSeance?id_seance=${seance.id_seance}" class="btn btn-info btn-sm" target='_blank' >Absence</a></td>
								<td><a href="suppSc?id_seance=${seance.id_seance}" class="btn btn-indigo btn-sm" >Supp</a></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>		
				<div class="row">
					<p><strong style="color:red;">Notice:</strong>La suppression d'une séance entrainra la suppression de tous ses dépendances(inclut les étudiants)</p>											
				</div>				      		
	      	</div>
	     </div>
	   </div>   		
	</body>
</html>