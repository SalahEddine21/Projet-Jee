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
					 <a href="gestion_groupes" class="list-group-item active">Gestion des groupes</a>
					 <a href="gestion_seances" class="list-group-item list-group-item-action">Gestion des séances</a>
				</div>
	      	</div>
	      	<div class="col-lg-8">
				<div class="row">
				    <div class="col-lg-12">
				      	<h4 style="margin-top:10px;" >Ajouter Un Groupe</h4>
				      	<hr>
				      	<p>${requestScope.query}</p>
				    </div>		
				</div>	
				<form action="ajoutGrp" method="post" enctype="multipart/form-data" >
					 <div class="col-lg-10">
					 	<div class="form-group">
					 		<label for="sel1">Liste des étudiants du Groupe (num, nom, prenom, email, passe)</label>
					 		<input type="file" name="etudiants" class="form-control"  />
					 	</div>
						<div class="form-group">
							<label for="sel1">Selectionner la semestre </label>
							<select class="form-control" name="semestre">
								<option value="S1" >S1</option>
								<option value="S2" >S2</option>
								<option value="S3" >S3</option>
								<option value="S4" >S4</option>
								<option value="S5" >S5</option>
							</select>
							<p style="color:red;">${controle.errors.semestre}</p>
						</div>	 
						<div class="form-group">
							<label for="sel1">Selectionner Les modules du groupe</label>
							<select class="form-control" name="modules" multiple>
								<c:forEach items="${requestScope.modules}" var="module" >
									<option value="${module.id}" >${module.titre} / ${module.semestre}</option>
								</c:forEach>
							</select>
						</div>	
						<div class="form-group">
							<button type="submit" class="btn btn-deep-purple">Ajouter</button>
						</div>
						<div class="form-group">
							<p style="color:red;">${controle.errors.query}</p>
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
					      	<h4 style="margin-top:10px;" >Liste des Groupes</h4>
					    </div>							
					</div>
					<div class="col-lg-10">
					<table class="table table-bordered table-sm">
						<thead class="mdb-color darken-3">
							<tr class="text-white">
							   <th>ID</th>
							   <th>Semestre</th>
							   <th></th>
							   <th></th>
							 </tr>
						</thead>
						<tbody>
						<c:forEach items="${requestScope.groupes}" var="groupe">
							<tr>
								<td>${groupe.id}</td>
								<td>${groupe.semestre}</td>
								<td><a href="detailGrp?id_groupe=${groupe.id}" class="btn btn-info btn-sm" target='_blank' >Infos</a></td>
								<td><a href="suppGrp?id_groupe=${groupe.id}" class="btn btn-indigo btn-sm" >Supprimer</a></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<p><strong style="color:red;">Notice:</strong>La suppression d'un groupe entrainra la suppression de tous ses dépendances(inclut les étudiants)</p>						
					</div>
				</div>									    		
	      	</div>
	      </div>
	    </div>  		
	</body>
</html>