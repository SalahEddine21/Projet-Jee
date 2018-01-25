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
		           <li class="active">Professeur/Appel</li>
	            </ol>
	          </div>
	      </div>
	      
	      <div class="row" >
	      	<div class="col-lg-4">
				<div class="list-group">
					 <a href="profil" class="list-group-item list-group-item-action">Appel</a>
					 <a href="modifierListe" class="list-group-item list-group-item-action">Modifier Une Liste</a>
					 <a href="lancerEval" class="list-group-item active">Lancer Une evaluation</a>
					 <a href="attribuerNote" class="list-group-item list-group-item-action">Attribuer Les Notes</a>
				</div>
	      	</div>
	      	<div class="col-lg-8">
	      		
	      		<c:choose>
	      			<c:when test="${!empty requestScope.no_module}">
							<div class="row">
				      			<div class="col-lg-12">
				      				<h4 style="margin-top:10px;" >Information</h4>
				      			</div>		
				      		</div>	
				      		<div class="row">
				      			<div class="col-lg-8">
				      				<p><strong>${requestScope.no_module}</strong></p>
				      			</div>
				      		</div>	      			
	      			</c:when>
	      			
	      			<c:otherwise>
	      			
							<div class="row">
				      			<div class="col-lg-12">
				      				<h4 style="margin-top:10px;" >Espace de lancement des évaluations</h4>
				      			</div>		
				      		</div>	
				      		
				      		<form action="lancerEval" method="post">
					      		<div class="col-lg-10">
									<div class="form-group">
										<label for="sel1">Selectionner un Module</label>
										<select class="form-control" name="module">
											<c:forEach items="${requestScope.modules}" var="modules" >
												<option value="${modules.id}" >${modules.titre}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="sel1">Selectionner La Date de l'évaluation</label>
										<input type="date" class="input-group date" name="date">
									</div>	 
									  
									<div class="form-group">
										<label for="sel1">Selectionner L'heure de l'évaluation</label>
										<select name="heure" class="form-control">
											<option value="8h-10h">8h-10h</option>
											<option value="10h-12h">10h-12h</option>
											<option value="12h-14h">12h-14h</option>
											<option value="14h-16h">14h-16h</option>
										</select>
									</div>	   				
					      		</div>
								<div class="row">
									<div class="col-lg-10">
										<div class="pull-right">
											<button type="submit" class="btn btn-deep-purple">Notifier Les Groupes</button>
										</div>
									</div>
								</div>					
				      		</form>	   
				      		   			
	      			</c:otherwise>
	      		</c:choose>
	      		
	      	</div>
	      </div>		      
	    </div> 	
	</body>
</html>