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
			<navbar Class="navbar navbar-expand-lg navbar-dark black-sell">
			    <logo><a class="navbar-brand" href="#">Accueil</a></logo>
			
			        <ul class="navbar-nav mr-auto">
			            <li class="nav-item">
			                <a class="nav-link waves-light" href="accueilClient" >Appel</a>
			            </li>
			        </ul>
	
			 		<div class="form-inline  waves-light" >
			 			<a class="btn btn-outline-default waves-effect btn-sm" href="deconnexion" >Deconnexion</a>
			 		</div> 
			</navbar>
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
	      
	      <div class="row" >
	      	<div class="col-lg-4">
				<div class="list-group">
					 <a href="profil" class="list-group-item active"> Appel </a>
					 <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
				</div>
	      	</div>
	      	<div class="col-lg-6">
	      	
					<c:choose>
					
						<c:when test="${!empty requestScope.titre_module}" >
							<div class="row">
				      			<div class="col-lg-12">
				      				<h4 style="margin-top:10px;" >Module: ${requestScope.titre_module}</h4>
				      			</div>		
				      		</div>	
			      			<div class="row">
								<div class="col-lg-12">
									<div class="form-group">
										<label for="sel1">Selectionner un groupe</label>
										<select class="form-control">
											<c:forEach items="${requestScope.groupes}" var="groupes" >
												<option value="${groupes}" > Groupe ${groupes} </option>
											</c:forEach>
										</select>
									</div>
								</div>
			      			</div>				
						</c:when>
						
						<c:otherwise>
							<div class="row">
				      			<div class="col-lg-12">
				      				<h4 style="margin-top:10px;" >Liste des Modules</h4>
				      			</div>		
				      		</div>	
				      		<div class="row">
				      			<div class="col-lg-12">
									<div class="form-group">
										<label for="sel1">Selectionner un Module</label>
										<select class="form-control" id="modules">
											<c:forEach items="${requestScope.modules}" var="modules" >
												<option value="${modules.id}" >${modules.titre}</option>
											</c:forEach>
										</select>
									</div>			      				
				      			</div>
				      		</div>	
				      		<div class="row">
								<div class="col-lg-12">
									<button id="disp_groupe" class="btn btn-deep-purple" >Afficher Groupes</button>
									<p>${requestScope.query1}</p>
								</div>	
				      		</div>
				      		<div class="row">
								<div class="col-lg-12" id="groupes"></div>
				      		</div>				
						</c:otherwise>
						
					</c:choose>
	      		
	      	</div>
	      </div>
	  </div>
	  <script src="../js/jquery-3.2.1.js"></script>
	  <script>
	  	var exist = false;
	  	$(document).ready(function(){
	  		$('#disp_groupe').on('click', function(){ 
	  			
	  			if(exist){
	  				$('#test').remove(); // we remove the old list of groupes which correspond to the ex module selected
	  			}
	  			
	  			$.ajax({
	  				url : 'groupes',
	  				type : 'POST',
	  				dataType: 'html',
	  				data : {
	  					module : $('#modules').val()
	  				},
	  				success : function(groupes,statut) {
	  					$('#groupes').append(groupes);
	  					exist = true;
	  				}
	  			}); 
	  		});
	  	});
	  </script>
	</body>
</html>