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
		           <li class="active">Administrateur/DASHBOARD</li>
	            </ol>
	          </div>
	      </div>	
	      <div class="row" >
	      	<div class="col-lg-4">
				<div class="list-group">
					 <a href="profil" class="list-group-item active">DashBoard</a>
					 <a href="gestion_profs" class="list-group-item list-group-item-action">Gestion des Professeurs</a>
					 <a href="gestion_modules" class="list-group-item list-group-item-action">Gestion des modules</a>
					 <a href="gestion_groupes" class="list-group-item list-group-item-action">Gestion des groupes</a>
					 <a href="gestion_seances" class="list-group-item list-group-item-action">Gestion des séances</a>
				</div>
	      	</div>
	      	
	      	<div class="col-lg-8">
	      	
				<div class="row">
				    <div class="col-lg-12">
				      	<h4 style="margin-top:10px;" >Tableau de Bord</h4>
				    </div>		
				</div>	
				
				<div class="row">
					<div class="col-lg-8">
						<div class="form-group">
							<label for="sel1">Sélectionner la date</label>
							<input type="date" class="input-group date" name="date" id="date">
						</div>	 					
					</div>
					<div class="col-lg-4">
						<button id="disp_seances" type="submit" class="btn btn-deep-purple" style="margin-top:30px;">Afficher les seances</button>
					</div>
				</div>
				<div class="row" style="margin-top:30px;">
					<div class="col-lg-12">
						<div id="data">
						
						</div>
					</div>
				</div>	      		
	      	</div>
	      </div>	
	    </div> 
	  <script src="../js/jquery-3.2.1.js"></script>
	  <script>
	  	var exist = false;
	  	$(document).ready(function(){
	  		
	  		$('#disp_seances').on('click', function(){ 
				if(!exist){
		  			$.ajax({
		  				url : 'seancesAdmin',
		  				type : 'GET',
		  				dataType: 'html',
		  				data : {
		  					date : $('#date').val()
		  				},
		  				success : function(seances,statut) {
		  					$('#data').append(seances);
		  					exist = true;	
		  				}
		  			}); 
				}
	  		});
	  		
	  		$('#date').change(function(){
	  			if(exist){
	  				$('#seances').remove();
	  				exist = false;
	  			}
	  		});
			
	  	});
	  	
	  </script> 		
	</body>
</html>