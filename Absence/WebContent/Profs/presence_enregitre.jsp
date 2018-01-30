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
			<nav Class="navbar navbar-expand-lg navbar-dark black-sell">
			    <logo><a class="navbar-brand" href="#">Accueil</a></logo>
			
			        <ul class="navbar-nav mr-auto">
			            <li class="nav-item">
			                <a class="nav-link waves-light" href="profil" >Appel</a>
			            </li>
			        </ul>
	
			 		<div class="form-inline  waves-light" >
			 			<a class="btn btn-outline-default waves-effect btn-sm" href="deconnexion" >Deconnexion</a>
			 		</div> 
			</nav>
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
	      
	      <div class="row" style="margin-top:40px;">
				<div class="col-lg-12" style="margin-top:50px;">
					<div class="col-lg-offset-3 col-lg-6">
						<div class="alert alert-success">
						  <h4 class="alert-heading">Well done!</h4>
						  <p style="margin-bottom:5px;">l'absence est enregistré avec <strong> succès</strong></p>
						</div>
						<div class="row">
							<div class="col-lg-offset-1">
							  <p><a class="btn btn-outline-primary btn-rounded waves-effect" href="modifierListe" >Modifier List</a>  
							  où <a class="btn btn-outline-danger btn-rounded waves-effect" href="deconnexion">se deconnecter</a> </p>
							</div>
						</div>
					</div>
					<p style="color:red">${requestScope.query}</p>
				</div>
	      </div>
	      <p>${requestScope.box}</p>
	      <p>${requestScope.box1}</p>
	      <p>${requestScope.fe}</p>
	      <p>${requestScope.fee}</p>
	      	      <p>${requestScope.re}</p>
	      	      <p>${requestScope.pe}</p>
	      
	    </div> 	
	</body>
</html>