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
		           <li class="active">Etudiant/Changement de groupe</li>
	            </ol>
	          </div>
	      </div>
	      
	      <div class="row" style="margin-top:30px;">
	      	<p>${requestScope.query}</p>
	      	<div class="col-lg-offset-3 col-lg-6">
	      		<c:choose>
	      			<c:when test="${!empty requestScope.ch_founded}">
	      				<p>${requestScope.ch_founded}</p>
	      				<p><a href="profil" class="btn btn-unique" >Retour</a></p>
	      			</c:when>
	      			<c:otherwise>
			      		<form action="changement" method="post">
							<div class="form-group">
								<label for="sel1">Selectionner un Module</label>
								<select class="form-control" name="groupe">
									<c:forEach items="${requestScope.groupes}" var="groupe" >
										<option value="${groupe.id}" >Groupe ${groupe.id}</option>
									</c:forEach>
								</select>
							</div>
							<a href="profil" class="btn btn-pink" >Retour</a>	
							<button type="submit" class="btn btn-unique" >Changer</button>      			
			      		</form>
						<div class="alert alert-warning" >
							<strong>Attention</strong>
							<p>Vous ne pouvez changer le groupe qu'une et une seule fois</p>
						</div>	      				
	      			</c:otherwise>
	      		</c:choose>

	      	</div>
	      </div>
	      
	   </div>   	
	</body>
</html>