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
		           <li class="active">Professeur/Modification Liste d'absence</li>
	            </ol>
	          </div>
	      </div>	
	      
	      <div class="row" >
	      	<div class="col-lg-4">
				<div class="list-group">
					 <a href="profil" class="list-group-item list-group-item-action">Appel</a>
					 <a href="modifierListe" class="list-group-item list-group-item-action active">Modifier Une Liste</a>
					 <a href="lancerEval" class="list-group-item list-group-item-action">Lancer Une evaluation</a>
					 <a href="attribuerNote" class="list-group-item list-group-item-action">Attribuer Les Notes</a>
				</div>
	      	</div>
	      	<div class="col-lg-8">
	      		<c:choose>
	      			<c:when test="${!empty requestScope.no_module}">
						<div class="row">
				      		<div class="col-lg-12">
				      			<h4 style="margin-top:10px;" >${requestScope.no_module}</h4>
				      		</div>		
				    	</div>		      				
	      			</c:when>
	      			<c:otherwise>
						<div class="row">
				      		<div class="col-lg-12">
				      			<h4 style="margin-top:10px;" >Espace de modification des Listes d'absences</h4>
				      		</div>		
				    	</div>	
				    	
				      	<div class="row">
					      	<div class="col-lg-8">
								<div class="form-group">
									<label for="sel1">Selectionner un Module</label>
									<select class="form-control" id="modules">
										<c:forEach items="${requestScope.modules}" var="modules" >
											<option value="${modules.id}" >${modules.titre}</option>
										</c:forEach>
									</select>
								</div>
								<p>${requestScope.query1}</p>			      				
					      	</div>
							<div class="col-lg-4">
								<button id="disp_groupe" class="btn btn-deep-purple" style="margin-top:30px;" >Afficher Groupes</button>
							</div>						
				      	</div>		
				      	<div id="groupes"></div> <!-- Do not delete this element look down to know why -->	
				      	<form id='seances' action="afficher_modifier_List" method='post' ></form>
				      			    	      			
	      			</c:otherwise>
	      		</c:choose>
	      	</div>
	      </div>		      
	     </div> 	
	     
	  <script src="../js/jquery-3.2.1.js"></script>
	  <script>
	  	var exist = false,seance = false;
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
	  				  	add();
	  				}
	  			}); 
	  		});
	  		
	  		$('#modules').change(function(){
	  			if(exist){
	  				$('#test').remove();
	  				exist = false;
	  				if(seance){
	  					$('#list_seances').remove();
	  					seance = false;
	  				}
	  			}
	  		});
			function add(){
				if(!seance){
					$('#dispseance').click(function(){
			  			$.ajax({
			  				url : 'seances',
			  				type : 'GET',
			  				dataType: 'html',
			  				data :  {module : $('#modules').val(), groupe : $('#groupe').val() },
			  				success : function(seances,statut) {
			  					$('#seances').append(seances);
			  					seance = true;
			  					$('#faire_appel').text("Afficher Liste");
			  				}
			  			}); 
					});	
				}
				$('#groupe').change(function(){
					if(seance){
	  					$('#list_seances').remove();
	  					seance = false;						
					}
				});
			}
			
	  	});
	  	
	  </script>	
	</body>
</html>